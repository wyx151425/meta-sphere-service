package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.auth.MSAuthUser;
import org.metasphere.adminservice.exception.AccountDisabledException;
import org.metasphere.adminservice.exception.UserNotFoundException;
import org.metasphere.adminservice.model.pojo.User;
import org.metasphere.adminservice.model.pojo.Permission;
import org.metasphere.adminservice.service.PermissionService;
import org.metasphere.adminservice.service.UserService;
import org.metasphere.adminservice.util.ConstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 16:20
 * @Modified By:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        if (null == user) {
            throw new UserNotFoundException();
        }

        if (ConstUtils.MSEntity.Status.DISABLE == user.getStatus()) {
            throw new AccountDisabledException();
        }

        List<Permission> userPermissions = permissionService.findPermissionsByUser(user.getId());
        List<SimpleGrantedAuthority> authorities = userPermissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getCode()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ACTION:QUERY"));
        return new MSAuthUser(user, authorities);
    }
}
