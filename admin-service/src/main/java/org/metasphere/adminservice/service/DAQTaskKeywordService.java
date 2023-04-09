package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集项目关键词业务逻辑规范
 * @Date: Created in 2023-04-07 14:46
 * @Modified By:
 */
public interface DAQTaskKeywordService {
    /**
     * 添加数据采集任务的关键词
     *
     * @param daqTask  数据采集任务
     * @param keywords 关键词
     */
    void addDAQTaskKeywords(DAQTask daqTask, List<String> keywords);

    /**
     * 删除数据采集任务关键词
     *
     * @param id 关键词的ID
     */
    void deleteDAQTaskKeyword(Long id);

    /**
     * 批量删除数据采集任务关键词
     *
     * @param ids 关键词ID
     */
    void batchDeleteDAQTaskKeywords(List<Long> ids);

    /**
     * 根据数据采集项目查询数据采集关键词
     *
     * @param daqTaskId 数据采集项目的ID
     * @return 数据采集关键词
     */
    List<DAQTaskKeyword> findDAQTaskKeywordsByDAQTask(Long daqTaskId);

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
