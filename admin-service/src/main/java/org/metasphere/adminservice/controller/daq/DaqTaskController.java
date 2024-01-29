package org.metasphere.adminservice.controller.daq;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.daq.DaqSpider;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;
import org.metasphere.adminservice.model.vo.DaqTaskTimingDataVolumes;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.daq.DaqTaskService;
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
    public DaqTaskController(DaqTaskService daqTaskService) {
        this.daqTaskService = daqTaskService;
    }

    @PostMapping(value = "")
    MSResponse<DaqTask> actionCreateDaqTask(@RequestBody DaqTask daqTask) {
        daqTaskService.createDaqTask(daqTask);
        return MSResponse.success();
    }

    @DeleteMapping(value = "{daqTaskId}")
    MSResponse<DaqTask> actionDeleteDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.deleteDaqTask(daqTaskId);
        return MSResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/submit")
    MSResponse<DaqTask> actionInitDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.submitDaqTask(daqTaskId);
        return MSResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/execute")
    MSResponse<DaqTask> actionPerformDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.executeDaqTask(daqTaskId);
        return MSResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/stopExecuting")
    MSResponse<DaqTask> actionStopPerformingDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.stopDaqTask(daqTaskId);
        return MSResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/importAcquiredData")
    MSResponse<DaqTask> actionEnterDaqTaskAcquiredData(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.importDaqTaskAcquiredData(daqTaskId);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByParams")
    MSResponse<MSPage<DaqTask>> actionQueryDaqTasksByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MSPage<DaqTask> page = daqTaskService.findDaqTasksByParams(pageNum, pageSize, stage);
        return MSResponse.success(page);
    }

    @PostMapping(value = "{daqTaskId}/spiders")
    MSResponse<DaqTask> actionAddDaqTaskSpiders(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> daqSpiderIds
    ) {
        daqTaskService.addDaqTaskSpiders(daqTaskId, daqSpiderIds);
        return MSResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/spiders/queryAll")
    MSResponse<List<DaqSpider>> actionQueryDaqTaskSpiders(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        List<DaqSpider> daqSpiders = daqTaskService.findDaqSpiders(daqTaskId);
        return MSResponse.success(daqSpiders);
    }

    @GetMapping(value = "{daqTaskId}/spiders/queryByPagination")
    MSResponse<MSPage<DaqTaskSpider>> actionQueryDaqTaskSpidersByPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpidersByPagination(daqTaskId, pageNum, pageSize);
        return MSResponse.success(daqTaskSpiders);
    }

    @PostMapping(value = "{daqTaskId}/keywords")
    MSResponse<DaqTask> actionAddDaqTaskKeywords(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<String> keywords
    ) {
        daqTaskService.addDaqTaskKeywords(daqTaskId, keywords);
        return MSResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/keywords/queryByPagination")
    MSResponse<MSPage<DaqTaskKeyword>> actionQueryDaqTaskKeywordsByPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DaqTaskKeyword> page = daqTaskService.findDaqTaskKeywordsByPagination(daqTaskId, pageNum, pageSize);
        return MSResponse.success(page);
    }

    @PutMapping(value = "{daqTaskId}/servers")
    MSResponse<DaqTask> actionBindDaqTaskServer(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> serverIds
    ) {
        daqTaskService.bindDaqTaskServers(daqTaskId, serverIds);
        return MSResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/timingDataVolumes")
    MSResponse<DaqTaskTimingDataVolumes> actionQueryDaqTaskTimingDataVolumes(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        DaqTaskTimingDataVolumes timingDataVolumes = daqTaskService.findDaqTaskTimingDataVolumes(daqTaskId);
        return MSResponse.success(timingDataVolumes);
    }
}
