package org.metasphere.adminservice.model.pojo;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集任务信息汇总类
 * @Date: Created in 2023-02-21 19:08
 * @Modified By:
 */
public class DAQJobs {
    /**
     * 正在等待加入任务队列的数据采集任务
     */
    private List<DAQJob> pendingJobs;

    /**
     * 正在运行的数据采集任务
     */
    private List<DAQJob> runningJobs;

    /**
     * 已经运行完毕的数据采集任务
     */
    private List<DAQJob> finishedJobs;

    public DAQJobs() {
    }

    public List<DAQJob> getPendingJobs() {
        return pendingJobs;
    }

    public void setPendingJobs(List<DAQJob> pendingJobs) {
        this.pendingJobs = pendingJobs;
    }

    public List<DAQJob> getRunningJobs() {
        return runningJobs;
    }

    public void setRunningJobs(List<DAQJob> runningJobs) {
        this.runningJobs = runningJobs;
    }

    public List<DAQJob> getFinishedJobs() {
        return finishedJobs;
    }

    public void setFinishedJobs(List<DAQJob> finishedJobs) {
        this.finishedJobs = finishedJobs;
    }

    @Override
    public String toString() {
        return "DAQJobs{" +
                "pendingJobs=" + pendingJobs +
                ", runningJobs=" + runningJobs +
                ", finishedJobs=" + finishedJobs +
                '}';
    }
}
