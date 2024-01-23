package org.metasphere.portalservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-20 21:54
 * @Modified By:
 */
@SpringBootTest
public class PortalServiceApplicationTests {

    @Test
    void testTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println("MSPODG" + now.format(dtf));
    }
}
