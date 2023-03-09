package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.exception.MSException;
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
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

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

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public Integer checkServerStatus(String hostName) {
        try {
            InetAddress host = InetAddress.getByName(hostName);
            boolean isReachable = host.isReachable(3000);
            return isReachable ? 1 : 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
