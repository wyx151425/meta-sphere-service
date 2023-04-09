package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.DAQTaskSpider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:21
 * @Modified By:
 */
@Repository(value = "daqTaskSpiderRepository")
public interface DAQTaskSpiderRepository extends JpaRepository<DAQTaskSpider, Long>, JpaSpecificationExecutor<DAQTaskSpider> {
    /**
     * 根据数据采集任务ID删除数据采集任务爬虫
     *
     * @param daqTaskId
     */
    void deleteAllByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID获取数据采集任务爬虫
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集任务爬虫
     */
    List<DAQTaskSpider> findAllByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID和爬虫ID获取数据采集任务爬虫
     *
     * @param daqTaskId    数据采集任务ID
     * @param daqSpiderIds 爬虫ID
     * @return 数据采集任务爬虫
     */
    List<DAQTaskSpider> findAllByTaskIdAndSpiderIdIsIn(Long daqTaskId, List<Long> daqSpiderIds);
}
