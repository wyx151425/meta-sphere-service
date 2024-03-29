package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 11:38
 * @Modified By:
 */
@Repository(value = "daqTaskKeywordRepository")
public interface DaqTaskKeywordRepository extends JpaRepository<DaqTaskKeyword, Long>, JpaSpecificationExecutor<DaqTaskKeyword> {
    /**
     * 根据数据采集任务ID查询数据采集关键词
     *
     * @param taskId 数据采集任务的ID
     * @return 数据采集关键词
     */
    List<DaqTaskKeyword> findAllByTaskId(Long taskId);

    /**
     * 根据数据采集任务ID查询已设置的关键词的数量
     *
     * @param daqTaskId 数据采集任务的ID
     * @return 已设置的关键词的数量
     */
    long countAllByTaskId(Long daqTaskId);
}
