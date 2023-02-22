package org.metasphere.adminservice.model.pojo;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 17:22
 * @Modified By:
 */
public class MSServer extends MSEntity {
    private String host;
    private Integer port;
    private String name;
    private String type;

    public MSServer() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
