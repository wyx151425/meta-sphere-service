package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqDataVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 10:50
 * @Modified By:
 */
@Repository(value = "daqDataVolumeRepository")
public interface DaqDataVolumeRepository extends JpaRepository<DaqDataVolume, Long>, JpaSpecificationExecutor<DaqDataVolume> {

    /**
     * 根据数据采集任务ID查询数据量
     *
     * @param taskCode 数据采集任务编码
     * @return 数据采集任务数据量
     */
    List<DaqDataVolume> findAllByTaskCode(String taskCode);

    /**
     * 根据数据采集任务编码和爬虫编码按统计时间升序获取数据量
     *
     * @param taskCode   数据采集任务编码
     * @param spiderCode 数据采集任务爬虫编码
     * @return 数据采集任务数据量
     */
    List<DaqDataVolume> findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(String taskCode, String spiderCode);
}
