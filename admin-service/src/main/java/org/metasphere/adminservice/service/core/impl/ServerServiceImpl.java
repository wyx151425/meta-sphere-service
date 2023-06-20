package org.metasphere.adminservice.service.core.impl;

import org.metasphere.adminservice.constant.MSConst;
import org.metasphere.adminservice.constant.MSStatusCode;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.repository.ServerRepository;
import org.metasphere.adminservice.service.daq.ScrapydService;
import org.metasphere.adminservice.service.core.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.net.*;

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
    private ScrapydService scrapydService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveServer(Server server) {
        if (!checkServerStatus(server.getIpAddress())) {
            throw new MSException(MSStatusCode.SERVER_UNREACHABLE);
        }
        if (MSConst.Server.Type.DAQ == server.getType() && !scrapydService.checkScrapydStatus(server.getIpAddress(), server.getPort())) {
            throw new MSException(MSStatusCode.PORT_UNREACHABLE);
        } else if (MSConst.Server.Type.NLP == server.getType()) {
            // TODO: 添加NLP服务器端口校验
        }

        serverRepository.save(server);
    }

    @Override
    public Server findServerById(Long id) {
        return serverRepository.findById(id).orElseThrow(MSException::getDataNotFoundException);
    }

    @Override
    public MSPage<Server> findServersByTypeAndPagination(Integer type, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Server> page = serverRepository.findAll((Specification<Server>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("type"), type);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MSPage.newInstance(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public MSPage<Server> findServersByPagination(Integer pageNum, Integer pageSize) {
        Page<Server> pageContext = serverRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        return new MSPage<>(pageContext);
    }

    @Override
    public Boolean checkServerStatus(String ipAddress) {
        try {
            InetAddress host = InetAddress.getByName(ipAddress);
            return host.isReachable(3000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer checkServerStatus(String ipAddress, Integer port) {
        try (Socket socket = new Socket()) {
            InetSocketAddress address = new InetSocketAddress(ipAddress, port);
            socket.connect(address, 3000);
            return 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
