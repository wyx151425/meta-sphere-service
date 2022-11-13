package org.metasphere.adminservice.auth;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.util.JwtUtils;
import org.metasphere.adminservice.util.ResponseUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 19:16
 * @Modified By:
 */
@Slf4j
public class MsAuthenticationFilter extends OncePerRequestFilter {

    public MsAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("uri: " + request.getRequestURI());
        if ("/admin/user/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            ResponseUtils.out(response, MsResponse.success());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("token");
        log.info("token: " + token);
        if (StringUtils.hasLength(token)) {
            String email = JwtUtils.getUserEmailByToken(token);
            if (StringUtils.hasLength(email)) {
                return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
            }
        }
        return null;
    }


}
