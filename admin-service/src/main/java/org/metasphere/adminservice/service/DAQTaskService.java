package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.metasphere.adminservice.model.pojo.DAQTaskSpider;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集业务逻辑规范
 * @Date: Created in 2023-02-21 16:57
 * @Modified By:
 */
public interface DAQTaskService {
    /**
     * 创建数据采集项目
     *
     * @param daqTask 数据采集项目信息
     */
    void createDAQTask(DAQTask daqTask);

    /**
     * 开始数据采集任务
     *
     * @param daqProjectId 数据采集任务的ID
     */
    void startDataAcquiring(Long daqProjectId);

    /**
     * 根据查询参数分页查询数据采集项目信息
     *
     * @param pageNum  页码
     * @param pageSize 单页数据量
     * @return 查询到的数据采集项目信息
     */
    MSPage<DAQTask> findDAQTasksByParams(Integer pageNum, Integer pageSize, Integer stage);

    /**
     * 添加数据采集任务关键词
     *
     * @param daqTaskId 数据采集任务的ID
     * @param keywords  数据采集任务的关键词
     */
    void addDAQTaskKeywords(Long daqTaskId, List<String> keywords);

    /**
     * 添加数据采集任务爬虫
     *
     * @param daqTaskId    数据采集任务ID
     * @param daqSpiderIds 爬虫的ID
     */
    void addDAQTaskSpiders(Long daqTaskId, List<Long> daqSpiderIds);

    /**
     * 根据数据采集任务获取数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫
     */
    List<DAQTaskSpider> findDAQTaskSpidersByDAQTask(Long daqTaskId);

    /**
     * 分页获取数据采集任务下的爬虫
     *
     * @param daqTaskId 数据采集任务的ID
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     * @return 数据采集任务爬虫
     */
    MSPage<DAQTaskSpider> findDAQTaskSpidersByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize);

    /**
     * 分页获取数据采集任务下的关键词
     *
     * @param daqTaskId 数据采集任务的ID
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     * @return 数据采集任务关键词
     */
    MSPage<DAQTaskKeyword> findDAQTaskKeywordsByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize);
}
