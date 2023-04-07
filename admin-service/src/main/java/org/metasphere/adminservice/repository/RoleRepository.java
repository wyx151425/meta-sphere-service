package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository(value = "roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    /**
     * 根据编码查询角色
     *
     * @param code 角色编码
     * @return 查询到的角色信息
     */
    Role findRoleByCode(String code);
}
