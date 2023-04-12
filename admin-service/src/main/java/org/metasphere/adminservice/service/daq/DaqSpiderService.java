package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqSpider;

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
    void saveDaqSpider(DaqSpider daqSpider);

    /**
     * 根据ID删除数据采集爬虫
     *
     * @param id 数据采集爬虫的ID
     */
    void deleteDaqSpiderById(Long id);

    /**
     * 获取所有的爬虫
     *
     * @return 爬虫数据
     */
    List<DaqSpider> findDaqSpiders();

    /**
     * 分页获取所有的爬虫
     *
     * @param pageNum  分页页码
     * @param pageSize 单页数据量
     * @return 爬虫数据
     */
    MsPage<DaqSpider> findDaqSpidersByPagination(Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取所有的爬虫
     *
     * @param ids 爬虫的ID
     * @return 爬虫数据
     */
    List<DaqSpider> findDaqSpidersByIds(List<Long> ids);
}
