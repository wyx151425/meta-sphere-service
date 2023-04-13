package org.metasphere.adminservice.model.pojo.daq;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.metasphere.adminservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-13 15:55
 * @Modified By:
 */
@Entity
@Table(name = "ms_weibo_account_item")
@Where(clause = "status = 1")
public class WeiboAccountItem extends MetaSphereEntity {
    /**
     * 源平台中的ID
     */
    private String sourceId;
    /**
     * 微博昵称
     */
    private String screenName;
    /**
     * 性别(1男0女)
     */
    private Integer gender;
    /**
     * 所在地区
     */
    private String location;
    /**
     * 简介
     */
    private String description;
    /**
     * 微博数
     */
    private Integer weibosCount;
    /**
     * 关注数
     */
    private Integer followsCount;
    /**
     * 粉丝数
     */
    private Integer followersCount;
    /**
     * 微博主页URL
     */
    private String profileUrl;

    public WeiboAccountItem() {
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeibosCount() {
        return weibosCount;
    }

    public void setWeibosCount(Integer weibosCount) {
        this.weibosCount = weibosCount;
    }

    public Integer getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(Integer followsCount) {
        this.followsCount = followsCount;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
