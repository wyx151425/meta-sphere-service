package org.metasphere.adminservice.controller.daq;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.daq.DaqSpider;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.daq.DaqSpiderService;
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
    public MSResponse<DaqSpider> actionSaveDaqSpider(@RequestBody DaqSpider daqSpider) {
        daqSpiderService.saveDaqSpider(daqSpider);
        return MSResponse.success();
    }

    @GetMapping(value = "queryAll")
    public MSResponse<List<DaqSpider>> actionQueryDaqSpiders() {
        List<DaqSpider> daqSpiders = daqSpiderService.findDaqSpiders();
        return MSResponse.success(daqSpiders);
    }

    @GetMapping(value = "queryByPagination")
    public MSResponse<MSPage<DaqSpider>> actionQueryDaqSpidersByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<DaqSpider> page = daqSpiderService.findDaqSpidersByPagination(pageNum, pageSize);
        return MSResponse.success(page);
    }
}
