package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.metasphere.adminservice.model.pojo.DAQTaskSpider;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.DAQTaskKeywordService;
import org.metasphere.adminservice.service.DAQTaskService;
import org.metasphere.adminservice.service.DAQTaskSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 20:35
 * @Modified By:
 */
@RestController
@RequestMapping(value = "daqTasks")
public class DAQTaskController {

    private final DAQTaskService daqTaskService;

    @Autowired
    public DAQTaskController(DAQTaskService daqTaskService, DAQTaskKeywordService daqTaskKeywordService, DAQTaskSpiderService daqTaskSpiderService) {
        this.daqTaskService = daqTaskService;
    }

    @PostMapping(value = "")
    MSResponse<DAQTask> actionCreateDAQTask(@RequestBody DAQTask daqTask) {
        daqTaskService.createDAQTask(daqTask);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByParams")
    MSResponse<MSPage<DAQTask>> actionQueryDAQTasksByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MSPage<DAQTask> page = daqTaskService.findDAQTasksByParams(pageNum, pageSize, stage);
        return MSResponse.success(page);
    }

    @PostMapping(value = "{daqTaskId}/daqTaskSpiders")
    MSResponse<DAQTask> actionAddDAQTaskSpiders(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> daqSpiderIds
    ) {
        daqTaskService.addDAQTaskSpiders(daqTaskId, daqSpiderIds);
        return MSResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/daqTaskSpiders/queryAll")
    MSResponse<List<DAQTaskSpider>> actionQueryDAQTaskSpidersByDAQTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        List<DAQTaskSpider> daqTaskSpiders = daqTaskService.findDAQTaskSpidersByDAQTask(daqTaskId);
        return MSResponse.success(daqTaskSpiders);
    }

    @GetMapping(value = "{daqTaskId}/daqTaskSpiders/queryByPagination")
    MSResponse<MSPage<DAQTaskSpider>> actionQueryDAQTaskSpidersByDAQTaskAndPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DAQTaskSpider> daqTaskSpiders = daqTaskService.findDAQTaskSpidersByDAQTaskAndPagination(daqTaskId, pageNum, pageSize);
        return MSResponse.success(daqTaskSpiders);
    }

    @PostMapping(value = "{daqTaskId}/daqTaskKeywords")
    MSResponse<DAQTask> actionAddDAQTaskKeywords(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<String> keywords
    ) {
        daqTaskService.addDAQTaskKeywords(daqTaskId, keywords);
        return MSResponse.success();
    }

    @PostMapping(value = "{daqTaskId}/daqTaskKeywords/queryByPagination")
    MSResponse<MSPage<DAQTaskKeyword>> actionQueryDAQTaskKeywordsByDAQTaskAndPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DAQTaskKeyword> page = daqTaskService.findDAQTaskKeywordsByDAQTaskAndPagination(daqTaskId, pageNum, pageSize);
        return MSResponse.success(page);
    }
}
