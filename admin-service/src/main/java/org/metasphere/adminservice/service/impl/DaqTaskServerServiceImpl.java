package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.model.pojo.DaqTaskServer;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.repository.DaqTaskServerRepository;
import org.metasphere.adminservice.service.DaqTaskServerService;
import org.metasphere.adminservice.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-10 16:42
 * @Modified By:
 */
@Service(value = "daqTaskServerService")
public class DaqTaskServerServiceImpl implements DaqTaskServerService {

    private final DaqTaskServerRepository taskServerRepository;
    private final ServerService serverService;

    @Autowired
    public DaqTaskServerServiceImpl(DaqTaskServerRepository taskServerRepository, ServerService serverService) {
        this.taskServerRepository = taskServerRepository;
        this.serverService = serverService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskServer(Long daqTaskId, Long serverId) {
        Server server = serverService.findServerById(serverId);
        DaqTaskServer taskServer = new DaqTaskServer();
        taskServer.setTaskId(daqTaskId);
        taskServer.setServerId(serverId);
        taskServer.setServerHost(server.getIpAddress());
        taskServerRepository.save(taskServer);
    }

    @Override
    public List<DaqTaskServer> findDaqTaskServers(Long daqTaskId) {
        return taskServerRepository.findAllByTaskId(daqTaskId);
    }
}
