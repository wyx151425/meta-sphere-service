package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.DaqTask;
import org.metasphere.adminservice.model.pojo.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.DaqTaskSpider;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
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
    MsResponse<DaqTask> actionCreateDAQTask(@RequestBody DaqTask daqTask) {
        daqTaskService.createDaqTask(daqTask);
        return MsResponse.success();
    }

    @DeleteMapping(value = "{id}")
    MsResponse<DaqTask> actionDeleteDAQTask(@PathVariable(value = "id") Long id) {
        daqTaskService.deleteDaqTask(id);
        return MsResponse.success();
    }

    @PutMapping(value = "{id}/start")
    MsResponse<DaqTask> actionStartDAQTask(@PathVariable(value = "id") Long id) {
        daqTaskService.startDaqTask(id);
        return MsResponse.success();
    }


    @GetMapping(value = "queryByParams")
    MsResponse<MsPage<DaqTask>> actionQueryDAQTasksByParams(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "stage", required = false) Integer stage
    ) {
        MsPage<DaqTask> page = daqTaskService.findDaqTasksByParams(pageNum, pageSize, stage);
        return MsResponse.success(page);
    }

    @PostMapping(value = "{daqTaskId}/daqTaskSpiders")
    MsResponse<DaqTask> actionAddDAQTaskSpiders(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<Long> daqSpiderIds
    ) {
        daqTaskService.addDaqTaskSpiders(daqTaskId, daqSpiderIds);
        return MsResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/daqTaskSpiders/queryAll")
    MsResponse<List<DaqTaskSpider>> actionQueryDAQTaskSpidersByDAQTask(@PathVariable(value = "daqTaskId") Long daqTaskId) {
        List<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpidersByDaqTask(daqTaskId);
        return MsResponse.success(daqTaskSpiders);
    }

    @GetMapping(value = "{daqTaskId}/daqTaskSpiders/queryByPagination")
    MsResponse<MsPage<DaqTaskSpider>> actionQueryDAQTaskSpidersByDAQTaskAndPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<DaqTaskSpider> daqTaskSpiders = daqTaskService.findDaqTaskSpidersByDaqTaskAndPagination(daqTaskId, pageNum, pageSize);
        return MsResponse.success(daqTaskSpiders);
    }

    @PostMapping(value = "{daqTaskId}/daqTaskKeywords")
    MsResponse<DaqTask> actionAddDAQTaskKeywords(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestBody List<String> keywords
    ) {
        daqTaskService.addDaqTaskKeywords(daqTaskId, keywords);
        return MsResponse.success();
    }

    @GetMapping(value = "{daqTaskId}/daqTaskKeywords/queryByPagination")
    MsResponse<MsPage<DaqTaskKeyword>> actionQueryDAQTaskKeywordsByDAQTaskAndPagination(
            @PathVariable(value = "daqTaskId") Long daqTaskId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<DaqTaskKeyword> page = daqTaskService.findDaqTaskKeywordsByDaqTaskAndPagination(daqTaskId, pageNum, pageSize);
        return MsResponse.success(page);
    }
}
