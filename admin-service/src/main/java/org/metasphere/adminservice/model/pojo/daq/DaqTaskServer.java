package org.metasphere.adminservice.model.pojo.daq;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-10 16:27
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_task_server")
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_daq_task_server SET status = 0 WHERE id = ?")
public class DaqTaskServer extends MetaSphereEntity {
    /**
     * 数据采集任务的ID
     */
    private Long taskId;
    /**
     * 服务器的ID
     */
    private Long serverId;
    /**
     * 服务器主机IP
     */
    private String serverIpAddress;
    /**
     * 服务器主机端口号
     */
    private Integer serverPort;

    public DaqTaskServer() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }

    public void setServerIpAddress(String serverHost) {
        this.serverIpAddress = serverHost;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
}
