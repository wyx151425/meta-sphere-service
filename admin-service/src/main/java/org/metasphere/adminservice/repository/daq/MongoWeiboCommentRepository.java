package org.metasphere.adminservice.repository.daq;

import org.metasphere.adminservice.model.bo.daq.MongoWeiboComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-25 16:08
 * @Modified By:
 */
@Repository(value = "mongoWeiboCommentRepository")
public interface MongoWeiboCommentRepository extends MongoRepository<MongoWeiboComment, String> {
    /**
     * 根据数据采集任务编号获取微博评论数据
     * @param daqTaskCode 数据采集任务编号
     * @param pageable 分页参数
     * @return 微博评论数据集合
     */
    Page<MongoWeiboComment> findAllByTaskCode(String daqTaskCode, Pageable pageable);

    /**
     * 查询数据采集任务的采集到的微博评论数据量
     * @param daqTaskCode 数据采集任务编号
     * @return 数据量
     */
    long countAllByTaskCode(String daqTaskCode);
}
