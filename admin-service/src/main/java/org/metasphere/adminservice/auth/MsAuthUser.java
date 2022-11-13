package org.metasphere.adminservice.auth;

import lombok.Getter;
import lombok.Setter;
import org.metasphere.adminservice.model.pojo.MsUser;
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
public class MsAuthUser extends User {

    private MsUser msUser;

    public MsAuthUser(MsUser msUser, Collection<? extends GrantedAuthority> authorities) {
        super(msUser.getEmail(), msUser.getPassword(), authorities);
        this.msUser = msUser;
    }
}
