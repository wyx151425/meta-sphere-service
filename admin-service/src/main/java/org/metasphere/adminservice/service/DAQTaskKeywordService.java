package org.metasphere.adminservice.service;

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
     * @param daqTaskId 所属数据采集任务
     * @param keywords  关键词
     */
    void addDAQTaskKeywords(Long daqTaskId, List<String> keywords);

    /**
     * 根据数据采集项目查询数据采集关键词
     *
     * @param daqProjectId 数据采集项目的ID
     * @return 数据采集关键词
     */
    List<DAQTaskKeyword> findDAQKeywordsByDAQProject(Long daqProjectId);
}
