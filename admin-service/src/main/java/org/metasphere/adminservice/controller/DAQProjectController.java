package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQProject;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.DAQProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 20:35
 * @Modified By:
 */
@RestController
@RequestMapping(value = "daqProjects")
public class DAQProjectController {

    @Autowired
    private DAQProjectService daqProjectService;

    @PostMapping(value = "")
    MSResponse<DAQProject> actionCreateDAQProject(@RequestBody DAQProject daqProject) {
        daqProjectService.createDAQProject(daqProject);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByParams")
    MSResponse<MSPage<DAQProject>> actionQueryDAQProjectsByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MSPage<DAQProject> page = daqProjectService.findDAQProjectByParams(pageNum, pageSize, stage);
        return MSResponse.success(page);
    }
}
