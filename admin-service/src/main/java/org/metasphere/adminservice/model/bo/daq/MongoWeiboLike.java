package org.metasphere.adminservice.model.bo.daq;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-15 16:31
 * @Modified By:
 */
@Document(collection = "weibo_like")
public class MongoWeiboLike {
    @Id
    private String id;
    private String taskCode;
    private String taskKeyword;

    private String mid;
    private String uid;
    private Integer like;

    public MongoWeiboLike() {
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

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
