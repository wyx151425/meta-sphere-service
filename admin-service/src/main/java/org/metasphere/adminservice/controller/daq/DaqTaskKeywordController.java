package org.metasphere.adminservice.controller.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskKeyword;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.daq.DaqTaskKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-08 17:19
 * @Modified By:
 */
@RestController
@RequestMapping(value = "daqTaskKeywords")
public class DaqTaskKeywordController {

    @Autowired
    private DaqTaskKeywordService daqTaskKeywordService;

    @DeleteMapping(value = "{id}")
    MSResponse<DaqTaskKeyword> actionDeleteDaqTaskKeyword(@PathVariable(value = "id") Long id) {
        daqTaskKeywordService.deleteDaqTaskKeyword(id);
        return MSResponse.success();
    }
}
