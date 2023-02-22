package org.metasphere.adminservice.model.dto.scrapyd;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-22 19:26
 * @Modified By:
 */
public class ScrapydProjectsResp {
    private String status;
    private List<String> projects;
    private String message;

    public ScrapydProjectsResp() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
