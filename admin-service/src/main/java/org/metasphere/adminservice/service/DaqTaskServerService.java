package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.pojo.DaqTaskServer;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-10 16:41
 * @Modified By:
 */
public interface DaqTaskServerService {
    /**
     * 添加数据采集任务服务器
     *
     * @param daqTaskId 数据采集任务的ID
     * @param serverId  服务器的ID
     */
    void addDaqTaskServer(Long daqTaskId, Long serverId);

    /**
     * 根据数据采集任务ID获取数据采集服务器
     *
     * @param daqTaskId 数据采集任务的ID
     * @return 属于该任务的服务器
     */
    List<DaqTaskServer> findDaqTaskServers(Long daqTaskId);
}
