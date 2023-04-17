package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskServer;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.repository.daq.DaqTaskServerRepository;
import org.metasphere.adminservice.service.daq.DaqTaskServerService;
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
    public void saveDaqTaskServer(Long daqTaskId, Long serverId) {
        Server server = serverService.findServerById(serverId);
        DaqTaskServer taskServer = new DaqTaskServer();
        taskServer.setTaskId(daqTaskId);
        taskServer.setServerId(serverId);
        taskServer.setServerIpAddress(server.getIpAddress());
        taskServer.setServerPort(server.getPort());
        taskServerRepository.save(taskServer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDaqTaskServersByTaskId(Long daqTaskId) {
        taskServerRepository.deleteAllByTaskId(daqTaskId);
    }

    @Override
    public DaqTaskServer findDaqTaskServer(Long daqTaskId) {
        return taskServerRepository.findOneByTaskId(daqTaskId);
    }

    @Override
    public List<DaqTaskServer> findDaqTaskServers(Long daqTaskId) {
        return taskServerRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindDaqTaskServer(Long daqTaskId, Long serverId) {
        deleteDaqTaskServersByTaskId(daqTaskId);
        saveDaqTaskServer(daqTaskId, serverId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindDaqTaskServers(Long daqTaskId, List<Long> serverIds) {
        deleteDaqTaskServersByTaskId(daqTaskId);
        serverIds.forEach(serverId -> saveDaqTaskServer(daqTaskId, serverId));
    }
}
