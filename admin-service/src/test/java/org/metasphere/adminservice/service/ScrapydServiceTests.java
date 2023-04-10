package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.model.dto.scrapyd.ScrapydStatus;
import org.metasphere.adminservice.model.pojo.DaqJobs;
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
    void testAddDaqProject() {
        Integer spidersCount = scrapydService.addDaqProject("127.0.0.1", 6800, "ms-daq-engine", "v1", "1676983869.egg");
        System.out.println(spidersCount);
    }

    @Test
    void testScheduleDaqSpider() {
        String jobId = scrapydService.scheduleDaqSpider("127.0.0.1", 6800, "ms-daq-engine", "weibo", "test_task_code");
        System.out.println(jobId);
    }

    @Test
    void testCancelDaqSpider() {
        String prevState = scrapydService.cancelDaqSpider("127.0.0.1", 6800, "ms-daq-engine", "8d0f19b5d5e911ed9929005056c00008");
        System.out.println(prevState);
    }

    @Test
    void testFindAllDaqProjects() {
        List<String> projects = scrapydService.findAllDaqProjects("127.0.0.1", 6800);
        System.out.println(projects);
    }

    @Test
    void testFindAllVersionsByDaqProject() {
        List<String> versions = scrapydService.findAllVersionsByDaqTask("127.0.0.1", 6800, "ms-daq-engine3");
        System.out.println(versions);
    }

    @Test
    void testFindAllDaqSpidersByDaqProject() {
        List<String> versions = scrapydService.findAllDaqSpidersByDaqTask("127.0.0.1", 6800, "ms-daq-engine4");
        System.out.println(versions);
    }

    @Test
    void testFindAllDaqJobs() {
        DaqJobs daqJobs = scrapydService.findAllDaqJobs("127.0.0.1", 6800, "ms-daq-engine4");
        System.out.println(daqJobs);
    }

    @Test
    void testDeleteDaqProjectByVersion() {
        scrapydService.deleteDaqTaskByVersion("127.0.0.1", 6800, "ms-daq-engine3", "v2");
    }

    @Test
    void testDeleteDaqProject() {
        scrapydService.deleteDaqProject("127.0.0.1", 6800, "ms-daq-engine4");
    }
}
