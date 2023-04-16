package org.metasphere.adminservice.model.bo.daq;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 16:15
 * @Modified By:
 */
@Document(collection = "weibo")
public class MongoWeibo {
    @Id
    private String id;
    private String taskCode;
    private String taskKeyword;

    private String mid;
    private String uid;
    private String hashMid;
    private String createdAt;

    private String textRaw;
    private String text;
    private String textLength;
    private String source;
    private String weiboUrl;
    private String regionName;

    private Integer likesCount;
    private Integer commentsCount;
    private Integer repostsCount;

    private String userScreenName;

    public MongoWeibo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getTextRaw() {
        return textRaw;
    }

    public void setTextRaw(String textRaw) {
        this.textRaw = textRaw;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextLength() {
        return textLength;
    }

    public void setTextLength(String textLength) {
        this.textLength = textLength;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public static List<DaqEngineWeiboItem> batchConvertToDaqEngineWeiboItem(String taskName, List<MongoWeibo> mongoWeibos) {
        return mongoWeibos
                .stream().map(mongoWeiboItem -> {
                    System.out.println(mongoWeiboItem);
                    DaqEngineWeiboItem weiboItem = new DaqEngineWeiboItem();
                    weiboItem.setTaskName(taskName);
                    weiboItem.setTaskCode(mongoWeiboItem.getTaskCode());
                    weiboItem.setTaskKeyword(mongoWeiboItem.getTaskKeyword());
                    weiboItem.setSourceId(mongoWeiboItem.getMid());
                    weiboItem.setSourceBlogId(mongoWeiboItem.getHashMid());
                    weiboItem.setSourceCreateAt(LocalDateTime.parse(mongoWeiboItem.getCreatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    weiboItem.setText(mongoWeiboItem.getTextRaw());
                    weiboItem.setLikesCount(Integer.valueOf(mongoWeiboItem.getLikesCount()));
                    weiboItem.setCommentsCount(Integer.valueOf(mongoWeiboItem.getCommentsCount()));
                    weiboItem.setRepostsCount(Integer.valueOf(mongoWeiboItem.getRepostsCount()));
                    weiboItem.setAccountId(mongoWeiboItem.getUid());
                    weiboItem.setAccountName(mongoWeiboItem.getUserScreenName());
                    weiboItem.setRegionName(mongoWeiboItem.getRegionName());
                    return weiboItem;
                }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MongoWeibo{" +
                "id='" + id + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", taskKeyword='" + taskKeyword + '\'' +
                ", mid='" + mid + '\'' +
                ", uid='" + uid + '\'' +
                ", hashMid='" + hashMid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", textRaw='" + textRaw + '\'' +
                ", text='" + text + '\'' +
                ", textLength='" + textLength + '\'' +
                ", source='" + source + '\'' +
                ", weiboUrl='" + weiboUrl + '\'' +
                ", regionName='" + regionName + '\'' +
                ", likesCount='" + likesCount + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", repostsCount='" + repostsCount + '\'' +
                ", userScreenName='" + userScreenName + '\'' +
                '}';
    }
}
