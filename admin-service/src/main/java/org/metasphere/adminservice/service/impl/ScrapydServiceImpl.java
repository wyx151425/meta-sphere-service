package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.exception.ScrapydReqException;
import org.metasphere.adminservice.model.dto.scrapyd.*;
import org.metasphere.adminservice.model.pojo.DAQJob;
import org.metasphere.adminservice.model.pojo.DAQJobs;
import org.metasphere.adminservice.model.pojo.MSServer;
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
@Service(value = "scrapydService")
public class ScrapydServiceImpl implements ScrapydService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ScrapydStatus findScrapydStatus(String host, Integer port) {
        // curl http://127.0.0.1:6800/daemonstatus.json
        // {"status": "ok", "finished": 0, "running": 0, "node_name": "DESKTOP", "pending": 0}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.QUERY_SCRAPYD_STATUS, host, port);

        ScrapydResp resp = restTemplate.getForObject(url, ScrapydResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
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
    public List<ScrapydStatus> findAllScrapydStatuses(List<MSServer> servers) {
        List<ScrapydStatus> scrapydStatuses = new ArrayList<>();
        for (MSServer server : servers) {
            ScrapydStatus status = findScrapydStatus(server.getHost(), server.getPort());
            scrapydStatuses.add(status);
        }

        return scrapydStatuses;
    }

    @Override
    public Integer addDAQProject(String host, Integer port, String projectName, String version, String eggFileName) {
        // curl http://127.0.0.1:6800/addversion.json -F project=ms -F version=v1 -F egg=@ms.egg
        // {"status": "ok", "spiders": 0}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.ADD_DAQ_PROJECT, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add(MSConstant.Scrapyd.ReqParam.PROJECT, projectName);
        formData.add(MSConstant.Scrapyd.ReqParam.VERSION, version);

        String eggFilePath = String.format("eggs/%s", eggFileName);
        ClassPathResource resource = new ClassPathResource(eggFilePath);
        formData.add(MSConstant.Scrapyd.ReqParam.EGG, resource);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp resp = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getSpiders();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public String scheduleDAQSpider(String host, Integer port, String projectName, String spiderName) {
        // curl http://127.0.0.1:6800/schedule.json -d project=ms -d spider=weibo
        // {"status": "ok", "jobid": "6487ec79947edab326d6db28a2d86511e8247444"}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.SCHEDULE_DAQ_SPIDER, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(MSConstant.Scrapyd.ReqParam.PROJECT, projectName);
        formData.add(MSConstant.Scrapyd.ReqParam.SPIDER, spiderName);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp resp = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getJobid();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public String cancelDAQSpider(String host, Integer port, String projectName, String jobId) {
        // curl http://127.0.0.1:6800/cancel.json -d project=ms -d job=6487ec79947edab326d6db28a2d86511e8247444
        // {"status": "ok", "prevstate": "running"}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.CANCEL_DAQ_SPIDER, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(MSConstant.Scrapyd.ReqParam.PROJECT, projectName);
        formData.add(MSConstant.Scrapyd.ReqParam.JOB, jobId);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp respParams = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(respParams.getStatus())) {
            return respParams.getPrevstate();
        } else {
            throw new ScrapydReqException(respParams.getMessage());
        }
    }

    @Override
    public List<String> findAllDAQProjects(String host, Integer port) {
        // curl http://127.0.0.1:6800/listprojects.json
        // {"status": "ok", "projects": ["weibo", "zhihu"]}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.QUERY_DAQ_PROJECTS, host, port);

        ScrapydProjectsResp resp = restTemplate.getForObject(url, ScrapydProjectsResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getProjects();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public List<String> findAllVersionsByDAQProject(String host, Integer port, String projectName) {
        // curl http://127.0.0.1:6800/listversions.json?project=ms
        // {"status": "ok", "versions": ["v1", "v2"]}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.QUERY_DAQ_PROJECT_VERSIONS, host, port, projectName);

        ScrapydVersionsResp resp = restTemplate.getForObject(url, ScrapydVersionsResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getVersions();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public List<String> findAllDAQSpidersByDAQProject(String host, Integer port, String projectName) {
        // curl http://120.27.34.25:6800/listspiders.json?project=ms
        // {"status": "ok", "spiders": ["weibo"]}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.QUERY_DAQ_PROJECT_SPIDERS, host, port, projectName);

        ScrapydSpidersResp resp = restTemplate.getForObject(url, ScrapydSpidersResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            return resp.getSpiders();
        } else {
            throw new ScrapydReqException(resp.getMessage());
        }
    }

    @Override
    public DAQJobs findAllDAQJobs(String host, Integer port, String projectName) {
        // curl http://120.27.34.25:6800/listjobs.json?project=weibo
        //{"status": "ok", "pending": [{"id": "", "spider": ""}],
        // "running": [{"id": "", "spider": "", "start_time": ""}],
        // "finished": [{"id": "", "spider": "", "start_time": "", "end_time": ""}]}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.QUERY_DAQ_PROJECT_JOBS, host, port, projectName);

        ScrapydJobsResp resp = restTemplate.getForObject(url, ScrapydJobsResp.class);

        if (MSConstant.Scrapyd.RespStatus.OK.equals(resp.getStatus())) {
            DAQJobs daqJobs = new DAQJobs();

            List<ScrapydJob> pending = resp.getPending();
            List<DAQJob> pendingJobs = pending.stream().map(job -> {
                DAQJob daqJob = new DAQJob();
                daqJob.setId(job.getId());
                daqJob.setSpiderName(job.getSpider());
                return daqJob;
            }).collect(Collectors.toList());
            daqJobs.setPendingJobs(pendingJobs);

            List<ScrapydJob> running = resp.getRunning();
            List<DAQJob> runningJobs = running.stream().map(job -> {
                DAQJob daqJob = new DAQJob();
                daqJob.setId(job.getId());
                daqJob.setSpiderName(job.getSpider());
                daqJob.setStartTime(LocalDateTime.parse(job.getStart_time().replace(" ", "T")));
                return daqJob;
            }).collect(Collectors.toList());
            daqJobs.setRunningJobs(runningJobs);

            List<ScrapydJob> finished = resp.getFinished();
            List<DAQJob> finishedJobs = finished.stream().map(job -> {
                DAQJob daqJob = new DAQJob();
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
    public void deleteDAQProjectByVersion(String host, Integer port, String projectName, String version) {
        // curl http://127.0.0.1:6800/delversion.json -d project=ms -d version=v1
        // {"status": "ok"}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.DELETE_DAQ_PROJECT_VERSION, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.set(MSConstant.Scrapyd.ReqParam.PROJECT, projectName);
        formData.set(MSConstant.Scrapyd.ReqParam.VERSION, version);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp respParams = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (!MSConstant.Scrapyd.RespStatus.OK.equals(respParams.getStatus())) {
            throw new ScrapydReqException(respParams.getMessage());
        }
    }

    @Override
    public void deleteDAQProject(String host, Integer port, String projectName) {
        // curl http://127.0.0.1:6800/delproject.json -d project=ms
        // {"status": "ok"}
        String url = String.format(MSConstant.Scrapyd.URLTemplate.DELETE_DAQ_PROJECT, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.set(MSConstant.Scrapyd.ReqParam.PROJECT, projectName);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ScrapydResp respParams = restTemplate.postForObject(url, httpEntity, ScrapydResp.class);

        if (!MSConstant.Scrapyd.RespStatus.OK.equals(respParams.getStatus())) {
            throw new ScrapydReqException(respParams.getMessage());
        }
    }
}

