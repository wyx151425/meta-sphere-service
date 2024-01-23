package org.metasphere.portalservice.context.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-19 10:07
 * @Modified By:
 */
@Configuration
@EnableWebSocketMessageBroker
public class MSWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/commonDeduction", "/intervenedDeduction", "/interactiveDeduction");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/commonDeductionServer").setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("/intervenedDeductionServer").setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("/interactiveDeductionServer").setAllowedOriginPatterns("*").withSockJS();
    }
}
