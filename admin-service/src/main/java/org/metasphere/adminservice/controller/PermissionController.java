package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.auth.Permission;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 系统权限操作API
 * @Date: Created in 2022-11-14 11:01
 * @Modified By:
 */
@RestController
@RequestMapping(value = "permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "")
    public MSResponse actionSavePermission(@RequestBody Permission permission) {
        permissionService.savePermission(permission);
        return MSResponse.success();
    }

    @DeleteMapping(value = "{id}")
    public MSResponse actionDeletePermission(@PathVariable(value = "id") Long id) {
        permissionService.deletePermission(id);
        return MSResponse.success();
    }

    @DeleteMapping(value = "{ids}")
    public MSResponse actionDeletePermissions(@PathVariable(value = "ids") List<Long> ids) {
        permissionService.deletePermissions(ids);
        return MSResponse.success();
    }

    @PutMapping(value = "name")
    public MSResponse actionUpdatePermissionName(@RequestBody Permission permission) {
        permissionService.updatePermissionName(permission);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByPagination")
    public MSResponse actionQueryPermissionsByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<Permission> page = permissionService.findPermissionsByPagination(pageNum, pageSize);
        return MSResponse.success(page);
    }
}
