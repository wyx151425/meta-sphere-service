package org.metasphere.adminservice.repository;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.bo.daq.MongoWeiboItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 16:35
 * @Modified By:
 */
@SpringBootTest
public class MongoWeiboItemRepositoryTests {

    @Autowired
    private WeiboItemRepository weiboItemRepository;

    @Test
    void testFindAll() {
        Iterator<MongoWeiboItem> iterator = weiboItemRepository.findAll().iterator();
        List<MongoWeiboItem> mongoWeiboItems = new ArrayList<>();
        while (iterator.hasNext()) {
            mongoWeiboItems.add(iterator.next());
        }
        System.out.println(mongoWeiboItems.size());
    }
}
