package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.model.pojo.daq.DaqDataVolume;
import org.metasphere.adminservice.repository.daq.DaqDataVolumeRepository;
import org.metasphere.adminservice.service.daq.DaqDataVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 10:53
 * @Modified By:
 */
@Service(value = "daqDataVolumeService")
public class DaqDataVolumeServiceImpl implements DaqDataVolumeService {

    @Autowired
    private DaqDataVolumeRepository daqDataVolumeRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqDataVolume(DaqDataVolume dataVolume) {
        daqDataVolumeRepository.save(dataVolume);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqDataVolumes(List<DaqDataVolume> dataVolumes) {
        daqDataVolumeRepository.saveAll(dataVolumes);
    }
}
