package org.metasphere.adminservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-21 19:48
 * @Modified By:
 */
@Configuration
public class MetaSphereConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
