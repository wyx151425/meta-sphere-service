package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MsStatusCode;
import org.metasphere.adminservice.exception.MsException;
import org.metasphere.adminservice.model.pojo.MsUser;
import org.metasphere.adminservice.repository.MsUserRepository;
import org.metasphere.adminservice.service.MsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
@Service
public class MsUserServiceImpl implements MsUserService {

    @Autowired
    private MsUserRepository msUserRepository;

    @Override
    public void loginByEmail(String email, String password) {
        MsUser user = msUserRepository.findMsUserByEmail(email);

        if (null == user) {
            throw new MsException(MsStatusCode.SYSTEM_ERROR);
        }
    }

    @Override
    public MsUser findMsUserByEmail(String email) {
        return msUserRepository.findMsUserByEmail(email);
    }
}
