package org.metasphere.adminservice.service.daq;

import org.metasphere.adminservice.model.bo.daq.*;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:41
 * @Modified By:
 */
public interface DaqTaskStorageSpaceService {
    /**
     * 创建数据采集任务的存储空间
     *
     * @param daqTaskCode 数据采集任务编码
     */
    void createDaqTaskStorageSpace(String daqTaskCode);

//    /**
//     * 保存微博数据
//     *
//     * @param tableName          表名
//     * @param daqEngineWeiboItem 微博数据
//     */
//    void saveDaqEngineWeiboItem(String tableName, DaqEngineWeiboItem daqEngineWeiboItem);
//
//    /**
//     * 保存微博数据
//     *
//     * @param tableName           表名
//     * @param daqEngineWeiboItems 微博数据
//     */
//    void saveDaqEngineWeiboItems(String tableName, List<DaqEngineWeiboItem> daqEngineWeiboItems);

    /**
     * 分页获取数据采集任务的数据
     *
     * @param tableName 数据采集任务表名
     * @param pageNum   分页页码
     * @param pageSize  单页数据量
     */
    void findDaqTaskDataByPagination(String tableName, Integer pageNum, Integer pageSize);

    /**
     * 保存Mongo中的微博
     *
     * @param daqTaskCode 数据采集任务编号
     * @param mongoWeibos Mongo中的微博
     */
    void saveMongoWeibos(String daqTaskCode, List<MongoWeibo> mongoWeibos);

    /**
     * 保存Mongo中的微博点赞
     *
     * @param daqTaskCode     数据采集任务编号
     * @param mongoWeiboLikes Mongo中的微博点赞
     */
    void saveMongoWeiboLikes(String daqTaskCode, List<MongoWeiboLike> mongoWeiboLikes);

    /**
     * 保存Mongo中的微博评论
     *
     * @param daqTaskCode        数据采集任务编号
     * @param mongoWeiboComments Mongo中的微博评论
     */
    void saveMongoWeiboComments(String daqTaskCode, List<MongoWeiboComment> mongoWeiboComments);

    /**
     * 保存Mongo中的微博转发
     *
     * @param daqTaskCode       数据采集任务编号
     * @param mongoWeiboReposts Mongo中的微博转发
     */
    void saveMongoWeiboReposts(String daqTaskCode, List<MongoWeiboRepost> mongoWeiboReposts);

    /**
     * 保存Mongo中的微博用户
     *
     * @param daqTaskCode     数据采集任务编号
     * @param mongoWeiboUsers Mongo中的微博用户
     */
    void saveMongoWeiboUsers(String daqTaskCode, List<MongoWeiboUser> mongoWeiboUsers);
}
