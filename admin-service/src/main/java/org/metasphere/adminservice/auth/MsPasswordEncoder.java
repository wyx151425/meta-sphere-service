package org.metasphere.adminservice.auth;

import org.metasphere.adminservice.util.Md5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 17:20
 * @Modified By:
 */
@Component
public class MsPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return Md5Utils.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(Md5Utils.encrypt(rawPassword.toString()));
    }
}
