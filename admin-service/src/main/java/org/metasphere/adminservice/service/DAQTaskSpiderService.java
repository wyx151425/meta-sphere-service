package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskSpider;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集业务爬虫业务逻辑规范
 * @Date: Created in 2023-04-07 21:19
 * @Modified By:
 */
public interface DAQTaskSpiderService {
    /**
     * 添加数据采集任务爬虫
     *
     * @param daqTask      数据采集任务
     * @param daqSpiderIds 爬虫ID
     */
    void addDAQTaskSpiders(DAQTask daqTask, List<Long> daqSpiderIds);

    /**
     * 根据数据采集任务获取数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫
     */
    List<DAQTaskSpider> findAllDAQTaskSpidersByDAQTask(Long daqTaskId);

    /**
     * 分页获取数据采集任务下的爬虫
     *
     * @param daqTaskId 数据采集任务的ID
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     * @return 数据采集任务爬虫
     */
    MSPage<DAQTaskSpider> findDAQTaskSpidersByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize);
}