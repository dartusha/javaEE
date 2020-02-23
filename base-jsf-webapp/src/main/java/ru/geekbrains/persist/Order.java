package ru.geekbrains.persist;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private long id;
    private long userId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String createDate;
    private String deliveryDate;
    private String comments;
    private int status; //1 - created, 2 - confirmed, 3- courier assigned, 4 - delivered
    private String statusName;
    private int courierId;
    private BigDecimal amount;
    private List<OrderDetails> orderDetails;

    public Order(){

    }

    public Order(long id, String name, String phone, String email, String address,
                 String createDate,String deliveryDate, String comments, BigDecimal amount, List<OrderDetails> orderDetails, String statusName){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.address=address;
        this.createDate=createDate;
        this.deliveryDate=deliveryDate;
        this.comments=comments;
        this.amount=amount;
        this.orderDetails=orderDetails;
        this.statusName = statusName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
