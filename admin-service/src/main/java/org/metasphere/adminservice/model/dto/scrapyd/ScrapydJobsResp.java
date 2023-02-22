package org.metasphere.adminservice.model.dto.scrapyd;

import java.util.List;

public class ScrapydJobsResp {
    private String status;
    private List<ScrapydJob> pending;
    private List<ScrapydJob> running;
    private List<ScrapydJob> finished;
    private String message;

    public ScrapydJobsResp() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ScrapydJob> getPending() {
        return pending;
    }

    public void setPending(List<ScrapydJob> pending) {
        this.pending = pending;
    }

    public List<ScrapydJob> getRunning() {
        return running;
    }

    public void setRunning(List<ScrapydJob> running) {
        this.running = running;
    }

    public List<ScrapydJob> getFinished() {
        return finished;
    }

    public void setFinished(List<ScrapydJob> finished) {
        this.finished = finished;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
