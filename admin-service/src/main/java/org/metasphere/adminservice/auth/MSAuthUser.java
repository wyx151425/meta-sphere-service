package org.metasphere.adminservice.auth;

import lombok.Getter;
import lombok.Setter;
import org.metasphere.adminservice.model.pojo.MSUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 18:11
 * @Modified By:
 */
@Getter
@Setter
public class MSAuthUser extends User {

    private MSUser msUser;

    public MSAuthUser(MSUser msUser, Collection<? extends GrantedAuthority> authorities) {
        super(msUser.getEmail(), msUser.getPassword(), authorities);
        this.msUser = msUser;
    }
}
