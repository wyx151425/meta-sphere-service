package org.metasphere.adminservice.model.dto.scrapyd;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-22 19:27
 * @Modified By:
 */
public class ScrapydVersionsResp {
    private String status;
    private List<String> versions;
    private String message;

    public ScrapydVersionsResp() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
