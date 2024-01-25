package org.metasphere.adminservice.model.pojo.daq;

import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集任务执行阶段
 * @Date: Created in 2024-01-24 10:01
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_task_execution_stage")
public class DaqTaskExecutionStage extends MetaSphereEntity {
    /**
     * 阶段名称
     */
    private String name;
    /**
     * 阶段编号
     */
    private String code;
    /**
     * 数据量
     */
    private String dataVolume;
    /**
     * 开始时间
     */
    private LocalDateTime startedAt;
    /**
     * 完成时间
     */
    private LocalDateTime completedAt;
    /**
     * 备注
     */
    private String remark;

    public DaqTaskExecutionStage() {
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

    public String getDataVolume() {
        return dataVolume;
    }

    public void setDataVolume(String dataVolume) {
        this.dataVolume = dataVolume;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
