package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.bo.daq.DaqEngineWeiboItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    void createDaqTaskDataTable(String tableName);

    /**
     * 保存微博数据
     *
     * @param tableName          表名
     * @param daqEngineWeiboItem 微博数据
     */
    void saveDaqEngineWeiboItem(String tableName, DaqEngineWeiboItem daqEngineWeiboItem);

    /**
     * 保存微博数据
     *
     * @param tableName           表名
     * @param daqEngineWeiboItems 微博数据
     */
    void saveDaqEngineWeiboItems(String tableName, List<DaqEngineWeiboItem> daqEngineWeiboItems);

    /**
     * 分页获取数据采集任务的数据
     *
     * @param tableName 数据采集任务表名
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     */
    void findDaqTaskDataByPagination(String tableName, Integer pageNum, Integer pageSize);
}
