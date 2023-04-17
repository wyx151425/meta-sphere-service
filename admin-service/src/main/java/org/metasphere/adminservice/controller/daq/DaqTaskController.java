package org.metasphere.adminservice.controller.daq;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqSpider;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;
import org.metasphere.adminservice.model.vo.DaqTaskTimingDataVolumes;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
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
    MsResponse<DaqTask> actionCreateDaqTask(@RequestBody DaqTask daqTask) {
        daqTaskService.createDaqTask(daqTask);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{daqTaskId}")
    MsResponse<DaqTask> actionDeleteDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.deleteDaqTask(daqTaskId);
        return MsResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/init")
    MsResponse<DaqTask> actionInitDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.initDaqTask(daqTaskId);
        return MsResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/perform")
    MsResponse<DaqTask> actionPerformDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.performDaqTask(daqTaskId);
        return MsResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/stopPerforming")
    MsResponse<DaqTask> actionStopPerformingDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.stopDaqTaskPerforming(daqTaskId);
        return MsResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/enterAcquiredData")
    MsResponse<DaqTask> actionEnterDaqTaskAcquiredData(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.enterDaqTaskAcquiredData(daqTaskId);
        return MsResponse.success();
    }

    @GetMapping(value = "queryByParams")
    MsResponse<MsPage<DaqTask>> actionQueryDaqTasksByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MsPage<DaqTask> page = daqTaskService.findDaqTasksByParams(pageNum, pageSize, stage);
        return MsResponse.success(page);
    }

    @PostMapping(value = "{daqTaskId}/spiders")
    MsResponse<DaqTask> actionAddDaqTaskSpiders(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> daqSpiderIds
    ) {
        daqTaskService.addDaqTaskSpiders(daqTaskId, daqSpiderIds);
        return MsResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/spiders/queryAll")
    MsResponse<List<DaqSpider>> actionQueryDaqTaskSpiders(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        List<DaqSpider> daqSpiders = daqTaskService.findDaqSpiders(daqTaskId);
        return MsResponse.success(daqSpiders);
    }

    @GetMapping(value = "{daqTaskId}/spiders/queryByPagination")
    MsResponse<MsPage<DaqTaskSpider>> actionQueryDaqTaskSpidersByPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpidersByPagination(daqTaskId, pageNum, pageSize);
        return MsResponse.success(daqTaskSpiders);
    }

    @PostMapping(value = "{daqTaskId}/keywords")
    MsResponse<DaqTask> actionAddDaqTaskKeywords(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<String> keywords
    ) {
        daqTaskService.addDaqTaskKeywords(daqTaskId, keywords);
        return MsResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/keywords/queryByPagination")
    MsResponse<MsPage<DaqTaskKeyword>> actionQueryDaqTaskKeywordsByPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<DaqTaskKeyword> page = daqTaskService.findDaqTaskKeywordsByPagination(daqTaskId, pageNum, pageSize);
        return MsResponse.success(page);
    }

    @PutMapping(value = "{daqTaskId}/servers")
    MsResponse<DaqTask> actionBindDaqTaskServer(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> serverIds
    ) {
        daqTaskService.bindDaqTaskServers(daqTaskId, serverIds);
        return MsResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/timingDataVolumes")
    MsResponse<DaqTaskTimingDataVolumes> actionQueryDaqTaskTimingDataVolumes(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        DaqTaskTimingDataVolumes timingDataVolumes = daqTaskService.findDaqTaskTimingDataVolumes(daqTaskId);
        return MsResponse.success(timingDataVolumes);
    }
}
