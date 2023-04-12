package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqSpider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:58
 * @Modified By:
 */
@Repository(value = "daqSpiderRepository")
public interface DaqSpiderRepository extends JpaRepository<DaqSpider, Long>, JpaSpecificationExecutor<DaqSpider> {
}
