package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.context.constant.MSConstant;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.pojo.HotEvent;
import org.metasphere.adminservice.repository.HotEventRepository;
import org.metasphere.adminservice.service.daq.HotEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-29 14:39
 * @Modified By:
 */
@Service(value = "hotEventService")
public class HotEventServiceImpl implements HotEventService {

    @Autowired
    private HotEventRepository hotEventRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HotEvent saveHotEvent(HotEvent hotEvent) {
        HotEvent target = new HotEvent();
        target.setName(hotEvent.getName());
        target.setIntroduction(hotEvent.getIntroduction());
        target.setStartDate(hotEvent.getStartDate());
        target.setEndDate(hotEvent.getEndDate());
        target.setDaqTaskCode(hotEvent.getDaqTaskCode());
        hotEventRepository.save(target);
        return target;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HotEvent updateHotEvent(HotEvent hotEvent) {
        HotEvent target = hotEventRepository.findById(hotEvent.getId()).orElseThrow(MSException::getDataNotFoundException);
        target.setUpdateAt(LocalDateTime.now().withNano(0));
        target.setName(hotEvent.getName());
        target.setIntroduction(hotEvent.getIntroduction());
        target.setStartDate(hotEvent.getStartDate());
        target.setEndDate(hotEvent.getEndDate());
        target.setDaqTaskCode(hotEvent.getDaqTaskCode());
        hotEventRepository.save(target);
        return target;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHotEvent(Long hotEvenId) {
        HotEvent target = hotEventRepository.findById(hotEvenId).orElseThrow(MSException::getDataNotFoundException);
        target.setStatus(MSConstant.MetaSphereEntity.Status.DISABLED);
        hotEventRepository.save(target);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public HotEvent findHotEvent(Long hotEvenId) {
        return hotEventRepository.findById(hotEvenId).orElseThrow(MSException::getDataNotFoundException);
    }
}
