package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.auth.Permission;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 系统权限业务逻辑规范
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
public interface PermissionService {
    /**
     * 保存权限
     *
     * @param permission 权限信息
     */
    void savePermission(Permission permission);

    /**
     * 根据ID删除权限
     *
     * @param id 权限ID
     */
    void deletePermission(Long id);

    /**
     * 根据ID批量删除权限
     *
     * @param ids 权限ID
     */
    void deletePermissions(List<Long> ids);

    /**
     * 修改权限的名称
     *
     * @param permission 包含权限新名称的权限对象
     */
    void updatePermissionName(Permission permission);

    /**
     * 分页查询权限信息
     *
     * @param pageNum  分页页码
     * @param pageSize 单页数据量
     * @return 包含分页信息的权限数据
     */
    MsPage<Permission> findPermissionsByPagination(Integer pageNum, Integer pageSize);

    /**
     * 根据用户ID获取权限
     *
     * @param userId 用户ID
     * @return 属于该用户的权限
     */
    List<Permission> findPermissionsByUser(Long userId);
}
