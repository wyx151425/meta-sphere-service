package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description: 系统服务器数据访问层接口
 * @Date: Created in 2023-03-07 20:21
 * @Modified By:
 */
@Repository(value = "serverRepository")
public interface ServerRepository extends JpaRepository<Server, Long>, JpaSpecificationExecutor<Server> {
}
