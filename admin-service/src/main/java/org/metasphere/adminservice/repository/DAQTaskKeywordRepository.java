package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 11:38
 * @Modified By:
 */
@Repository(value = "daqTaskKeywordRepository")
public interface DAQTaskKeywordRepository extends JpaRepository<DAQTaskKeyword, Long> {
    /**
     * 根据数据采集项目ID查询数据采集关键词
     *
     * @param projectId 数据采集项目的ID
     * @return 数据采集关键词
     */
    List<DAQTaskKeyword> findAllByProjectId(Long projectId);
}
