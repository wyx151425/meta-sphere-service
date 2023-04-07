package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.model.pojo.DAQTaskSpider;
import org.metasphere.adminservice.repository.DAQTaskSpiderRepository;
import org.metasphere.adminservice.service.DAQTaskSpiderService;
import org.metasphere.adminservice.service.ScrapydService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:26
 * @Modified By:
 */
@Service(value = "daqTaskSpiderService")
public class DAQTaskSpiderServiceImpl implements DAQTaskSpiderService {

    @Autowired
    private DAQTaskSpiderRepository daqTaskSpiderRepository;

    @Autowired
    private ScrapydService scrapydService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQTaskSpider(DAQTaskSpider daqTaskSpider) {
        daqTaskSpiderRepository.save(daqTaskSpider);
    }
}
