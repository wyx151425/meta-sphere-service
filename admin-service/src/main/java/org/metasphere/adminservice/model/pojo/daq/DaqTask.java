package org.metasphere.adminservice.model.pojo.daq;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集任务类
 * @Date: Created in 2023-02-22 21:36
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_task")
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_daq_task SET status = 0 WHERE id = ?")
public class DaqTask extends MetaSphereEntity {
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务编码
     */
    private String code;
    /**
     * 所属舆情热点事件的ID
     */
    private Long hotEventId;
    /**
     * 任务执行阶段
     */
    private Integer executionStage;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 终止时间
     */
    private LocalDateTime finishedAt;

    public DaqTask() {
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

    public Long getHotEventId() {
        return hotEventId;
    }

    public void setHotEventId(Long hotEventId) {
        this.hotEventId = hotEventId;
    }

    public Integer getExecutionStage() {
        return executionStage;
    }

    public void setExecutionStage(Integer stage) {
        this.executionStage = stage;
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
