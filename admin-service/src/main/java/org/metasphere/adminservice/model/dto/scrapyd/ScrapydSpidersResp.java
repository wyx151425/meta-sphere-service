package org.metasphere.adminservice.model.dto.scrapyd;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-22 19:27
 * @Modified By:
 */
public class ScrapydSpidersResp {
    private String status;
    private List<String> spiders;
    private String message;

    public ScrapydSpidersResp() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSpiders() {
        return spiders;
    }

    public void setSpiders(List<String> spiders) {
        this.spiders = spiders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
