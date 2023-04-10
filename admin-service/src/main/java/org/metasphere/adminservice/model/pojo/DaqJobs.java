package org.metasphere.adminservice.model.pojo;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集任务信息汇总类
 * @Date: Created in 2023-02-21 19:08
 * @Modified By:
 */
public class DaqJobs {
    /**
     * 正在等待加入任务队列的数据采集任务
     */
    private List<DaqJob> pendingJobs;

    /**
     * 正在运行的数据采集任务
     */
    private List<DaqJob> runningJobs;

    /**
     * 已经运行完毕的数据采集任务
     */
    private List<DaqJob> finishedJobs;

    public DaqJobs() {
    }

    public List<DaqJob> getPendingJobs() {
        return pendingJobs;
    }

    public void setPendingJobs(List<DaqJob> pendingJobs) {
        this.pendingJobs = pendingJobs;
    }

    public List<DaqJob> getRunningJobs() {
        return runningJobs;
    }

    public void setRunningJobs(List<DaqJob> runningJobs) {
        this.runningJobs = runningJobs;
    }

    public List<DaqJob> getFinishedJobs() {
        return finishedJobs;
    }

    public void setFinishedJobs(List<DaqJob> finishedJobs) {
        this.finishedJobs = finishedJobs;
    }

    @Override
    public String toString() {
        return "DaqJobs{" +
                "pendingJobs=" + pendingJobs +
                ", runningJobs=" + runningJobs +
                ", finishedJobs=" + finishedJobs +
                '}';
    }
}
