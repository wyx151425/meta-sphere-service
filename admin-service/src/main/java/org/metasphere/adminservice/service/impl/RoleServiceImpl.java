package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.exception.PermissionExistException;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.auth.Role;
import org.metasphere.adminservice.repository.RoleRepository;
import org.metasphere.adminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 16:19
 * @Modified By:
 */
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(Role role) {
        Role target = roleRepository.findRoleByCode(role.getCode());
        if (null != target) {
            throw new PermissionExistException();
        } else {
            roleRepository.save(role);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(List<Long> ids) {
        roleRepository.deleteAllById(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleName(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public MsPage<Role> findRolesByPagination(Integer pageNum, Integer pageSize) {
        Page<Role> pageContext = roleRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        return new MsPage<>(pageContext);
    }
}
