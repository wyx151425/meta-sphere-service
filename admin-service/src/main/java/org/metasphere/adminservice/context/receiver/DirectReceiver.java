package org.metasphere.adminservice.context.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.service.daq.DaqTaskService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-30 14:57
 * @Modified By:
 */
@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "directQueue", autoDelete = "false"),
        exchange = @Exchange(value = "directExchange"), key = "directRoutingKey"))
public class DirectReceiver {

    @Autowired
    private DaqTaskService daqTaskService;

    @RabbitHandler
    public void receiverMessage(String daqTaskCode, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("class: {}, daqTaskCode: {}", "DirectReceiver", daqTaskCode);
        // 执行数据导入
        daqTaskService.executeImportDaqTaskAcquiredData(daqTaskCode);
        // 数据导入完毕后，手动签收，channel.basicAck(消息唯一标识, 是否批量签收)
        channel.basicAck(deliveryTag, false);
    }
}
