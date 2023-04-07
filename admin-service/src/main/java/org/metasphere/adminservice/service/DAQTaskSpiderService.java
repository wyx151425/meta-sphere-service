package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.pojo.DAQTaskSpider;

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
     * @param daqTaskSpider 数据采集任务爬虫
     */
    void addDAQTaskSpider(DAQTaskSpider daqTaskSpider);
}
