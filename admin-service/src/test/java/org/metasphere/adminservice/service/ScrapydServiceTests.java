package org.metasphere.adminservice.service;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.exception.ScrapydReqException;
import org.metasphere.adminservice.model.dto.scrapyd.ScrapydResp;
import org.metasphere.adminservice.model.dto.scrapyd.ScrapydStatus;
import org.metasphere.adminservice.model.pojo.DaqJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testFindScrapydStatus() {
        ScrapydStatus scrapydStatus = scrapydService.findScrapydStatus("127.0.0.1", 6800);
        System.out.println(scrapydStatus);
    }

    @Test
    void testAddDaqProject() {
        Integer spidersCount = scrapydService.addScrapyProject("127.0.0.1", 6800, UUID.randomUUID().toString().replace("-", ""));
        System.out.println(spidersCount);
    }

    @Test
    void testScheduleDaqSpider() {
        String jobId = scrapydService.scheduleScrapySpider("127.0.0.1", 6800, "ms-daq-engine", "weibo");
        System.out.println(jobId);
    }

    @Test
    void testCancelDaqSpider() {
        String prevState = scrapydService.cancelScrapySpider("127.0.0.1", 6800, "ms-daq-engine", "8d0f19b5d5e911ed9929005056c00008");
        System.out.println(prevState);
    }

    @Test
    void testFindAllDaqProjects() {
        List<String> projects = scrapydService.findScrapyProjects("127.0.0.1", 6800);
        System.out.println(projects);
    }

    @Test
    void testFindAllVersionsByDaqProject() {
        List<String> versions = scrapydService.findScrapyProjectVersions("127.0.0.1", 6800, "ms-daq-engine3");
        System.out.println(versions);
    }

    @Test
    void testFindAllDaqSpidersByDaqProject() {
        List<String> versions = scrapydService.findScrapyProjectSpiders("127.0.0.1", 6800, "b302c9ae62e840748f0add5a32415f5f");
        System.out.println(versions);
    }

    @Test
    void testFindAllDaqJobs() {
        DaqJobs daqJobs = scrapydService.findScrapyProjectJobs("127.0.0.1", 6800, "ms-daq-engine4");
        System.out.println(daqJobs);
    }

    @Test
    void testDeleteDaqProjectByVersion() {
        scrapydService.deleteScrapyProjectByVersion("127.0.0.1", 6800, "ms-daq-engine3", "v2");
    }

    @Test
    void testDeleteDaqProject() {
        scrapydService.deleteScrapyProject("127.0.0.1", 6800, "ms-daq-engine");
    }

    @Test
    void testAddProject() {
        String url = String.format(MsConst.Scrapyd.URLTemplate.ADD_DAQ_PROJECT, "127.0.0.1", 6800);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add(MsConst.Scrapyd.ReqParam.PROJECT, "151425");

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp resp = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            System.out.println(resp.getSpiders());
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }
}
