package org.metasphere.adminservice.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-29 11:16
 * @Modified By:
 */
@Entity
@Table(name = "ms_hot_event")
public class HotEvent extends MetaSphereEntity {
    /**
     * 热点事件名称
     */
    private String name;
    /**
     * 热点事件简介
     */
    private String introduction;
    /**
     * 开始日期
     */
    private LocalDate startDate;
    /**
     * 结束日期
     */
    private LocalDate endDate;
    /**
     * 关联的数据采集任务编号
     */
    private String daqTaskCode;

    public HotEvent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDaqTaskCode() {
        return daqTaskCode;
    }

    public void setDaqTaskCode(String daqTaskCode) {
        this.daqTaskCode = daqTaskCode;
    }
}
