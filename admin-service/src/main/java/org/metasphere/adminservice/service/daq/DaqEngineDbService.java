package org.metasphere.adminservice.service.daq;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:41
 * @Modified By:
 */
public interface DaqEngineDbService {
    /**
     * 创建数据采集任务的数据表
     *
     * @param tableName 表名
     */
    void createDaqDataTable(String tableName);
}
