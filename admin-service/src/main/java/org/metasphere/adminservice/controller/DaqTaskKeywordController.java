package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.pojo.DaqTaskKeyword;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.DaqTaskKeywordService;
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
    MsResponse<DaqTaskKeyword> actionDeleteDAQTaskKeyword(@PathVariable(value = "id") Long id) {
        daqTaskKeywordService.deleteDaqTaskKeyword(id);
        return MsResponse.success();
    }
}
