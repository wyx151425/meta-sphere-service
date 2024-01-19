package org.metasphere.portalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.metasphere.portalservice.model.pojo.auth.User;

import java.security.Principal;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-12-16 17:13
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StompAuthenticatedUser implements Principal {

    private User user;

    @Override
    public String getName() {
        return null;
    }
}
