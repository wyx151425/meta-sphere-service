package org.metasphere.adminservice.model.pojo.daq;

import org.hibernate.annotations.Where;
import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-13 15:55
 * @Modified By:
 */
@Entity
@Table(name = "ms_weibo_item")
@Where(clause = "status = 1")
public class WeiboItem extends MetaSphereEntity {
    /**
     * 所属数据采集任务的名称
     */
    private String taskName;
    /**
     * 所属数据采集任务的编码
     */
    private String taskCode;
    /**
     * 任务关键词
     */
    private String taskKeyword;
    /**
     * 源平台中的ID
     */
    private String sourceId;
    /**
     * 源平台中的哈希ID
     */
    private String sourceBlogId;
    /**
     * 转发文本在源平台中源文本的ID
     */
    private String sourceOriginId;
    /**
     * 源平台中的入库时间
     */
    private LocalDateTime sourceCreateAt;
    /**
     * 微博文本内容
     */
    private String text;
    /**
     * 阅读数
     */
    private Integer readsCount;
    /**
     * 点赞数
     */
    private Integer likesCount;
    /**
     * 评论数
     */
    private Integer commentsCount;
    /**
     * 转发数
     */
    private Integer repostsCount;
    /**
     * 发布账号的ID
     */
    private String accountId;
    /**
     * 发布账号的名称
     */
    private String accountName;
    /**
     * 发布账号所在地区
     */
    private String regionName;
    /**
     * 源平台的名称
     */
    private String platformName;
    /**
     * 源平台的编码
     */
    private String platformCode;

    public WeiboItem() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskKeyword() {
        return taskKeyword;
    }

    public void setTaskKeyword(String taskKeyword) {
        this.taskKeyword = taskKeyword;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceBlogId() {
        return sourceBlogId;
    }

    public void setSourceBlogId(String sourceBlogId) {
        this.sourceBlogId = sourceBlogId;
    }

    public String getSourceOriginId() {
        return sourceOriginId;
    }

    public void setSourceOriginId(String sourceOriginId) {
        this.sourceOriginId = sourceOriginId;
    }

    public LocalDateTime getSourceCreateAt() {
        return sourceCreateAt;
    }

    public void setSourceCreateAt(LocalDateTime sourceCreateAt) {
        this.sourceCreateAt = sourceCreateAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getReadsCount() {
        return readsCount;
    }

    public void setReadsCount(Integer readsCount) {
        this.readsCount = readsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(Integer repostsCount) {
        this.repostsCount = repostsCount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Override
    public String toString() {
        return "WeiboItem{" +
                "taskName='" + taskName + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", taskKeyword='" + taskKeyword + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", sourceBlogId='" + sourceBlogId + '\'' +
                ", sourceOriginId='" + sourceOriginId + '\'' +
                ", sourceCreateAt=" + sourceCreateAt +
                ", text='" + text + '\'' +
                ", readsCount=" + readsCount +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", repostsCount=" + repostsCount +
                ", accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", platformCode='" + platformCode + '\'' +
                '}';
    }
}
