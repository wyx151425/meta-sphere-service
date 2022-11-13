package org.metasphere.commonservice.util;

import org.junit.jupiter.api.Test;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 11:16
 * @Modified By:
 */
public class JwtUtilsTests {

    @Test
    void testCreateToken() {
        String token = JwtUtils.generateToken(1L, "wyx151425@163.com");
        System.out.println(token);
        // eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJScgwN8dANDXYNUtJRSq0oULIyNDOzMDY0MLAw11EqLU4t8kwBigHlchMzc4DqyysrDE0NTYxMHQzNjPWS83OVagGWXVGUSwAAAA.z22FQ5-lthFuMf9-m6RvcDZrKDkcawILNZeYywIrye0
    }

    @Test
    void testGetUserIdByToken() {
        Long userId = JwtUtils.getUserIdByToken("eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJScgwN8dANDXYNUtJRSq0oULIyNDOzMDY0MLAw11EqLU4t8kwBigHlchMzc4DqyysrDE0NTYxMHQzNjPWS83OVagGWXVGUSwAAAA.z22FQ5-lthFuMf9-m6RvcDZrKDkcawILNZeYywIrye0");
        System.out.println(userId);
    }

    @Test
    void testGetEmailByToken() {
        String email = JwtUtils.getUserEmailByToken("eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJScgwN8dANDXYNUtJRSq0oULIyNDOzMDY0MLAw11EqLU4t8kwBigHlchMzc4DqyysrDE0NTYxMHQzNjPWS83OVagGWXVGUSwAAAA.z22FQ5-lthFuMf9-m6RvcDZrKDkcawILNZeYywIrye0");
        System.out.println(email);
    }
}
