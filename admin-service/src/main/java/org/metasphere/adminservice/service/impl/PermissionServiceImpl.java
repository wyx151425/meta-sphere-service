package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.exception.PermissionExistException;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.Permission;
import org.metasphere.adminservice.repository.PermissionRepository;
import org.metasphere.adminservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 15:05
 * @Modified By:
 */
@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePermission(Permission permission) {
        Permission target = permissionRepository.findPermissionByCode(permission.getCode());
        if (null != target) {
            throw new PermissionExistException();
        } else {
            permissionRepository.save(permission);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermissions(List<Long> ids) {
        permissionRepository.deleteAllById(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermissionName(Permission permission) {
        permissionRepository.save(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public MsPage<Permission> findPermissionsByPagination(Integer pageNum, Integer pageSize) {
        Page<Permission> pageContext =  permissionRepository.findAll(PageRequest.of(pageNum, pageSize));
        return new MsPage<>(pageContext);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Permission> findPermissionsByUser(Long userId) {
        return new ArrayList<>();
    }
}
