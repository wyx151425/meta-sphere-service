package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * 根据数据采集任务ID删除所有数据采集任务服务器
     *
     * @param daqTaskId 数据采集任务ID
     */

    @Modifying
    @Query(value = "update DaqTaskServer set status = 0, updateAt = current_time where status = 1 and taskId = :daqTaskId")
    void deleteAllByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID查询数据采集服务器
     *
     * @param daqTaskId 数据采集任务ID
     * @return 数据采集服务器
     */
    DaqTaskServer findOneByTaskId(Long daqTaskId);

    /**
     * 根据数据采集任务ID查询已绑定的数据采集服务器的数量
     *
     * @param daqTaskId 数据采集任务的ID
     * @return 已绑定的数据采集服务器的数量
     */
    long countAllByTaskId(Long daqTaskId);
}
