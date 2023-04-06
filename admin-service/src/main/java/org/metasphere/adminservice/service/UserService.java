package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.pojo.User;

/**
 * @Author: WangZhenqi
 * @Description: 系统用户业务逻辑规范
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
public interface UserService {
    /**
     * 使用邮箱和密码登录系统
     *
     * @param email    邮箱
     * @param password 密码
     * @return 登录成功后的用户对象
     */
    User login(String email, String password);

    /**
     * 根据邮箱地址获取用户
     *
     * @param email 用户的邮箱地址
     * @return 查询到的用户
     */
    User findUserByEmail(String email);
}
