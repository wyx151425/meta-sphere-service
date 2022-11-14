package org.metasphere.adminservice.auth;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.util.JWTUtils;
import org.metasphere.adminservice.util.ResponseUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 19:16
 * @Modified By:
 */
@Slf4j
public class MSAuthenticationFilter extends OncePerRequestFilter {

    private RedisTemplate redisTemplate;

    public MSAuthenticationFilter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("uri: " + request.getRequestURI());
        if ("/api/admin/users/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            ResponseUtils.out(response, MSResponse.accessDenied());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info("token: " + token);
        if (StringUtils.hasLength(token)) {
            String email = JWTUtils.getUserEmailByToken(token);
            log.info("email: " + email);
            if (StringUtils.hasLength(email)) {
                String authoritiesStr = (String) redisTemplate.opsForValue().get(email);
                List<Map> mapList = JSON.parseArray(authoritiesStr, Map.class);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (Map map : mapList) {
                    authorities.add(new SimpleGrantedAuthority((String)map.get("authority")));
                }
                return new UsernamePasswordAuthenticationToken(email, null, authorities);
            }
        }
        return null;
    }


}
