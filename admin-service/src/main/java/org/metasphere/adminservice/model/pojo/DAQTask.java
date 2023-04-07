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
@Table(name = "ms_daq_task")
public class DAQTask extends MSEntity {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 唯一识别码
     */
    private String code;
    /**
     * 项目执行阶段（11-新建/21-数据采集阶段/22-数据待入库阶段/31-数据分析阶段/32-数据统计阶段/41-执行完毕）
     */
    private Integer stage;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 终止时间
     */
    private LocalDateTime finishedAt;

    public DAQTask() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdTime) {
        this.createdAt = createdTime;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedTime) {
        this.finishedAt = finishedTime;
    }
}
