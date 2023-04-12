package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 16:27
 * @Modified By:
 */
@Repository(value = "daqTaskRepository")
public interface DaqTaskRepository extends JpaRepository<DaqTask, Long>, JpaSpecificationExecutor<DaqTask> {

}
