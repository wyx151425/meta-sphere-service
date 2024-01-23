package org.metasphere.portalservice.service.impl;

import org.metasphere.portalservice.model.pojo.deduction.DeductionGroup;
import org.metasphere.portalservice.repository.DeductionGroupRepository;
import org.metasphere.portalservice.service.DeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: WangZhenqi
 * @Description: 推演业务逻辑
 * @Date: Created in 2024-01-19 15:29
 * @Modified By:
 */
@Service
public class DeductionServiceImpl implements DeductionService {

    @Autowired
    private DeductionGroupRepository deductionGroupRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeductionGroup createDeductionGroup(DeductionGroup deductionGroup) {
        DeductionGroup target = new DeductionGroup();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String code = "MSPODG" + now.format(dtf);
        target.setCode(code);

        target.setTheme(deductionGroup.getTheme());
        target.setType(deductionGroup.getType());
        target.setIntervenedLevel(deductionGroup.getIntervenedLevel());
        deductionGroupRepository.save(target);
        return target;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinDeductionGroup(String deductionRoomCode) {

    }
}
