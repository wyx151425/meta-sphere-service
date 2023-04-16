package org.metasphere.adminservice.model.pojo.daq;

import org.hibernate.annotations.Where;
import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-11 21:26
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_task_data_volume")
@Where(clause = "status = 1")
public class DaqTaskDataVolume extends MetaSphereEntity {
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务编号
     */
    private String taskCode;
    /**
     * 爬虫名称
     */
    private String spiderName;
    /**
     * 爬虫编号
     */
    private String spiderCode;
    /**
     * 统计时间
     */
    private LocalDateTime countedAt;
    /**
     * 数据量
     */
    private Long dataVolume;

    public DaqTaskDataVolume() {
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

    public LocalDateTime getCountedAt() {
        return countedAt;
    }

    public void setCountedAt(LocalDateTime countedAt) {
        this.countedAt = countedAt;
    }

    public Long getDataVolume() {
        return dataVolume;
    }

    public void setDataVolume(Long dataVolume) {
        this.dataVolume = dataVolume;
    }
}
