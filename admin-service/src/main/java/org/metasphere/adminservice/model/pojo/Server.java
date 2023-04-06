package org.metasphere.adminservice.model.pojo;

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
public class Server extends MSEntity {
    private String host;
    private Integer port;
    private String name;
    private Integer type;

    public Server() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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
