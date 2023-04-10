package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.DaqTaskServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-10 16:37
 * @Modified By:
 */
@Repository(value = "daqTaskServerRepository")
public interface DaqTaskServerRepository extends JpaRepository<DaqTaskServer, Long> {
    /**
     * 根据数据采集任务ID获取数据采集服务器
     *
     * @param daqTaskId 数据采集任务的ID
     * @return 属于该任务的服务器
     */
    List<DaqTaskServer> findAllByTaskId(Long daqTaskId);
}
