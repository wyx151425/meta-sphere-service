package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MsStatusCode;
import org.metasphere.adminservice.exception.MsException;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.repository.ServerRepository;
import org.metasphere.adminservice.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveServer(Server server) {
        Integer status = checkServerStatus(server.getHost());
        if (1 == status) {
            serverRepository.save(server);
        } else {
            throw new MsException(MsStatusCode.SERVER_UNREACHABLE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public MsPage<Server> findServersByPagination(Integer pageNum, Integer pageSize) {
        Page<Server> pageContext = serverRepository.findAll(PageRequest.of(pageNum, pageSize));
        return new MsPage<>(pageContext);
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

    @Override
    public Integer checkServerStatus(String host, Integer port) {
        try (Socket socket = new Socket()) {
            InetSocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address, 3000);
            return 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
