package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository(value = "permissionRepository")
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    /**
     * 根据权限编号获取权限
     *
     * @param code 权限编号
     * @return 权限信息
     */
    Permission findPermissionByCode(String code);
}
