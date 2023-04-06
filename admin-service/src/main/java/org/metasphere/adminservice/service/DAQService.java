package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.pojo.DAQProject;
import org.springframework.stereotype.Service;

/**
 * @Author: WangZhenqi
 * @Description: 数据采集业务逻辑规范
 * @Date: Created in 2022-11-10 20:13
 * @Modified By:
 */
@Service(value = "daqService")
public interface DAQService {
    void createDAQProject(DAQProject daqProject);
}
