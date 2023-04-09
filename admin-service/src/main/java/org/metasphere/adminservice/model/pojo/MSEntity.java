package org.metasphere.adminservice.model.pojo;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.metasphere.adminservice.config.SoftDelete;
import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.util.ConstUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:05
 * @Modified By:
 */
@Data
@MappedSuperclass
public class MSEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objectId;
    private Integer status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public MSEntity() {
        LocalDateTime dateTime = LocalDateTime.now().withNano(0);
        this.objectId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.status = MSConstant.MSEntity.Status.ENABLED;
        this.createAt = dateTime;
        this.updateAt = dateTime;
    }
}
