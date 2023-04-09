package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQSpider;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.DAQSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-08 17:19
 * @Modified By:
 */
@RestController
@RequestMapping(value = "daqSpiders")
public class DAQSpiderController {

    @Autowired
    private DAQSpiderService daqSpiderService;

    @PostMapping(value = "")
    public MSResponse<DAQSpider> actionSaveDAQSpider(@RequestBody DAQSpider daqSpider) {
        daqSpiderService.addDAQSpider(daqSpider);
        return MSResponse.success();
    }

    @GetMapping(value = "queryAll")
    public MSResponse<List<DAQSpider>> actionQueryDAQSpiders() {
        List<DAQSpider> daqSpiders = daqSpiderService.findDAQSpiders();
        return MSResponse.success(daqSpiders);
    }

    @GetMapping(value = "queryByPagination")
    public MSResponse<MSPage<DAQSpider>> actionQueryDAQSpidersByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DAQSpider> page = daqSpiderService.findDAQSpidersByPagination(pageNum, pageSize);
        return MSResponse.success(page);
    }
}
