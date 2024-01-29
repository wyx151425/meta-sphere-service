package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskServer;

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
    void saveDaqTaskServer(Long daqTaskId, Long serverId);

    /**
     * 根据数据采集任务ID删除数据采集服务器
     *
     * @param daqTaskId 数据采集任务ID
     */
    void deleteDaqTaskServersByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID获取数据采集服务器
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集服务器
     */
    DaqTaskServer findDaqTaskServer(Long daqTaskId);

    /**
     * 根据数据采集任务ID获取数据采集服务器
     *
     * @param daqTaskId 数据采集任务的ID
     * @return 属于该任务的服务器
     */
    List<DaqTaskServer> findDaqTaskServers(Long daqTaskId);

    /**
     * 绑定数据采集任务服务器
     *
     * @param daqTaskId 数据采集任务ID
     * @param serverId  服务器ID
     */
    void bindDaqTaskServer(Long daqTaskId, Long serverId);

    /**
     * 绑定数据采集任务服务器
     *
     * @param daqTaskId 数据采集任务ID
     * @param serverIds  服务器ID
     */
    void bindDaqTaskServers(Long daqTaskId, List<Long> serverIds);

    /**
     * 查询数据采集任务已绑定的服务器的数量
     *
     * @param daqTaskId 数据采集任务的ID
     * @return 已绑定的服务器的数量
     */
    long countServerNumberByDaqTask(Long daqTaskId);
}
