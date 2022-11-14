package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.auth.MSAuthUser;
import org.metasphere.adminservice.exception.AccountDisabledException;
import org.metasphere.adminservice.exception.UserNotFoundException;
import org.metasphere.adminservice.model.pojo.MSUser;
import org.metasphere.adminservice.service.UserService;
import org.metasphere.adminservice.util.ConstUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MSUser msUser = userService.findMsUserByEmail(email);
        if (null == msUser) {
            throw new UserNotFoundException();
        }

        if (ConstUtils.MsEntity.Status.DISABLE == msUser.getStatus()) {
            throw new AccountDisabledException();
        }

        return new MSAuthUser(msUser, Collections.emptyList());
    }
}
