package org.metasphere.adminservice.model.bo.daq;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-15 16:30
 * @Modified By:
 */
@Document(collection = "weibo_comment")
public class MongoWeiboComment {
    @Id
    private String id;
    private String taskCode;
    private String taskKeyword;

    private String cid;
    private String mid;
    private String uid;

    private String textRaw;
    private String text;
    private Integer likesCount;
    private String source;
    private String createdAt;

    public MongoWeiboComment() {
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
