package org.metasphere.adminservice.model.bo.daq;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-15 16:31
 * @Modified By:
 */
@Document(collection = "weibo_user")
public class MongoWeiboUser {
    @Id
    private String id;
    private String taskCode;
    private String taskKeyword;

    private String uid;
    private String createdAt;
    private String screenName;
    private String gender;
    private String birthday;
    private String constellation;
    private String province;
    private String city;
    private String description;
    private String profileUrl;
    private Integer verified;
    private String creditLevel;

    private Integer followersCount;
    private String followersCountStr;
    private Integer followsCount;
    private String followsCountStr;
    private Integer weibosCount;
    private String weibosCountStr;

    public MongoWeiboUser() {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public String getFollowersCountStr() {
        return followersCountStr;
    }

    public void setFollowersCountStr(String followersCountStr) {
        this.followersCountStr = followersCountStr;
    }

    public Integer getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(Integer followsCount) {
        this.followsCount = followsCount;
    }

    public String getFollowsCountStr() {
        return followsCountStr;
    }

    public void setFollowsCountStr(String followsCountStr) {
        this.followsCountStr = followsCountStr;
    }

    public Integer getWeibosCount() {
        return weibosCount;
    }

    public void setWeibosCount(Integer weibosCount) {
        this.weibosCount = weibosCount;
    }

    public String getWeibosCountStr() {
        return weibosCountStr;
    }

    public void setWeibosCountStr(String weibosCountStr) {
        this.weibosCountStr = weibosCountStr;
    }
}
