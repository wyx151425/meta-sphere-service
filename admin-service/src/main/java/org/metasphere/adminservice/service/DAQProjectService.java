package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQProject;
import org.springframework.stereotype.Service;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集业务逻辑规范
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
public interface DAQProjectService {
    /**
     * 创建数据采集项目
     *
     * @param daqProject 数据采集项目信息
     */
    void createDAQProject(DAQProject daqProject);

    /**
     * 根据查询参数分页查询数据采集项目信息
     *
     * @param pageNum  页码
     * @param pageSize 单页数据量
     * @return 查询到的数据采集项目信息
     */
    MSPage<DAQProject> findDAQProjectByParams(Integer pageNum, Integer pageSize, Integer stage);
}
