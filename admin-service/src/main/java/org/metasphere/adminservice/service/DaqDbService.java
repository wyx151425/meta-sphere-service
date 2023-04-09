package org.metasphere.adminservice.service;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:41
 * @Modified By:
 */
public interface DaqDbService {
    /**
     * 创建数据采集任务的数据表
     *
     * @param tableName 表名
     */
    void createDaqDataTable(String tableName);
}
