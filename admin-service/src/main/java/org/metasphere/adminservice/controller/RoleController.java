package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.auth.Role;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.auth.RoleService;
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
    public MSResponse actionSaveRole(@RequestBody Role role) {
        roleService.saveRole(role);
        return MSResponse.success();
    }

    @DeleteMapping(value = "{id}")
    public MSResponse actionDeleteRole(@PathVariable(value = "id") Long id) {
        roleService.deleteRole(id);
        return MSResponse.success();
    }

    @DeleteMapping(value = "{ids}")
    public MSResponse actionDeleteRoles(@PathVariable(value = "ids") List<Long> ids) {
        roleService.deleteRoles(ids);
        return MSResponse.success();
    }

    @PutMapping(value = "name")
    public MSResponse actionUpdateRoleName(@RequestBody Role role) {
        roleService.updateRoleName(role);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByPagination")
    public MSResponse actionQueryRolesByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<Role> page = roleService.findRolesByPagination(pageNum, pageSize);
        return MSResponse.success(page);
    }
}
