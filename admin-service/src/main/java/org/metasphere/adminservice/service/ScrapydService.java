package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.scrapyd.ScrapydStatus;
import org.metasphere.adminservice.model.pojo.DaqJobs;
import org.metasphere.adminservice.model.pojo.Server;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description: Scrapyd业务规范
 * @Date: Created in 2023-02-21 17:40
 * @Modified By:
 */
public interface ScrapydService {
    /**
     * 检查Scrapyd服务器是否可访问
     *
     * @param ipAddress Scrapy服务主机IP
     * @param port      服务端口号
     * @return 可达状态
     */
    Boolean checkScrapydStatus(String ipAddress, Integer port);

    /**
     * 查询Scrapyd服务的状态
     *
     * @param host Scrapy服务主机IP
     * @param port 服务端口号
     * @return Scrapyd服务的状态
     */
    ScrapydStatus findScrapydStatus(String host, Integer port);

    /**
     * 查询所有Scrapyd服务的状态
     *
     * @param servers 所有Scrapy服务器的信息
     * @return 所有Scrapyd服务的状态
     */
    List<ScrapydStatus> findAllScrapydStatuses(List<Server> servers);

    /**
     * 添加Scrapy项目
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @return 项目所包含爬虫的数量
     */
    Integer addScrapyProject(String host, Integer port, String project);

    /**
     * 部署爬虫
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @param spider  爬虫名称
     * @return 爬虫的Scrapy任务ID
     */
    String scheduleScrapySpider(String host, Integer port, String project, String spider);

    /**
     * 取消Scrapy任务
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @param jobId   Scrapy作业ID
     * @return 任务取消前的状态
     */
    String cancelScrapySpider(String host, Integer port, String project, String jobId);

    /**
     * 查询Scrapyd服务内部署的所有Scrapy项目的名称
     *
     * @param host Scrapy服务主机IP
     * @param port 服务端口号
     * @return Scrapy项目名称列表
     */
    List<String> findScrapyProjects(String host, Integer port);

    /**
     * 查询Scrapy项目所有版本名
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @return 所有版本名组成的列表
     */
    List<String> findScrapyProjectVersions(String host, Integer port, String project);

    /**
     * 查询Scrapy项目中所有爬虫的名称
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @return 所有爬虫的名称组成的列表
     */
    List<String> findScrapyProjectSpiders(String host, Integer port, String project);

    /**
     * 查询Scrapy项目中所有任务的详细信息
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @return 所有任务的信息
     */
    DaqJobs findScrapyProjectJobs(String host, Integer port, String project);

    /**
     * 删除某个版本的Scrapy项目
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     * @param version 版本号
     */
    void deleteScrapyProjectByVersion(String host, Integer port, String project, String version);

    /**
     * 删除Scrapy项目
     *
     * @param host    Scrapy服务主机IP
     * @param port    服务端口号
     * @param project Scrapy项目的名称
     */
    void deleteScrapyProject(String host, Integer port, String project);
}
