package org.metasphere.adminservice.model.vo.req;

import lombok.Data;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 19:01
 * @Modified By:
 */
@Data
public class UserLoginVO {
    private String email;
    private String password;
}
