package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.bo.daq.MongoWeibo;
import org.metasphere.adminservice.model.pojo.auth.User;
import org.metasphere.adminservice.repository.UserRepository;
import org.metasphere.adminservice.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 21:49
 * @Modified By:
 */
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveMsUser() {
        LocalDateTime dateTime = LocalDateTime.now().withNano(0);
        User user = new User();
        user.setEmail("wyx151425@163.com");
        user.setName("王振琦");
        user.setPassword(Md5Utils.encrypt("151425"));
        userRepository.save(user);
    }

    @Test
    void testUUID() {
        System.out.println(UUID.randomUUID().toString().length());

        List<Long> toAdd = new ArrayList<>();
        toAdd.add(1L);
        toAdd.add(2L);
        toAdd.add(3L);

        List<Long> old = new ArrayList<>();
        old.add(1L);
        old.add(4L);

        List<Long> c = old.stream()
                .filter(id -> !toAdd.contains(id))
                .collect(Collectors.toList());
        System.out.println(c);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testDateConvert() {
//        LocalDateTime localDateTime = LocalDateTime.parse("2023-02-04 08:22:37", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(localDateTime);

        int pageNum = 0;
        int pageSize = 100;
        List<MongoWeibo> mongoWeibo = mongoTemplate.find(Query.query(Criteria.where("task_code").is("fc49a8b7e21d4341898c23fd34996504")).skip(0).limit(pageSize), MongoWeibo.class);
        int count = 0;
        while (mongoWeibo.size() > 0) {
            System.out.println(mongoWeibo);
            count += mongoWeibo.size();
            pageNum++;
            mongoWeibo = mongoTemplate.find(Query.query(Criteria.where("task_code").is("fc49a8b7e21d4341898c23fd34996504")).skip((long) pageSize * pageNum).limit(pageSize), MongoWeibo.class);
        }
        System.out.println(count);

        long count1 = mongoTemplate.count(Query.query(Criteria.where("task_code").is("fc49a8b7e21d4341898c23fd34996504")), MongoWeibo.class);
        System.out.println(count1);
    }
}
