package org.metasphere.portalservice.model.pojo.deduction;

import org.metasphere.portalservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description: 推演群组类
 * @Date: Created in 2024-01-19 10:43
 * @Modified By:
 */
@Entity
@Table(name = "ms_deduction_group")
public class DeductionGroup extends MetaSphereEntity {
    /**
     * 推演室编号
     */
    private String code;
    /**
     * 推演主题
     */
    private String theme;
    /**
     * 推演室类型
     */
    private Integer type;
    /**
     * 干预等级
     */
    private Integer intervenedLevel;

    public DeductionGroup() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIntervenedLevel() {
        return intervenedLevel;
    }

    public void setIntervenedLevel(Integer intervenedLevel) {
        this.intervenedLevel = intervenedLevel;
    }
}
