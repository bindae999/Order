package mydelivery;

public class OrderRequested extends AbstractEvent {

    private Long orderId;
    private String foodName;
    private Long foodQty;
    private String orderStatus;

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