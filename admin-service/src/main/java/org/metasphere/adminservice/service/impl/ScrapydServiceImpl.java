package org.metasphere.adminservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.exception.ScrapydReqException;
import org.metasphere.adminservice.model.dto.scrapyd.*;
import org.metasphere.adminservice.model.pojo.DaqJob;
import org.metasphere.adminservice.model.pojo.DaqJobs;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.service.ScrapydService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WangZhenqi
 * @Description: Scrapyd业务逻辑
 * @Date: Created in 2023-02-21 19:46
 * @Modified By:
 */
@Slf4j
@Service(value = "scrapydService")
public class ScrapydServiceImpl implements ScrapydService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean checkScrapydStatus(String ipAddress, Integer port) {
        String url = String.format(MsConst.Scrapyd.URLTemplate.QUERY_SCRAPYD_STATUS, ipAddress, port);
        try {
            ScrapydResp resp = restTemplate.getForObject(url, ScrapydResp.class);
            return MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public ScrapydStatus findScrapydStatus(String host, Integer port) {
        // curl http://127.0.0.1:6800/daemonstatus.json
        // {"status": "ok", "finished": 0, "running": 0, "node_name": "DESKTOP", "pending": 0}
        String url = String.format(MsConst.Scrapyd.URLTemplate.QUERY_SCRAPYD_STATUS, host, port);

        ScrapydResp resp = restTemplate.getForObject(url, ScrapydResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            ScrapydStatus scrapydStatus = new ScrapydStatus();
            scrapydStatus.setNodeName(resp.getNode_name());
            scrapydStatus.setPendingJobsCount(resp.getPending());
            scrapydStatus.setRunningJobsCount(resp.getRunning());
            scrapydStatus.setFinishedJobsCount(resp.getFinished());

            return scrapydStatus;
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public List<ScrapydStatus> findAllScrapydStatuses(List<Server> servers) {
        List<ScrapydStatus> scrapydStatuses = new ArrayList<>();
        for (Server server : servers) {
            ScrapydStatus status = findScrapydStatus(server.getIpAddress(), server.getPort());
            scrapydStatuses.add(status);
        }

        return scrapydStatuses;
    }

    @Override
    public Integer addScrapyProject(String host, Integer port, String project) {
        // curl http://127.0.0.1:6800/addversion.json -F project=ms -F version=v1 -F egg=@ms.egg
        // {"status": "ok", "spiders": 0}
        String url = String.format(MsConst.Scrapyd.URLTemplate.ADD_DAQ_PROJECT, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add(MsConst.Scrapyd.ReqParam.PROJECT, project);
        formData.add(MsConst.Scrapyd.ReqParam.VERSION, MsConst.Scrapyd.PROJECT_VERSION);
        ClassPathResource resource = new ClassPathResource(MsConst.Scrapyd.EGG_FILE_PATH);
        formData.add(MsConst.Scrapyd.ReqParam.EGG, resource);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(formData, headers);
        ScrapydResp resp = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getSpiders();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public String scheduleScrapySpider(String host, Integer port, String project, String spider) {
        // curl http://127.0.0.1:6800/schedule.json -d project=ms -d spider=weibo
        // {"status": "ok", "jobid": "6487ec79947edab326d6db28a2d86511e8247444"}
        String url = String.format(MsConst.Scrapyd.URLTemplate.SCHEDULE_DAQ_SPIDER, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(MsConst.Scrapyd.ReqParam.PROJECT, project);
        formData.add(MsConst.Scrapyd.ReqParam.SPIDER, spider);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp resp = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getJobid();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public String cancelScrapySpider(String host, Integer port, String project, String jobId) {
        // curl http://127.0.0.1:6800/cancel.json -d project=ms -d job=6487ec79947edab326d6db28a2d86511e8247444
        // {"status": "ok", "prevstate": "running"}
        String url = String.format(MsConst.Scrapyd.URLTemplate.CANCEL_DAQ_SPIDER, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(MsConst.Scrapyd.ReqParam.PROJECT, project);
        formData.add(MsConst.Scrapyd.ReqParam.JOB, jobId);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp respParams = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(respParams.getStatus())) {
            return respParams.getPrevstate();
        } else {
            throw new ScrapydReqException(respParams.getMessage());
        }
    }

    @Override
    public List<String> findScrapyProjects(String host, Integer port) {
        // curl http://127.0.0.1:6800/listprojects.json
        // {"status": "ok", "projects": ["weibo", "zhihu"]}
        String url = String.format(MsConst.Scrapyd.URLTemplate.QUERY_DAQ_PROJECTS, host, port);

        ScrapydProjectsResp resp = restTemplate.getForObject(url, ScrapydProjectsResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getProjects();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public List<String> findScrapyProjectVersions(String host, Integer port, String project) {
        // curl http://127.0.0.1:6800/listversions.json?project=ms
        // {"status": "ok", "versions": ["v1", "v2"]}
        String url = String.format(MsConst.Scrapyd.URLTemplate.QUERY_DAQ_PROJECT_VERSIONS, host, port, project);

        ScrapydVersionsResp resp = restTemplate.getForObject(url, ScrapydVersionsResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getVersions();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public List<String> findScrapyProjectSpiders(String host, Integer port, String project) {
        // curl http://120.27.34.25:6800/listspiders.json?project=ms
        // {"status": "ok", "spiders": ["weibo"]}
        String url = String.format(MsConst.Scrapyd.URLTemplate.QUERY_DAQ_PROJECT_SPIDERS, host, port, project);

        ScrapydSpidersResp resp = restTemplate.getForObject(url, ScrapydSpidersResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getSpiders();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public DaqJobs findScrapyProjectJobs(String host, Integer port, String project) {
        // curl http://120.27.34.25:6800/listjobs.json?project=weibo
        //{"status": "ok", "pending": [{"id": "", "spider": ""}],
        // "running": [{"id": "", "spider": "", "start_time": ""}],
        // "finished": [{"id": "", "spider": "", "start_time": "", "end_time": ""}]}
        String url = String.format(MsConst.Scrapyd.URLTemplate.QUERY_DAQ_PROJECT_JOBS, host, port, project);

        ScrapydJobsResp resp = restTemplate.getForObject(url, ScrapydJobsResp.class);

        if (MsConst.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            DaqJobs daqJobs = new DaqJobs();

            List<ScrapydJob> pending = resp.getPending();
            List<DaqJob> pendingJobs = pending.stream().map(job -> {
                DaqJob daqJob = new DaqJob();
                daqJob.setId(job.getId());
                daqJob.setSpiderName(job.getSpider());
                return daqJob;
            }).collect(Collectors.toList());
            daqJobs.setPendingJobs(pendingJobs);

            List<ScrapydJob> running = resp.getRunning();
            List<DaqJob> runningJobs = running.stream().map(job -> {
                DaqJob daqJob = new DaqJob();
                daqJob.setId(job.getId());
                daqJob.setSpiderName(job.getSpider());
                daqJob.setStartTime(LocalDateTime.parse(job.getStart_time().replace(" ", "T")));
                return daqJob;
            }).collect(Collectors.toList());
            daqJobs.setRunningJobs(runningJobs);

            List<ScrapydJob> finished = resp.getFinished();
            List<DaqJob> finishedJobs = finished.stream().map(job -> {
                DaqJob daqJob = new DaqJob();
                daqJob.setId(job.getId());
                daqJob.setSpiderName(job.getSpider());
                daqJob.setStartTime(LocalDateTime.parse(job.getStart_time().replace(" ", "T")));
                daqJob.setEndTime(LocalDateTime.parse(job.getEnd_time().replace(" ", "T")));
                return daqJob;
            }).collect(Collectors.toList());
            daqJobs.setFinishedJobs(finishedJobs);

            return daqJobs;
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public void deleteScrapyProjectByVersion(String host, Integer port, String project, String version) {
        // curl http://127.0.0.1:6800/delversion.json -d project=ms -d version=v1
        // {"status": "ok"}
        String url = String.format(MsConst.Scrapyd.URLTemplate.DELETE_DAQ_PROJECT_VERSION, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.set(MsConst.Scrapyd.ReqParam.PROJECT, project);
        formData.set(MsConst.Scrapyd.ReqParam.VERSION, version);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp respParams = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (!MsConst.Scrapyd.RespStatus.OK.equals(respParams.getStatus())) {
            throw new ScrapydReqException(respParams.getMessage());
        }
    }

    @Override
    public void deleteScrapyProject(String host, Integer port, String project) {
        // curl http://127.0.0.1:6800/delproject.json -d project=ms
        // {"status": "ok"}
        String url = String.format(MsConst.Scrapyd.URLTemplate.DELETE_DAQ_PROJECT, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.set(MsConst.Scrapyd.ReqParam.PROJECT, project);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp respParams = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (!MsConst.Scrapyd.RespStatus.OK.equals(respParams.getStatus())) {
            throw new ScrapydReqException(respParams.getMessage());
        }
    }
}

