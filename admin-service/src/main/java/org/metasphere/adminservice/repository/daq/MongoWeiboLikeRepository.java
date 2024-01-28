package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.bo.daq.MongoWeiboLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-25 16:09
 * @Modified By:
 */
@Repository(value = "mongoWeiboLikeRepository")
public interface MongoWeiboLikeRepository extends MongoRepository<MongoWeiboLike, String> {
    /**
     * 根据数据采集任务编号获取微博点赞数据
     * @param daqTaskCode 数据采集任务编号
     * @param pageable 分页参数
     * @return 微博点赞数据集合
     */
    Page<MongoWeiboLike> findAllByTaskCode(String daqTaskCode, Pageable pageable);

    /**
     * 查询数据采集任务的采集到的微博点赞数据量
     * @param daqTaskCode 数据采集任务编号
     * @return 数据量
     */
    long countAllByTaskCode(String daqTaskCode);
}
