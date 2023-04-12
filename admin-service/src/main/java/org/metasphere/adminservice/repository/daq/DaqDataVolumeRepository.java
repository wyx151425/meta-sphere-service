package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqDataVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 10:50
 * @Modified By:
 */
@Repository(value = "daqDataVolumeRepository")
public interface DaqDataVolumeRepository extends JpaRepository<DaqDataVolume, Long>, JpaSpecificationExecutor<DaqDataVolume> {
}
