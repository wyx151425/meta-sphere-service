package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskDataVolume;
import org.metasphere.adminservice.repository.daq.DaqDataVolumeRepository;
import org.metasphere.adminservice.service.daq.DaqTaskDataVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 10:53
 * @Modified By:
 */
@Service(value = "daqDataVolumeService")
public class DaqTaskDataVolumeServiceImpl implements DaqTaskDataVolumeService {

    @Autowired
    private DaqDataVolumeRepository daqDataVolumeRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqDataVolume(DaqTaskDataVolume dataVolume) {
        daqDataVolumeRepository.save(dataVolume);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqDataVolumes(List<DaqTaskDataVolume> dataVolumes) {
        daqDataVolumeRepository.saveAll(dataVolumes);
    }

    @Override
    public Map<String, List<DaqTaskDataVolume>> findSpiderCode2DaqDataVolumesMap(String taskCode) {
        String spidersCacheKey = String.format(MsConst.CacheKeyTemplate.DAQ_TASK_SPIDERS, taskCode);
        List<String> spiderCodes = redisTemplate.opsForList().range(spidersCacheKey, 0, -1);

        Map<String, List<DaqTaskDataVolume>> dataVolumesMap = new HashMap<>();
        spiderCodes.forEach(spiderCode -> {
            List<DaqTaskDataVolume> dataVolumes = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, spiderCode);
            dataVolumesMap.put(spiderCode, dataVolumes);
            if ("weibo".equals(spiderCode)) {
                List<DaqTaskDataVolume> dataVolumes1 = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, "weibo_user");
                dataVolumesMap.put(spiderCode, dataVolumes1);
                List<DaqTaskDataVolume> dataVolumes2 = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, "weibo_like");
                dataVolumesMap.put(spiderCode, dataVolumes2);
                List<DaqTaskDataVolume> dataVolumes3 = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, "weibo_comment");
                dataVolumesMap.put(spiderCode, dataVolumes3);
                List<DaqTaskDataVolume> dataVolumes4 = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, "weibo_repost");
                dataVolumesMap.put(spiderCode, dataVolumes4);
            }
        });
        return dataVolumesMap;
    }
}
