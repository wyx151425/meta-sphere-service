package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.pojo.daq.DaqDataVolume;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 10:53
 * @Modified By:
 */
public interface DaqDataVolumeService {

    /**
     * 保存数据采集任务数据量
     *
     * @param dataVolume 数据量
     */
    void saveDaqDataVolume(DaqDataVolume dataVolume);

    /**
     * 保存数据采集任务数据量
     *
     * @param dataVolumes 数据量
     */
    void saveDaqDataVolumes(List<DaqDataVolume> dataVolumes);
}
