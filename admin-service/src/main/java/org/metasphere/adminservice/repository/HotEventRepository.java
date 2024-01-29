package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.HotEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-29 14:11
 * @Modified By:
 */
public interface HotEventRepository extends JpaRepository<HotEvent, Long> {
}
