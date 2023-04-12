package org.metasphere.adminservice.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 20:06
 * @Modified By:
 */
@Data
public class DaqTaskTimingDataVolumes {
    private String taskName;
    private String taskCode;
    private List<TimingDataVolumes> timingDataVolumes;
}
