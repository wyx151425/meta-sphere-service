package org.metasphere.adminservice.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-12 19:57
 * @Modified By:
 */
@Data
public class TimingDataVolumes {
    private String spiderName;
    private String spiderCode;
    private List<DataVolume> dataVolumes;
}
