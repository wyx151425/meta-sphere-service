package org.metasphere.adminservice.service;

import io.lettuce.core.output.ScanOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.http.HttpClient;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-03-09 9:53
 * @Modified By:
 */
@SpringBootTest
public class ServerServiceTests {

    @Autowired
    private ServerService serverService;

    @Test
    void testCheckServerStatus() {
        int status = serverService.checkServerStatus("180.201.163.246");
        System.out.println(status);
    }
}
