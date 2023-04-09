package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:55
 * @Modified By:
 */
@SpringBootTest
public class DaqDbServiceTests {

    @Autowired
    private DaqDbService daqDbService;

    @Test
    void testCreateDaqDataTable() {
        String code = UUID.randomUUID().toString();
        daqDbService.createDaqDataTable(code);
    }
}
