package org.metasphere.portalservice.model.pojo.auth;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.metasphere.portalservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 10:57
 * @Modified By:
 */
@Entity
@Table(name = "ms_role")
@Where(clause = "status = 1")
@SQLDelete(sql = "UPDATE ms_role SET status = 0 WHERE id = ?")
public class Role extends MetaSphereEntity {
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;

    public Role() {
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
