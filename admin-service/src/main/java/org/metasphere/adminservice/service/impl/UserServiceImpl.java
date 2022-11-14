package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSStatusCode;
import org.metasphere.adminservice.exception.MsException;
import org.metasphere.adminservice.model.pojo.MSUser;
import org.metasphere.adminservice.repository.UserRepository;
import org.metasphere.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MSUser findMsUserByEmail(String email) {
        return userRepository.findMsUserByEmail(email);
    }
}
