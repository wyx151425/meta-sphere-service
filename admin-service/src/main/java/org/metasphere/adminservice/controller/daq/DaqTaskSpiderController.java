package org.metasphere.adminservice.controller.daq;

import org.metasphere.adminservice.service.daq.DaqTaskSpiderService;
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
public class DaqTaskSpiderController {

    @Autowired
    private DaqTaskSpiderService daqTaskSpiderService;
}
