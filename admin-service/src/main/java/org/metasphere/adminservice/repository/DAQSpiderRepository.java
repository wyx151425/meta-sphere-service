package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.DaqSpider;
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
public interface DAQSpiderRepository extends JpaRepository<DaqSpider, Long>, JpaSpecificationExecutor<DaqSpider> {
}
