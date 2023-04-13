package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqTaskDataVolume;

import java.util.List;
import java.util.Map;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 10:53
 * @Modified By:
 */
public interface DaqTaskDataVolumeService {

    /**
     * 保存数据采集任务数据量
     *
     * @param dataVolume 数据量
     */
    void saveDaqDataVolume(DaqTaskDataVolume dataVolume);

    /**
     * 保存数据采集任务数据量
     *
     * @param dataVolumes 数据量
     */
    void saveDaqDataVolumes(List<DaqTaskDataVolume> dataVolumes);

    /**
     * 根据数据采集任务编码获取爬虫编码与时序数据量的映射
     *
     * @param taskCode 数据采集任务编码
     * @return 爬虫编码与时序数据量的映射
     */
    Map<String, List<DaqTaskDataVolume>> findSpiderCode2DaqDataVolumesMap(String taskCode);
}
