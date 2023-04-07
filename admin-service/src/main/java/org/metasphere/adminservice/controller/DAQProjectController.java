package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.DAQTaskService;
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
    private DAQTaskService daqTaskService;

    @PostMapping(value = "")
    MSResponse<DAQTask> actionCreateDAQProject(@RequestBody DAQTask daqTask) {
        daqTaskService.createDAQTask(daqTask);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByParams")
    MSResponse<MSPage<DAQTask>> actionQueryDAQProjectsByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MSPage<DAQTask> page = daqTaskService.findDAQTasksByParams(pageNum, pageSize, stage);
        return MSResponse.success(page);
    }


}
