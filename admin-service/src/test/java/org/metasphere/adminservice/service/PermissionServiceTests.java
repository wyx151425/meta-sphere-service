package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.model.pojo.auth.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 15:28
 * @Modified By:
 */
@SpringBootTest
public class PermissionServiceTests {

    @Autowired
    PermissionService permissionService;

    @Test
    void testSavePermission() {
        LocalDateTime dateTime = LocalDateTime.now().withNano(0);
        Permission permission = new Permission();
        permission.setStatus(MsConst.MetaSphereEntity.Status.ENABLED);
        permission.setObjectId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        permission.setCreateAt(dateTime);
        permission.setUpdateAt(dateTime);
        permission.setCode("MENU:ALL");
        permission.setName("可以查看所有菜单");
        permissionService.savePermission(permission);
    }

    @Test
    void testDeletePermission() {
        permissionService.deletePermission(1L);
        permissionService.deletePermissions(Collections.singletonList(1L));
    }

    @Test
    void testFindPermissionByPagination() {
        permissionService.findPermissionsByPagination(1, 20);
    }
}
