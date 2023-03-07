package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
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
     * @param server 服务器数据对象
     */
    void saveServer(Server server);

    /**
     * 分页查询服务器信息
     * @param pageNum 分页页码
     * @param pageSize 单页数据量
     * @return 查询到的服务器信息
     */
    MSPage<Server> findServersByPagination(Integer pageNum, Integer pageSize);
}
