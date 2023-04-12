package org.metasphere.adminservice.model.pojo.daq;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

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
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_daq_spider SET status = 0 WHERE id = ?")
public class DaqSpider extends MetaSphereEntity {
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
    private String cookies;

    public DaqSpider() {
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

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookie) {
        this.cookies = cookie;
    }
}
