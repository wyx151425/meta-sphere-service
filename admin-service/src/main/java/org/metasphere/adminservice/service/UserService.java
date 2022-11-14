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
     * 根据邮箱地址获取用户
     *
     * @param email 用户的邮箱地址
     * @return 查询到的用户
     */
    User findMsUserByEmail(String email);
}
