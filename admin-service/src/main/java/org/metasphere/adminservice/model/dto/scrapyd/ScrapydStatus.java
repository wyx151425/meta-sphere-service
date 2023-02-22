package org.metasphere.adminservice.model.dto.scrapyd;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 17:12
 * @Modified By:
 */
public class ScrapydStatus {
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 正在等待加入任务队列的任务的数量
     */
    private Integer pendingJobsCount;
    /**
     * 正在运行的任务的数量
     */
    private Integer runningJobsCount;
    /**
     * 已经完成的任务的数量
     */
    private Integer finishedJobsCount;

    public ScrapydStatus() {
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getPendingJobsCount() {
        return pendingJobsCount;
    }

    public void setPendingJobsCount(Integer pendingJobsCount) {
        this.pendingJobsCount = pendingJobsCount;
    }

    public Integer getRunningJobsCount() {
        return runningJobsCount;
    }

    public void setRunningJobsCount(Integer runningJobsCount) {
        this.runningJobsCount = runningJobsCount;
    }

    public Integer getFinishedJobsCount() {
        return finishedJobsCount;
    }

    public void setFinishedJobsCount(Integer finishedJobsCount) {
        this.finishedJobsCount = finishedJobsCount;
    }

    @Override
    public String toString() {
        return "ScrapydStatus{" +
                "nodeName='" + nodeName + '\'' +
                ", pendingJobsCount=" + pendingJobsCount +
                ", runningJobsCount=" + runningJobsCount +
                ", finishedJobsCount=" + finishedJobsCount +
                '}';
    }
}
