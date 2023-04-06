package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.pojo.User;
import org.metasphere.adminservice.repository.UserRepository;
import org.metasphere.adminservice.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

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
        user.setStatus(1);
        user.setObjectId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        user.setCreateAt(dateTime);
        user.setUpdateAt(dateTime);
        user.setEmail("wyx151425@163.com");
        user.setName("王振琦");
        user.setPassword(MD5Utils.encrypt("151425"));
        userRepository.save(user);
    }

    @Test
    void testUUID() {
        System.out.println(UUID.randomUUID().toString().length());
    }
}
