package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.DAQProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 16:27
 * @Modified By:
 */
@Repository(value = "daqProjectRepository")
public interface DAQProjectRepository extends JpaRepository<DAQProject, Long>, JpaSpecificationExecutor<DAQProject> {

}
