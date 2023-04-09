package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.dto.scrapyd.ScrapydStatus;
import org.metasphere.adminservice.model.pojo.DAQJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 20:53
 * @Modified By:
 */
@SpringBootTest
public class ScrapydServiceTests {

    @Autowired
    private ScrapydService scrapydService;

    @Test
    void testFindScrapydStatus() {
        ScrapydStatus scrapydStatus = scrapydService.findScrapydStatus("127.0.0.1", 6800);
        System.out.println(scrapydStatus);
    }

    @Test
    void testAddDAQProject () {
        Integer spidersCount = scrapydService.addDAQProject("127.0.0.1", 6800, "ms-daq-engine", "v1", "1676983869.egg");
        System.out.println(spidersCount);
    }

    @Test
    void testScheduleDAQSpider() {
        String jobId = scrapydService.scheduleDAQSpider("127.0.0.1", 6800, "ms-daq-engine", "weibo", "test_task_code");
        System.out.println(jobId);
    }

    @Test
    void testCancelDAQSpider() {
        String prevState = scrapydService.cancelDAQSpider("127.0.0.1", 6800, "ms-daq-engine", "8d0f19b5d5e911ed9929005056c00008");
        System.out.println(prevState);
    }

    @Test
    void testFindAllDAQProjects() {
        List<String> projects = scrapydService.findAllDAQProjects("127.0.0.1", 6800);
        System.out.println(projects);
    }

    @Test
    void testFindAllVersionsByDAQProject() {
        List<String> versions = scrapydService.findAllVersionsByDAQProject("127.0.0.1", 6800, "ms-daq-engine3");
        System.out.println(versions);
    }

    @Test
    void testFindAllDAQSpidersByDAQProject() {
        List<String> versions = scrapydService.findAllDAQSpidersByDAQProject("127.0.0.1", 6800, "ms-daq-engine4");
        System.out.println(versions);
    }

    @Test
    void testFindAllDAQJobs() {
        DAQJobs daqJobs = scrapydService.findAllDAQJobs("127.0.0.1", 6800, "ms-daq-engine4");
        System.out.println(daqJobs);
    }

    @Test
    void testDeleteDAQProjectByVersion() {
        scrapydService.deleteDAQProjectByVersion("127.0.0.1", 6800, "ms-daq-engine3", "v2");
    }

    @Test
    void testDeleteDAQProject() {
        scrapydService.deleteDAQProject("127.0.0.1", 6800, "ms-daq-engine4");
    }
}
