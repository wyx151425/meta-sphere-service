package org.metasphere.portalservice.service;

import org.metasphere.portalservice.model.pojo.deduction.DeductionGroup;

/**
 * @Author: WangZhenqi
 * @Description: 推演业务规范
 * @Date: Created in 2024-01-19 15:29
 * @Modified By:
 */
public interface DeductionService {
    /**
     * 创建推演群组
     *
     * @param deductionGroup 推演群组数据对象
     */
    DeductionGroup createDeductionGroup(DeductionGroup deductionGroup);

    /**
     * 加入推演群组
     *
     * @param deductionRoomCode 推演群组编码
     */
    void joinDeductionGroup(String deductionRoomCode);
}
