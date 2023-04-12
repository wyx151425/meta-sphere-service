package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集业务逻辑规范
 * @Date: Created in 2023-02-21 16:57
 * @Modified By:
 */
public interface DaqTaskService {
    /**
     * 创建数据采集任务
     *
     * @param daqTask 数据采集任务信息
     */
    void createDaqTask(DaqTask daqTask);

    /**
     * 删除数据采集任务（在任务启动前允许删除）
     *
     * @param daqTaskId 数据采集任务的ID
     */
    void deleteDaqTask(Long daqTaskId);

    /**
     * 启动数据采集任务（启动后将创建数据存储空间，并允许配置爬虫和关键词）
     *
     * @param daqTaskId 数据采集任务的ID
     */
    void startDaqTask(Long daqTaskId);

    /**
     * 开始数据采集任务
     *
     * @param daqTaskId 数据采集任务的ID
     */
    void executeDaqTask(Long daqTaskId);

    /**
     * 根据查询参数分页查询数据采集任务信息
     *
     * @param pageNum  页码
     * @param pageSize 单页数据量
     * @param stage    任务运行阶段
     * @return 查询到的数据采集任务信息
     */
    MsPage<DaqTask> findDaqTasksByParams(Integer pageNum, Integer pageSize, Integer stage);

    /**
     * 添加数据采集任务关键词
     *
     * @param daqTaskId 数据采集任务的ID
     * @param keywords  数据采集任务的关键词
     */
    void addDaqTaskKeywords(Long daqTaskId, List<String> keywords);

    /**
     * 添加数据采集任务爬虫
     *
     * @param daqTaskId    数据采集任务ID
     * @param daqSpiderIds 爬虫的ID
     */
    void addDaqTaskSpiders(Long daqTaskId, List<Long> daqSpiderIds);

    /**
     * 根据数据采集任务获取数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫
     */
    List<DaqTaskSpider> findDaqTaskSpiders(Long daqTaskId);

    /**
     * 分页获取数据采集任务下的爬虫
     *
     * @param daqTaskId 数据采集任务的ID
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     * @return 数据采集任务爬虫
     */
    MsPage<DaqTaskSpider> findDaqTaskSpidersByPagination(Long daqTaskId, Integer pageNum, Integer pageSize);

    /**
     * 分页获取数据采集任务下的关键词
     *
     * @param daqTaskId 数据采集任务的ID
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     * @return 数据采集任务关键词
     */
    MsPage<DaqTaskKeyword> findDaqTaskKeywordsByPagination(Long daqTaskId, Integer pageNum, Integer pageSize);

    /**
     * 绑定数据采集任务服务器
     *
     * @param daqTaskId 数据采集任务ID
     * @param serverId  服务器ID
     */
    void bindDaqTaskServer(Long daqTaskId, Long serverId);
}