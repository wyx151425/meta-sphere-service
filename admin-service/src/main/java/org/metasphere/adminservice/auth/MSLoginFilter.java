package org.metasphere.adminservice.auth;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.model.vo.req.UserLoginVO;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.util.JWTUtils;
import org.metasphere.adminservice.util.ResponseUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 18:50
 * @Modified By:
 */
@Slf4j
public class MSLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisTemplate redisTemplate;

    public MSLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/users/login", "POST"));
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            UserLoginVO userLoginVO = jsonMapper.readValue(request.getInputStream(), UserLoginVO.class);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userLoginVO.getEmail(), userLoginVO.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MSAuthUser msAuthUser = (MSAuthUser) authResult.getPrincipal();
        String token = JWTUtils.generateToken(msAuthUser.getMsUser().getId(), msAuthUser.getMsUser().getEmail());

        redisTemplate.opsForValue().set(msAuthUser.getUsername(), JSON.toJSONString(msAuthUser.getAuthorities()));

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        ResponseUtils.out(response, MSResponse.success(map));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("MsLoginFilter", failed);
        if (failed.getCause() instanceof RuntimeException) {
            ResponseUtils.out(response, MSResponse.accessDenied());
        } else {
            ResponseUtils.out(response, MSResponse.usernameOrPasswordError());
        }
    }
}
