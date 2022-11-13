package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.MsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:28
 * @Modified By:
 */
@Repository
public interface MsUserRepository extends JpaRepository<MsUser, Long> {

    /**
     * 根据Email查询用户信息
     *
     * @param email 用户Email
     * @return 查询到的用户信息
     */
    MsUser findMsUserByEmail(String email);
}
