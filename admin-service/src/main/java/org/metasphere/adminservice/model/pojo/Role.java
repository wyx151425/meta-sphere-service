package org.metasphere.adminservice.model.pojo;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 10:57
 * @Modified By:
 */
@Data
@Entity
@Table(name = "ms_role")
@SQLDelete(sql = "UPDATE ms_role SET status = 0 WHERE id = ?")
public class Role extends MSEntity {
    private String code;
    private String name;
}
