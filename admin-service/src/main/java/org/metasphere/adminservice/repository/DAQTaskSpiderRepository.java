package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.DAQTaskSpider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:21
 * @Modified By:
 */
@Repository(value = "daqTaskSpiderRepository")
public interface DAQTaskSpiderRepository extends JpaRepository<DAQTaskSpider, Long> {
}
