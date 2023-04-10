package org.metasphere.adminservice.model.pojo;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 17:22
 * @Modified By:
 */
@Entity
@Table(name = "ms_server")
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_server SET status = 0 WHERE id = ?")
public class Server extends MetaSphereEntity {
    /**
     * 主机IP
     */
    private String ipAddress;
    /**
     * 主机名称
     */
    private String name;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 服务器类型
     */
    private Integer type;

    public Server() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String host) {
        this.ipAddress = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
