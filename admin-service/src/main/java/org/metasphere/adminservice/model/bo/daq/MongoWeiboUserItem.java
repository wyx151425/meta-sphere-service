package org.metasphere.adminservice.model.bo.daq;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 16:18
 * @Modified By:
 */
public class MongoWeiboUserItem {

    private String id;
    private String idStr;
    private String screenName;
    private String gender;
    private String location;
    private String description;
    private String profileUrl;
    private String profileImageUrl;
    private String avatarLargeUrl;
    private String avatarHdUrl;
    private String followersCount;
    private String followersCountStr;
    private String friendsCount;
    private String friendsCountStr;
    private String statusesCount;
    private String statusesCountStr;

    public MongoWeiboUserItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
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

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getAvatarLargeUrl() {
        return avatarLargeUrl;
    }

    public void setAvatarLargeUrl(String avatarLargeUrl) {
        this.avatarLargeUrl = avatarLargeUrl;
    }

    public String getAvatarHdUrl() {
        return avatarHdUrl;
    }

    public void setAvatarHdUrl(String avatarHdUrl) {
        this.avatarHdUrl = avatarHdUrl;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getFollowersCountStr() {
        return followersCountStr;
    }

    public void setFollowersCountStr(String followersCountStr) {
        this.followersCountStr = followersCountStr;
    }

    public String getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(String friendsCount) {
        this.friendsCount = friendsCount;
    }

    public String getFriendsCountStr() {
        return friendsCountStr;
    }

    public void setFriendsCountStr(String friendsCountStr) {
        this.friendsCountStr = friendsCountStr;
    }

    public String getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(String statusesCount) {
        this.statusesCount = statusesCount;
    }

    public String getStatusesCountStr() {
        return statusesCountStr;
    }

    public void setStatusesCountStr(String statusesCountStr) {
        this.statusesCountStr = statusesCountStr;
    }
}
