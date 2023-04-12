package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.model.pojo.daq.DaqDataVolume;
import org.metasphere.adminservice.repository.daq.DaqDataVolumeRepository;
import org.metasphere.adminservice.service.daq.DaqDataVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

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

    @Override
    public Map<String, List<DaqDataVolume>> findSpiderCode2DaqDataVolumesMap(String taskCode) {
        String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, taskCode);
        List<String> spiderCodes = redisTemplate.opsForList().range(spidersCacheKey, 0, -1);

        Map<String, List<DaqDataVolume>> dataVolumesMap = new HashMap<>();
        spiderCodes.forEach(spiderCode -> {
            List<DaqDataVolume> dataVolumes = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, spiderCode);
            dataVolumesMap.put(spiderCode, dataVolumes);
        });
        return dataVolumesMap;
    }
}
