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
@Table(name = "ms_permission")
@SQLDelete(sql = "UPDATE ms_permission SET status = 0 WHERE id = ?")
public class Permission extends MSEntity {
    private String code;
    private String name;
}
