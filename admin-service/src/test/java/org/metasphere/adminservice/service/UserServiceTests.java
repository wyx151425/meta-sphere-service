package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.pojo.User;
import org.metasphere.adminservice.repository.UserRepository;
import org.metasphere.adminservice.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
