package org.metasphere.adminservice.model.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-08 17:27
 * @Modified By:
 */
@Data
public class AddDAQTaskKeywordsReq {
    private Long daqTaskId;
    private List<String> keywords;
}
