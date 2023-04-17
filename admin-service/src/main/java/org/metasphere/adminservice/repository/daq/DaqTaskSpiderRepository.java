package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:21
 * @Modified By:
 */
@Repository(value = "daqTaskSpiderRepository")
public interface DaqTaskSpiderRepository extends JpaRepository<DaqTaskSpider, Long>, JpaSpecificationExecutor<DaqTaskSpider> {
    /**
     * 根据数据采集任务ID删除数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     */
    void deleteAllByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID获取数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫
     */
    List<DaqTaskSpider> findAllByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID获取数据采集任务爬虫的编码
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫的编码
     */
    @Query(value = "select distinct spiderId from DaqTaskSpider where taskId = :daqTaskId")
    List<Long> findDaqSpiderIdsByTaskId(Long daqTaskId);
}
