package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.pojo.DAQTaskSpider;
import org.metasphere.adminservice.service.DAQTaskSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-08 17:19
 * @Modified By:
 */
@RestController
@RequestMapping(value = "daqTaskSpiders")
public class DAQTaskSpiderController {

    @Autowired
    private DAQTaskSpiderService daqTaskSpiderService;
}
