package org.metasphere.adminservice.model.pojo;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.metasphere.adminservice.util.ConstUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
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
@Where(clause = "status = 1")
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
        this.status = ConstUtils.MSEntity.Status.ENABLE;
        this.createAt = dateTime;
        this.updateAt = dateTime;
    }
}
