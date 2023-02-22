package org.metasphere.adminservice.model.dto.scrapyd;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-22 19:16
 * @Modified By:
 */
public class ScrapydJob {
    private String id;
    private String spider;
    private String start_time;
    private String end_time;

    public ScrapydJob() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpider() {
        return spider;
    }

    public void setSpider(String spider) {
        this.spider = spider;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
