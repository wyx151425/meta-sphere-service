package org.metasphere.adminservice.model.pojo;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 10:57
 * @Modified By:
 */
@Entity
@Table(name = "ms_permission")
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_permission SET status = 0 WHERE id = ?")
public class Permission extends MetaSphereEntity {
    /**
     * 权限编码
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;

    public Permission() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
