package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.repository.ServerRepository;
import org.metasphere.adminservice.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author: WangZhenqi
 * @Description: 系统服务器业务逻辑实现
 * @Date: Created in 2023-03-07 20:21
 * @Modified By:
 */
@Service(value = "serverService")
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveServer(Server server) {
        serverRepository.save(server);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public MSPage<Server> findServersByPagination(Integer pageNum, Integer pageSize) {
        Page<Server> pageContext = serverRepository.findAll(PageRequest.of(pageNum, pageSize));
        return new MSPage<>(pageContext);
    }
}
