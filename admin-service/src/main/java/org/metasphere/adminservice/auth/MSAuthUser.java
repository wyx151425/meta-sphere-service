package org.metasphere.adminservice.auth;

import lombok.Getter;
import lombok.Setter;
import org.metasphere.adminservice.model.pojo.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 18:11
 * @Modified By:
 */
@Getter
@Setter
public class MSAuthUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public MSAuthUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}
