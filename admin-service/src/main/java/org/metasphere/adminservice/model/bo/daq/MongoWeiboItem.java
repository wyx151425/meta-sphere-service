package org.metasphere.adminservice.model.bo.daq;

import org.metasphere.adminservice.constant.MsConst;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
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
public class MongoWeiboItem {
    @Id
    private String id;
    private String spiderCode;
    private String taskCode;
    private String keyword;
    private String mid;
    private String mblogId;
    private String createdAt;
    private String textRaw;
    private String text;
    private String textLength;
    private String repostsCount;
    private String commentsCount;
    private String attitudesCount;
    private String regionName;
    private String source;
    private String weiboUrl;
    private String user;
    private String userId;
    private String userScreenName;
    private String userProfileUrl;

    public MongoWeiboItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpiderCode() {
        return spiderCode;
    }

    public void setSpiderCode(String spiderCode) {
        this.spiderCode = spiderCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMblogId() {
        return mblogId;
    }

    public void setMblogId(String mblogId) {
        this.mblogId = mblogId;
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

    public String getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(String repostsCount) {
        this.repostsCount = repostsCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(String attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public static List<DaqEngineWeiboItem> batchConvertToDaqEngineWeiboItem(String taskName, List<MongoWeiboItem> mongoWeiboItems) {
        return mongoWeiboItems
                .stream().map(mongoWeiboItem -> {
                    System.out.println(mongoWeiboItem);
                    DaqEngineWeiboItem weiboItem = new DaqEngineWeiboItem();
                    weiboItem.setTaskName(taskName);
                    weiboItem.setTaskCode(mongoWeiboItem.getTaskCode());
                    weiboItem.setTaskKeyword(mongoWeiboItem.getKeyword());
                    weiboItem.setSourceId(mongoWeiboItem.getMid());
                    weiboItem.setSourceBlogId(mongoWeiboItem.getMblogId());
                    weiboItem.setSourceCreateAt(LocalDateTime.parse(mongoWeiboItem.getCreatedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    weiboItem.setText(mongoWeiboItem.getTextRaw());
                    weiboItem.setLikesCount(Integer.valueOf(mongoWeiboItem.getAttitudesCount()));
                    weiboItem.setCommentsCount(Integer.valueOf(mongoWeiboItem.getCommentsCount()));
                    weiboItem.setRepostsCount(Integer.valueOf(mongoWeiboItem.getRepostsCount()));
                    weiboItem.setAccountId(mongoWeiboItem.getUserId());
                    weiboItem.setAccountName(mongoWeiboItem.getUserScreenName());
                    weiboItem.setRegionName(mongoWeiboItem.getRegionName());
                    weiboItem.setPlatformName(MsConst.DaqSpider.CODE2NAME.get(mongoWeiboItem.getSpiderCode()));
                    weiboItem.setPlatformCode(mongoWeiboItem.getSpiderCode());
                    return weiboItem;
                }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MongoWeiboItem{" +
                "id='" + id + '\'' +
                ", spiderCode='" + spiderCode + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", keyword='" + keyword + '\'' +
                ", mid='" + mid + '\'' +
                ", mblogId='" + mblogId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", textRaw='" + textRaw + '\'' +
                ", text='" + text + '\'' +
                ", textLength='" + textLength + '\'' +
                ", repostsCount='" + repostsCount + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", attitudesCount='" + attitudesCount + '\'' +
                ", regionName='" + regionName + '\'' +
                ", source='" + source + '\'' +
                ", weiboUrl='" + weiboUrl + '\'' +
                ", user='" + user + '\'' +
                ", userId='" + userId + '\'' +
                ", userScreenName='" + userScreenName + '\'' +
                ", userProfileUrl='" + userProfileUrl + '\'' +
                '}';
    }
}
