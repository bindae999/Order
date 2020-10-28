package mydelivery;

public class RequestCanceled extends AbstractEvent {

    private Long orderId;
    private String orderStatus;

    public RequestCanceled(){
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long id) {
        this.orderId = id;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
