package org.metasphere.portalservice.controller;

import org.metasphere.portalservice.model.pojo.deduction.DeductionGroup;
import org.metasphere.portalservice.service.DeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-19 10:16
 * @Modified By:
 */
@Controller
public class DeductionController {

    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DeductionService deductionService;

    @PostMapping(value = "/createDeductionRoom")
    public void actionCreateDeductionRoom(@RequestBody DeductionGroup deductionGroup) {
        deductionService.createDeductionGroup(deductionGroup);
    }

    @PostMapping(value = "/joinDeductionRoom")
    public void actionJoinDeductionRoom(@RequestParam(value = "deductionRoomCode") String deductionRoomCode) {
        deductionService.joinDeductionGroup(deductionRoomCode);
    }

    // 普通推演 ordinary deduction


    // 干预推演 intervened deduction


    // 交互推演 interactive deduction

}
