package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQSpider;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:50
 * @Modified By:
 */
public interface DAQSpiderService {
    /**
     * 添加数据采集爬虫
     *
     * @param daqSpider 数据采集爬虫
     */
    void addDAQSpider(DAQSpider daqSpider);

    /**
     * 根据ID删除数据采集爬虫
     *
     * @param id 数据采集爬虫的ID
     */
    void deleteDAQSpiderById(Long id);

    /**
     * 获取所有的爬虫
     *
     * @return 爬虫数据
     */
    List<DAQSpider> findDAQSpiders();

    /**
     * 分页获取所有的爬虫
     *
     * @param pageNum  分页页码
     * @param pageSize 单页数据量
     * @return 爬虫数据
     */
    MSPage<DAQSpider> findDAQSpidersByPagination(Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取所有的爬虫
     *
     * @param ids
     * @return 爬虫数据
     */
    List<DAQSpider> findDAQSpidersByIds(List<Long> ids);
}
