package org.metasphere.portalservice.model.pojo.deduction;

import org.metasphere.portalservice.model.pojo.MetaSphereEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description: 干预措施类
 * @Date: Created in 2024-01-19 10:17
 * @Modified By:
 */
@Entity
@Table(name = "ms_intervention")
public class Intervention extends MetaSphereEntity {
    /**
     * 推演室编号
     */
    private String deductionRoomCode;
    /**
     * 干预角色编号
     */
    private String roleCode;
    /**
     * 干预措施编号
     */
    private String measureCode;
    /**
     * 发表言论的内容
     */
    private String speechContent;

    public Intervention() {
    }

    public String getDeductionRoomCode() {
        return deductionRoomCode;
    }

    public void setDeductionRoomCode(String deductionRoomCode) {
        this.deductionRoomCode = deductionRoomCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMeasureCode() {
        return measureCode;
    }

    public void setMeasureCode(String measureCode) {
        this.measureCode = measureCode;
    }

    public String getSpeechContent() {
        return speechContent;
    }

    public void setSpeechContent(String speechContent) {
        this.speechContent = speechContent;
    }
}
