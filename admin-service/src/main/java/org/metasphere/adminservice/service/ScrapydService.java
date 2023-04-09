package org.metasphere.adminservice.service;

import org.metasphere.adminservice.model.dto.scrapyd.ScrapydStatus;
import org.metasphere.adminservice.model.pojo.DAQJobs;
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
     * @param servers 所有数据采集服务器的信息
     * @return 所有Scrapyd服务的状态
     */
    List<ScrapydStatus> findAllScrapydStatuses(List<Server> servers);

    /**
     * 添加数据采集项目
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     * @param version  数据采集项目的版本
     * @param egg      数据采集项目打包成Egg文件的存放位置
     * @return 项目所包含爬虫的数量
     */
    Integer addDAQProject(String host, Integer port, String taskName, String version, String egg);

    /**
     * 部署爬虫
     *
     * @param host       Scrapy服务主机IP
     * @param port       服务端口号
     * @param taskName   数据采集任务的名称
     * @param spiderName 爬虫名称
     * @param taskCode   数据采集任务编号
     * @return 爬虫的数据采集任务ID
     */
    String scheduleDAQSpider(String host, Integer port, String taskName, String spiderName, String taskCode);

    /**
     * 取消数据采集任务
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     * @param jobId    数据采集任务ID
     * @return 任务取消前的状态
     */
    String cancelDAQSpider(String host, Integer port, String taskName, String jobId);

    /**
     * 查询Scrapyd服务内部署的所有数据采集项目的名称
     *
     * @param host Scrapy服务主机IP
     * @param port 服务端口号
     * @return 数据采集项目名称列表
     */
    List<String> findAllDAQProjects(String host, Integer port);

    /**
     * 查询数据采集项目所有版本名
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     * @return 所有版本名组成的列表
     */
    List<String> findAllVersionsByDAQProject(String host, Integer port, String taskName);

    /**
     * 查询数据采集项目中所有爬虫的名称
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     * @return 所有爬虫的名称组成的列表
     */
    List<String> findAllDAQSpidersByDAQProject(String host, Integer port, String taskName);

    /**
     * 查询数据采集项目中所有任务的详细信息
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     * @return 所有任务的信息
     */
    DAQJobs findAllDAQJobs(String host, Integer port, String taskName);

    /**
     * 删除某个版本的数据采集项目
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     * @param version  版本号
     */
    void deleteDAQProjectByVersion(String host, Integer port, String taskName, String version);

    /**
     * 删除数据采集项目
     *
     * @param host     Scrapy服务主机IP
     * @param port     服务端口号
     * @param taskName 数据采集任务的名称
     */
    void deleteDAQProject(String host, Integer port, String taskName);
}
