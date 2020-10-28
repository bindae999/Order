package mydelivery;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;

    private String foodName;
    private Long foodQty;
    private String orderStatus;



    @PostPersist
    public void onPostPersist(){
        OrderRequested orderRequested = new OrderRequested();
        BeanUtils.copyProperties(this, orderRequested);
        //pss : 오더주문할때는  기본값  "Order"로 세팅
        orderRequested.setOrderStatus("Order");
        orderRequested.setOrderId(this.getOrderId());
        orderRequested.publishAfterCommit();

    }

    @PreUpdate
    public void onPreUpdate(){

        System.out.println("주문변경!!");
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);

        if (orderCanceled.getOrderStatus().equals("Cancel")){
            System.out.println("주문취소 : "+orderCanceled.getOrderId());
            orderCanceled.publishAfterCommit();

        }

    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public Long getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(Long foodQty) {
        this.foodQty = foodQty;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


}
