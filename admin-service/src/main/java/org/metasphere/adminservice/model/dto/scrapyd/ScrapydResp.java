package org.metasphere.adminservice.model.dto.scrapyd;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 21:22
 * @Modified By:
 */
public class ScrapydResp {
    private String status;
    private String message;
    private String node_name;
    private Integer pending;
    private Integer running;
    private Integer finished;
    private String jobid;
    private Integer spiders;
    private String prevstate;

    public ScrapydResp() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getRunning() {
        return running;
    }

    public void setRunning(Integer running) {
        this.running = running;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public Integer getSpiders() {
        return spiders;
    }

    public void setSpiders(Integer spiders) {
        this.spiders = spiders;
    }

    public String getPrevstate() {
        return prevstate;
    }

    public void setPrevstate(String prevstate) {
        this.prevstate = prevstate;
    }

    @Override
    public String toString() {
        return "ScrapydRespParams{" +
                "status='" + status + '\'' +
                ", node_name='" + node_name + '\'' +
                ", pending=" + pending +
                ", running=" + running +
                ", finished=" + finished +
                ", jobid='" + jobid + '\'' +
                ", spiders='" + spiders + '\'' +
                ", prevstate='" + prevstate + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
