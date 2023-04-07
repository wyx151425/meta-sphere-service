package org.metasphere.adminservice.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 17:24
 * @Modified By:
 */
@Entity
@Table(name = "ms_daq_spider")
public class DAQSpider extends MSEntity {
    /**
     * 爬虫的名称
     */
    private String name;
    /**
     * 爬虫编号
     */
    private String code;
    /**
     * 爬虫的Cookie信息
     */
    private String cookie;
    /**
     * 爬虫用于检索的URL
     */
    private String searchUrl;

    public DAQSpider() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }
}
