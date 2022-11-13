package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.auth.MsAuthUser;
import org.metasphere.adminservice.exception.AccountDisabledException;
import org.metasphere.adminservice.exception.UserNotFoundException;
import org.metasphere.adminservice.model.pojo.MsUser;
import org.metasphere.adminservice.service.MsUserService;
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
    private MsUserService msUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MsUser msUser = msUserService.findMsUserByEmail(email);
        if (null == msUser) {
            throw new UserNotFoundException();
        }

        if (ConstUtils.MsEntity.Status.DISABLE == msUser.getStatus()) {
            throw new AccountDisabledException();
        }

        return new MsAuthUser(msUser, Collections.emptyList());
    }
}
