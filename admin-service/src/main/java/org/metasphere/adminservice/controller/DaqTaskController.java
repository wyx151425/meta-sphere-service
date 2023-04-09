package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DaqTask;
import org.metasphere.adminservice.model.pojo.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.DaqTaskSpider;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.DaqTaskKeywordService;
import org.metasphere.adminservice.service.DaqTaskService;
import org.metasphere.adminservice.service.DaqTaskSpiderService;
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
public class DaqTaskController {

    private final DaqTaskService daqTaskService;

    @Autowired
    public DaqTaskController(DaqTaskService daqTaskService, DaqTaskKeywordService daqTaskKeywordService, DaqTaskSpiderService daqTaskSpiderService) {
        this.daqTaskService = daqTaskService;
    }

    @PostMapping(value = "")
    MSResponse<DaqTask> actionCreateDAQTask(@RequestBody DaqTask daqTask) {
        daqTaskService.createDaqTask(daqTask);
        return MSResponse.success();
    }

    @DeleteMapping(value = "{id}")
    MSResponse<DaqTask> actionDeleteDAQTask(@PathVariable(value = "id") Long id) {
        daqTaskService.deleteDaqTask(id);
        return MSResponse.success();
    }

    @PutMapping(value = "{id}/start")
    MSResponse<DaqTask> actionStartDAQTask(@PathVariable(value = "id") Long id) {
        daqTaskService.startDaqTask(id);
        return MSResponse.success();
    }


    @GetMapping(value = "queryByParams")
    MSResponse<MSPage<DaqTask>> actionQueryDAQTasksByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MSPage<DaqTask> page = daqTaskService.findDaqTasksByParams(pageNum, pageSize, stage);
        return MSResponse.success(page);
    }

    @PostMapping(value = "{daqTaskId}/daqTaskSpiders")
    MSResponse<DaqTask> actionAddDAQTaskSpiders(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> daqSpiderIds
    ) {
        daqTaskService.addDaqTaskSpiders(daqTaskId, daqSpiderIds);
        return MSResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/daqTaskSpiders/queryAll")
    MSResponse<List<DaqTaskSpider>> actionQueryDAQTaskSpidersByDAQTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        List<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpidersByDaqTask(daqTaskId);
        return MSResponse.success(daqTaskSpiders);
    }

    @GetMapping(value = "{daqTaskId}/daqTaskSpiders/queryByPagination")
    MSResponse<MSPage<DaqTaskSpider>> actionQueryDAQTaskSpidersByDAQTaskAndPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpidersByDaqTaskAndPagination(daqTaskId, pageNum, pageSize);
        return MSResponse.success(daqTaskSpiders);
    }

    @PostMapping(value = "{daqTaskId}/daqTaskKeywords")
    MSResponse<DaqTask> actionAddDAQTaskKeywords(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<String> keywords
    ) {
        daqTaskService.addDaqTaskKeywords(daqTaskId, keywords);
        return MSResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/daqTaskKeywords/queryByPagination")
    MSResponse<MSPage<DaqTaskKeyword>> actionQueryDAQTaskKeywordsByDAQTaskAndPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DaqTaskKeyword> page = daqTaskService.findDaqTaskKeywordsByDaqTaskAndPagination(daqTaskId, pageNum, pageSize);
        return MSResponse.success(page);
    }
}
