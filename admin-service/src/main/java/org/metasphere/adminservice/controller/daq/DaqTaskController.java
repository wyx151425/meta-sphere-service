package org.metasphere.adminservice.controller.daq;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.daq.DaqTaskKeywordService;
import org.metasphere.adminservice.service.daq.DaqTaskService;
import org.metasphere.adminservice.service.daq.DaqTaskSpiderService;
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
    MsResponse<DaqTask> actionCreateDaqTask(@RequestBody DaqTask daqTask) {
        daqTaskService.createDaqTask(daqTask);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{daqTaskId}")
    MsResponse<DaqTask> actionDeleteDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.deleteDaqTask(daqTaskId);
        return MsResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/start")
    MsResponse<DaqTask> actionStartDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.startDaqTask(daqTaskId);
        return MsResponse.success();
    }

    @PutMapping(value = "{daqTaskId}/execute")
    MsResponse<DaqTask> actionExecuteDaqTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        daqTaskService.executeDaqTask(daqTaskId);
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
    MsResponse<List<DaqTaskSpider>> actionQueryDaqTaskSpiders(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        List<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpiders(daqTaskId);
        return MsResponse.success(daqTaskSpiders);
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

    @PutMapping(value = "{daqTaskId}/server")
    MsResponse<DaqTask> actionBindDaqTaskServer(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody Long serverId
    ) {
        daqTaskService.bindDaqTaskServer(daqTaskId, serverId);
        return MsResponse.success();
    }
}
