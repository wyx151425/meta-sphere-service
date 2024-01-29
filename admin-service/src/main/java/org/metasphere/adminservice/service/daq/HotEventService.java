package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.pojo.HotEvent;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-29 14:37
 * @Modified By:
 */
public interface HotEventService {
    /**
     * 保存舆情热点事件
     *
     * @param hotEvent 舆情热点事件
     * @return 包含ID的舆情热点事件
     */
    HotEvent saveHotEvent(HotEvent hotEvent);

    /**
     * 更新舆情热点事件的数据
     *
     * @param hotEvent 舆情热点事件
     * @return 修改后的舆情热点事件
     */
    HotEvent updateHotEvent(HotEvent hotEvent);

    /**
     * 删除舆情热点事件
     *
     * @param hotEvenId 热点事件ID
     */
    void deleteHotEvent(Long hotEvenId);

    /**
     * 根据ID查询舆情热点事件
     *
     * @param hotEvenId 舆情热点事件ID
     * @return 舆情热点事件
     */
    HotEvent findHotEvent(Long hotEvenId);
}
