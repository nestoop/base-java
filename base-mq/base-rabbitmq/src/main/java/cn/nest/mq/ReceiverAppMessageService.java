package cn.nest.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by botter
 *
 * @Date 11/11/16.
 * @description
 */
@Service
@RabbitListener(queues = RabbitMQConfig.MQ_APP_QUEUE)
public class ReceiverAppMessageService {

    @RabbitHandler
    public void receiverAppMessage (Object message){
        Message appMessage = (Message) message;
        System.out.println("app message :" + new String(appMessage.getBody()));
    }
}
