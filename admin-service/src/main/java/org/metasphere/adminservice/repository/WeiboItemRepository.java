package org.metasphere.adminservice.repository;

import org.metasphere.adminservice.model.bo.daq.MongoWeiboItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 16:35
 * @Modified By:
 */
@Repository(value = "weiboItemRepository")
public interface WeiboItemRepository extends CrudRepository<MongoWeiboItem, String> {

}
