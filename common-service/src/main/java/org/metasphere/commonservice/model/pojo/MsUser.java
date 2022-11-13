package org.metasphere.commonservice.model.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:08
 * @Modified By:
 */
@Data
@Entity
@Table(name = "user")
public class MsUser extends MsEntity {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobilePhoneNumber;
}
