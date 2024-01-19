package org.metasphere.adminservice.model.pojo;

import org.metasphere.adminservice.context.constant.MSConstant;
import org.metasphere.adminservice.util.UUIDUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:05
 * @Modified By:
 */
@MappedSuperclass
public class MetaSphereEntity implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;
    /**
     * 逻辑主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 分布式主键
     */
    private String objectId;
    /**
     * 数据状态（1-可用/0-已删除）
     */
    private Integer status;
    /**
     * 数据入库时间
     */
    private LocalDateTime createAt;
    /**
     * 数据最后一次修改时间
     */
    private LocalDateTime updateAt;

    public MetaSphereEntity() {
        LocalDateTime dateTime = LocalDateTime.now().withNano(0);
        this.objectId = UUIDUtils.getMSObjectId();
        this.status = MSConstant.MetaSphereEntity.Status.ENABLED;
        this.createAt = dateTime;
        this.updateAt = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
