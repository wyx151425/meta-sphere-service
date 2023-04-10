package org.metasphere.adminservice.model.pojo;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 15:49
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_task_spider")
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_daq_task_spider SET status = 0 WHERE id = ?")
public class DaqTaskSpider extends MetaSphereEntity {
    /**
     * 项目的ID
     */
    private Long taskId;
    /**
     * 项目的名称
     */
    private String taskName;
    /**
     * 项目的编号
     */
    private String taskCode;
    /**
     * 爬虫的ID
     */
    private Long spiderId;
    /**
     * 爬虫的名称
     */
    private String spiderName;
    /**
     * 爬虫的编码
     */
    private String spiderCode;
    /**
     * 爬虫的状态（2-调度/1-启用/0-禁用）
     */
    private Integer spiderStatus;
    /**
     * 服务器主机地址
     */
    private String serverHost;
    /**
     * 服务器端口号
     */
    private Integer serverPort;
    /**
     * 数据采集任务ID
     */
    private String jobId;

    public DaqTaskSpider() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getSpiderId() {
        return spiderId;
    }

    public void setSpiderId(Long spiderId) {
        this.spiderId = spiderId;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }

    public String getSpiderCode() {
        return spiderCode;
    }

    public void setSpiderCode(String spiderCode) {
        this.spiderCode = spiderCode;
    }

    public Integer getSpiderStatus() {
        return spiderStatus;
    }

    public void setSpiderStatus(Integer spiderStatus) {
        this.spiderStatus = spiderStatus;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
