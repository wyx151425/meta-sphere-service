package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.auth.Role;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 系统角色操作API
 * @Date: Created in 2022-11-14 11:01
 * @Modified By:
 */
@RestController
@RequestMapping(value = "roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "")
    public MsResponse actionSaveRole(@RequestBody Role role) {
        roleService.saveRole(role);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{id}")
    public MsResponse actionDeleteRole(@PathVariable(value = "id") Long id) {
        roleService.deleteRole(id);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{ids}")
    public MsResponse actionDeleteRoles(@PathVariable(value = "ids") List<Long> ids) {
        roleService.deleteRoles(ids);
        return MsResponse.success();
    }

    @PutMapping(value = "name")
    public MsResponse actionUpdateRoleName(@RequestBody Role role) {
        roleService.updateRoleName(role);
        return MsResponse.success();
    }

    @GetMapping(value = "queryByPagination")
    public MsResponse actionQueryRolesByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<Role> page = roleService.findRolesByPagination(pageNum, pageSize);
        return MsResponse.success(page);
    }
}
