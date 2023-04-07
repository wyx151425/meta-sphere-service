package org.metasphere.adminservice.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集项目下的关键词
 * @Date: Created in 2023-02-22 21:33
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_task_keyword")
public class DAQTaskKeyword extends MSEntity {
    /**
     * 数据采集项目的ID
     */
    private Long taskId;
    /**
     * 数据采集项目的名称
     */
    private String taskName;
    /**
     * 项目编码
     */
    private String taskCode;
    /**
     * 关键词
     */
    private String keyword;

    public DAQTaskKeyword() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long projectId) {
        this.taskId = projectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String projectName) {
        this.taskName = projectName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String projectCode) {
        this.taskCode = projectCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
