package org.metasphere.adminservice.service.auth;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.auth.Role;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 系统用户业务逻辑规范
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
public interface RoleService {

    /**
     * 保存角色信息
     *
     * @param role 角色信息
     */
    void saveRole(Role role);

    /**
     * 根据ID删除角色
     *
     * @param id 角色ID
     */
    void deleteRole(Long id);

    /**
     * 根据ID批量删除角色
     *
     * @param ids 角色ID
     */
    void deleteRoles(List<Long> ids);

    /**
     * 修改角色名称
     *
     * @param role 包含新名称的角色信息
     */
    void updateRoleName(Role role);

    /**
     * 分页查询角色信息
     *
     * @param pageNum  分页页码
     * @param pageSize 单页数据量
     * @return 查询到的
     */
    MSPage<Role> findRolesByPagination(Integer pageNum, Integer pageSize);
}
