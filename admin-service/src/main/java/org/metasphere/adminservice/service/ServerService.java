package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.Server;

/**
 * @Author: WangZhenqi
 * @Description: 系统服务器业务规范
 * @Date: Created in 2023-02-21 17:40
 * @Modified By:
 */
public interface ServerService {

    /**
     * 保存服务器信息
     *
     * @param server 服务器数据对象
     */
    void saveServer(Server server);

    /**
     * 分页查询服务器信息
     *
     * @param pageNum  分页页码
     * @param pageSize 单页数据量
     * @return 查询到的服务器信息
     */
    MsPage<Server> findServersByPagination(Integer pageNum, Integer pageSize);

    /**
     * 检查服务器的状态（能否PING通）
     *
     * @param host 主机号
     * @return 状态代码
     */
    Integer checkServerStatus(String host);

    /**
     * 检查服务器状态（检查IP和端口能否连通）
     *
     * @param host 主机号
     * @param port 端口号
     * @return 状态代码
     */
    Integer checkServerStatus(String host, Integer port);
}
