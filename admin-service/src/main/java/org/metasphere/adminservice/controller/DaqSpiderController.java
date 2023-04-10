package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.DaqSpider;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.DaqSpiderService;
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
public class DaqSpiderController {

    @Autowired
    private DaqSpiderService daqSpiderService;

    @PostMapping(value = "")
    public MsResponse<DaqSpider> actionSaveDAQSpider(@RequestBody DaqSpider daqSpider) {
        daqSpiderService.addDaqSpider(daqSpider);
        return MsResponse.success();
    }

    @GetMapping(value = "queryAll")
    public MsResponse<List<DaqSpider>> actionQueryDAQSpiders() {
        List<DaqSpider> daqSpiders = daqSpiderService.findDaqSpiders();
        return MsResponse.success(daqSpiders);
    }

    @GetMapping(value = "queryByPagination")
    public MsResponse<MsPage<DaqSpider>> actionQueryDAQSpidersByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<DaqSpider> page = daqSpiderService.findDaqSpidersByPagination(pageNum, pageSize);
        return MsResponse.success(page);
    }
}
