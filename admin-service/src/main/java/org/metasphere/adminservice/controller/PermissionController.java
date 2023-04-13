package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.auth.Permission;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.PermissionService;
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
    public MsResponse actionSavePermission(@RequestBody Permission permission) {
        permissionService.savePermission(permission);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{id}")
    public MsResponse actionDeletePermission(@PathVariable(value = "id") Long id) {
        permissionService.deletePermission(id);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{ids}")
    public MsResponse actionDeletePermissions(@PathVariable(value = "ids") List<Long> ids) {
        permissionService.deletePermissions(ids);
        return MsResponse.success();
    }

    @PutMapping(value = "name")
    public MsResponse actionUpdatePermissionName(@RequestBody Permission permission) {
        permissionService.updatePermissionName(permission);
        return MsResponse.success();
    }

    @GetMapping(value = "queryByPagination")
    public MsResponse actionQueryPermissionsByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<Permission> page = permissionService.findPermissionsByPagination(pageNum, pageSize);
        return MsResponse.success(page);
    }
}
