package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:28
 * @Modified By:
 */
@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据Email查询用户信息
     *
     * @param email 用户Email
     * @return 查询到的用户信息
     */
    User findUserByEmail(String email);
}
