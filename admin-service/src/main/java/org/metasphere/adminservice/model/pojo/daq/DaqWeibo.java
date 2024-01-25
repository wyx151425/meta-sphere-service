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
public class DaqWeibo {
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
    private String mid;
    /**
     * 源平台中的哈希ID
     */
    private String hashMid;
    /**
     * 源平台中的入库时间
     */
    private String createdAt;
    /**
     * 微博文本内容
     */
    private String text;
    /**
     * 原生微博文本内容
     */
    private String textRaw;
    /**
     * 微博文本长度
     */
    private Integer textLength;
    /**
     * 微博的链接地址
     */
    private String weiboUrl;
    /**
     * 微博发布客户端
     */
    private String source;
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
    private String uid;
    /**
     * 发布账号的名称
     */
    private String userScreenName;
    /**
     * 发布账号所在地区
     */
    private String regionName;

    public DaqWeibo() {
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getHashMid() {
        return hashMid;
    }

    public void setHashMid(String hashMid) {
        this.hashMid = hashMid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextRaw() {
        return textRaw;
    }

    public void setTextRaw(String textRaw) {
        this.textRaw = textRaw;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "DaqWeibo{" +
                "taskCode='" + taskCode + '\'' +
                ", taskKeyword='" + taskKeyword + '\'' +
                ", mid='" + mid + '\'' +
                ", hashMid='" + hashMid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", text='" + text + '\'' +
                ", textRaw='" + textRaw + '\'' +
                ", textLength=" + textLength +
                ", weiboUrl='" + weiboUrl + '\'' +
                ", source='" + source + '\'' +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", repostsCount=" + repostsCount +
                ", uid='" + uid + '\'' +
                ", userScreenName='" + userScreenName + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
