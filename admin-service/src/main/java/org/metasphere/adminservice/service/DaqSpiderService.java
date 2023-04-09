package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DaqSpider;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:50
 * @Modified By:
 */
public interface DaqSpiderService {
    /**
     * 添加数据采集爬虫
     *
     * @param daqSpider 数据采集爬虫
     */
    void addDAQSpider(DaqSpider daqSpider);

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
    List<DaqSpider> findDAQSpiders();

    /**
     * 分页获取所有的爬虫
     *
     * @param pageNum  分页页码
     * @param pageSize 单页数据量
     * @return 爬虫数据
     */
    MSPage<DaqSpider> findDAQSpidersByPagination(Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取所有的爬虫
     *
     * @param ids
     * @return 爬虫数据
     */
    List<DaqSpider> findDAQSpidersByIds(List<Long> ids);
}
