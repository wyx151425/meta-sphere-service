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
@Table(name = "daq_project_keyword")
public class DAQProjectKeyword extends MSEntity {
    /**
     * 数据采集项目的ID
     */
    private Long projectId;
    /**
     * 数据采集项目的名称
     */
    private String projectName;
    /**
     * 关键词
     */
    private String keyword;

    public DAQProjectKeyword() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
