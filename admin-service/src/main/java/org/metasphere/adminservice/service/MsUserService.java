package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.pojo.MsUser;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
public interface MsUserService {

    /**
     * 通过Email登录
     *
     * @param email    邮箱
     * @param password 密码
     */
    void loginByEmail(String email, String password);

    /**
     * 根据邮箱地址获取用户
     *
     * @param email 用户的邮箱地址
     * @return 查询到的用户
     */
    MsUser findMsUserByEmail(String email);
}
