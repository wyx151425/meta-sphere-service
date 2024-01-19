package org.metasphere.portalservice.repository;

import org.metasphere.portalservice.model.pojo.deduction.DeductionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description: 推演室数据仓库
 * @Date: Created in 2024-01-19 15:29
 * @Modified By:
 */
@Repository
public interface DeductionGroupRepository extends JpaRepository<DeductionGroup, Long> {
}
