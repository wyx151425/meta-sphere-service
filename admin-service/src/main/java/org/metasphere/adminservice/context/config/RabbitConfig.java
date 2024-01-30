package org.metasphere.adminservice.context.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-30 15:08
 * @Modified By:
 */
@Configuration
public class RabbitConfig {
    // 配置交换机
    /**
     * Direct交换机：directExchange
     */
    @Bean
    public static DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * Topic交换机：topicExchange
     */
    @Bean
    public static TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * fanout交换机：fanoutExchange
     */
    @Bean
    public static FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public static HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }

    // 配置消息队列
    public static String directQueue = "directQueue";

    /**
     * 消息队列：directQueue
     */
    @Bean
    public static Queue directQueue() {
        return new Queue(directQueue, true);
    }

    /**
     * 绑定将队列和交换机绑定, 并设置用于匹配键：directRoutingKey
     */
    @Bean
    Binding bindingQueue() {
        return BindingBuilder.bind(directQueue())
                .to(directExchange())
                .with("directRoutingKey");
    }
}

