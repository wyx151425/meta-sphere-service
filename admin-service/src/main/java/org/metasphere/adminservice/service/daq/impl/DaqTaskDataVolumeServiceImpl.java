package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.context.constant.MSConstant;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskDataVolume;
import org.metasphere.adminservice.model.pojo.daq.ImportedDataCount;
import org.metasphere.adminservice.repository.daq.DaqDataVolumeRepository;
import org.metasphere.adminservice.service.daq.DaqTaskDataVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public void saveAcquiredDaqDataVolumes(List<DaqTaskDataVolume> dataVolumes) {
        daqDataVolumeRepository.saveAll(dataVolumes);
    }

    @Override
    public Map<String, List<DaqTaskDataVolume>> findSpiderCode2DaqDataVolumesMap(String taskCode) {
        String spidersCacheKey = String.format(MSConstant.CacheKeyTemplate.DAQ_TASK_SPIDERS, taskCode);
        List<String> spiderCodes = redisTemplate.opsForList().range(spidersCacheKey, 0, -1);

        Map<String, List<DaqTaskDataVolume>> dataVolumesMap = new HashMap<>();
        spiderCodes.forEach(spiderCode -> {
            List<DaqTaskDataVolume> dataVolumes = daqDataVolumeRepository.findAllByTaskCodeAndSpiderCodeOrderByCountedAtAsc(taskCode, spiderCode);
            dataVolumesMap.put(spiderCode, dataVolumes);
        });
        return dataVolumesMap;
    }

    @Override
    public List<ImportedDataCount> getImportedDataCounts(String daqTaskCode, List<String> daqTaskSpiderCodes) {
        List<ImportedDataCount> counts = new ArrayList<>();

        if (daqTaskSpiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO)) {
            int acquiredWeiboCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("ACQUIRED_DATA_COUNT:WEIBO:" + daqTaskCode)));
            int importedWeiboCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("IMPORTED_DATA_COUNT:WEIBO:" + daqTaskCode)));
            ImportedDataCount count = new ImportedDataCount();
            count.setPlatformCode(MSConstant.DaqSpider.Codes.WEIBO);
            count.setPlatformName(MSConstant.DaqSpider.CODE2NAME.get(MSConstant.DaqSpider.Codes.WEIBO));
            count.setAcquiredDataCount(acquiredWeiboCount);
            count.setImportedDataCount(importedWeiboCount);
            count.setPercent(100 * importedWeiboCount / acquiredWeiboCount);
            counts.add(count);
        }

        if (daqTaskSpiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO_LIKE)) {
            int acquiredWeiboLikeCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("ACQUIRED_DATA_COUNT:WEIBO_LIKE:" + daqTaskCode)));
            int importedWeiboLikeCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("IMPORTED_DATA_COUNT:WEIBO_LIKE:" + daqTaskCode)));
            ImportedDataCount count = new ImportedDataCount();
            count.setPlatformCode(MSConstant.DaqSpider.Codes.WEIBO_LIKE);
            count.setPlatformName(MSConstant.DaqSpider.CODE2NAME.get(MSConstant.DaqSpider.Codes.WEIBO_LIKE));
            count.setAcquiredDataCount(acquiredWeiboLikeCount);
            count.setImportedDataCount(importedWeiboLikeCount);
            count.setPercent(100 * importedWeiboLikeCount / acquiredWeiboLikeCount);
            counts.add(count);
        }

        if (daqTaskSpiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO_COMMENT)) {
            int acquiredWeiboCommentCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("ACQUIRED_DATA_COUNT:WEIBO_COMMENT:" + daqTaskCode)));
            int importedWeiboCommentCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("IMPORTED_DATA_COUNT:WEIBO_COMMENT:" + daqTaskCode)));
            ImportedDataCount count = new ImportedDataCount();
            count.setPlatformCode(MSConstant.DaqSpider.Codes.WEIBO_COMMENT);
            count.setPlatformName(MSConstant.DaqSpider.CODE2NAME.get(MSConstant.DaqSpider.Codes.WEIBO_COMMENT));
            count.setAcquiredDataCount(acquiredWeiboCommentCount);
            count.setImportedDataCount(importedWeiboCommentCount);
            count.setPercent(100 * importedWeiboCommentCount / acquiredWeiboCommentCount);
            counts.add(count);
        }

        if (daqTaskSpiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO_REPOST)) {
            int acquiredWeiboRepostCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("ACQUIRED_DATA_COUNT:WEIBO_REPOST:" + daqTaskCode)));
            int importedWeiboRepostCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("IMPORTED_DATA_COUNT:WEIBO_REPOST:" + daqTaskCode)));
            ImportedDataCount count = new ImportedDataCount();
            count.setPlatformCode(MSConstant.DaqSpider.Codes.WEIBO_REPOST);
            count.setPlatformName(MSConstant.DaqSpider.CODE2NAME.get(MSConstant.DaqSpider.Codes.WEIBO_REPOST));
            count.setAcquiredDataCount(acquiredWeiboRepostCount);
            count.setImportedDataCount(importedWeiboRepostCount);
            count.setPercent(100 * importedWeiboRepostCount / acquiredWeiboRepostCount);
            counts.add(count);
        }

        if (daqTaskSpiderCodes.contains(MSConstant.DaqSpider.Codes.WEIBO_USER)) {
            int acquiredWeiboUserCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("ACQUIRED_DATA_COUNT:WEIBO_USER:" + daqTaskCode)));
            int importedWeiboUserCount = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("IMPORTED_DATA_COUNT:WEIBO_USER:" + daqTaskCode)));
            ImportedDataCount count = new ImportedDataCount();
            count.setPlatformCode(MSConstant.DaqSpider.Codes.WEIBO_USER);
            count.setPlatformName(MSConstant.DaqSpider.CODE2NAME.get(MSConstant.DaqSpider.Codes.WEIBO_USER));
            count.setAcquiredDataCount(acquiredWeiboUserCount);
            count.setImportedDataCount(importedWeiboUserCount);
            count.setPercent(100 * importedWeiboUserCount / acquiredWeiboUserCount);
            counts.add(count);
        }

        return counts;
    }
}
