package org.metasphere.adminservice.repository;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.daq.WeiboItem;
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
public class WeiboItemRepositoryTests {

    @Autowired
    private WeiboItemRepository weiboItemRepository;

    @Test
    void testFindAll() {
        Iterator<WeiboItem> iterator = weiboItemRepository.findAll().iterator();
        List<WeiboItem> weiboItems = new ArrayList<>();
        while (iterator.hasNext()) {
            weiboItems.add(iterator.next());
        }
        System.out.println(weiboItems.size());
    }
}
