package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.DAQTaskKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class DAQTaskKeywordController {

    @Autowired
    private DAQTaskKeywordService daqTaskKeywordService;

    @DeleteMapping(value = "")
    MSResponse<DAQTaskKeyword> actionDeleteDAQTaskKeyword() {
        return null;
    }

    @DeleteMapping(value = "2")
    MSResponse<DAQTaskKeyword> actionDeleteDAQTaskKeywords() {
        return null;
    }
}
