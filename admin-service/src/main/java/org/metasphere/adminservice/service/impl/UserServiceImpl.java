package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.constant.MSStatusCode;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.pojo.User;
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
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String email, String password) {
        User targetUser = userRepository.findUserByEmail(email);
        if (null != targetUser) {
            if (targetUser.getPassword().equals(password)) {
                return targetUser;
            } else {
                throw new MSException(MSStatusCode.ACCOUNT_AUTHENTICATION_FAILED);
            }
        } else {
            throw new MSException(MSStatusCode.USER_NOT_FOUND);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
