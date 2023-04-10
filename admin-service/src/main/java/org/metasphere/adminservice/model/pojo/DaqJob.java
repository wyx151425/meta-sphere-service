package org.metasphere.adminservice.model.pojo;

import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集任务信息类
 * @Date: Created in 2023-02-21 19:06
 * @Modified By:
 */
public class DaqJob {
    /**
     * 任务在Scrapyd中的ID
     */
    private String id;
    /**
     * 任务所使用的爬虫的名字
     */
    private String spiderName;
    /**
     * 任务的开始时间
     */
    private LocalDateTime startTime;
    /**
     * 任务的结束时间
     */
    private LocalDateTime endTime;

    public DaqJob() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "DaqJob{" +
                "id='" + id + '\'' +
                ", spiderName='" + spiderName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
