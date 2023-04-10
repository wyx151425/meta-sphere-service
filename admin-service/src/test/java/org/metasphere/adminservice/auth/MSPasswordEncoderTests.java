package org.metasphere.adminservice.auth;

import org.junit.jupiter.api.Test;
import org.metasphere.adminservice.util.Md5Utils;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 17:24
 * @Modified By:
 */
public class MSPasswordEncoderTests {

    @Test
    void testMD5Encoder() {
        String encoded = Md5Utils.encrypt("wyx151425");
        System.out.println(encoded);

        System.out.println(encoded.equals(Md5Utils.encrypt("wyx151425")));
    }
}
