package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskServer;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集业务爬虫业务逻辑规范
 * @Date: Created in 2023-04-07 21:19
 * @Modified By:
 */
public interface DaqTaskSpiderService {
    /**
     * 添加数据采集任务爬虫
     *
     * @param daqTask        数据采集任务
     * @param daqTaskServers 数据采集服务器
     * @param daqSpiderIds   爬虫ID
     */
    void saveDaqTaskSpiders(DaqTask daqTask, List<DaqTaskServer> daqTaskServers, List<Long> daqSpiderIds);

    /**
     * 更新数据采集任务爬虫
     *
     * @param taskSpider 数据采集任务爬虫
     */
    void updateDaqTaskSpider(DaqTaskSpider taskSpider);

    /**
     * 根据数据采集任务获取数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫
     */
    List<DaqTaskSpider> findDaqTaskSpiders(Long daqTaskId);

    /**
     * 根据数据采集任务获取数据采集任务爬虫的编码
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫的编码
     */
    List<Long> findDaqSpiderIdsByTaskId(Long daqTaskId);

    /**
     * 分页获取数据采集任务下的爬虫
     *
     * @param daqTaskId 数据采集任务的ID
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     * @return 数据采集任务爬虫
     */
    MsPage<DaqTaskSpider> findDaqTaskSpidersByPagination(Long daqTaskId, Integer pageNum, Integer pageSize);
}
