package mydelivery;

import mydelivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRequestCanceled_CancelRequest(@Payload RequestCanceled requestCanceled){

        if(requestCanceled.isMe()){
            System.out.println("##### listener CancelRequest : " + requestCanceled.toJson());

            //pss : 접수취소 수신시 객체 생성(update)
            Iterator<Order> iterator = orderRepository.findAll().iterator();
            while(iterator.hasNext()){
                Order orderTmp = iterator.next();
                if(orderTmp.getOrderId() == requestCanceled.getOrderId()){
                    Optional<Order> ShopOptional = orderRepository.findById(orderTmp.getOrderId());
                    Order order = ShopOptional.get();
                    order.setOrderStatus(requestCanceled.getOrderStatus());
                    orderRepository.save(order);
                }
            }

        }
    }


}
