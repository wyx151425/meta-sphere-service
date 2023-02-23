package org.metasphere.adminservice.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集项目类
 * @Date: Created in 2023-02-22 21:36
 * @Modified By:
 */
@Entity
@Table(name = "daq_project")
public class DAQProject extends MSEntity {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 终止时间
     */
    private LocalDateTime finishedTime;

    public DAQProject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(LocalDateTime finishedTime) {
        this.finishedTime = finishedTime;
    }
}
