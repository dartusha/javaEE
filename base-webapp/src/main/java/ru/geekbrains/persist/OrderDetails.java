package ru.geekbrains.persist;

import java.math.BigDecimal;

public class OrderDetails {
    private long orderId;
    private long productId;
    private BigDecimal amount;
    private long cnt;
    private String productName;
    private int rowid;
    private BigDecimal amountPerOne;

    public OrderDetails(){

    }

    public OrderDetails(long orderId, long productId, BigDecimal amount, long cnt){
        this.orderId=orderId;
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
    }

    public OrderDetails(long orderId, long productId, BigDecimal amount, long cnt, String productName, int rowid){
        this.orderId=orderId;
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
        this.productName=productName;
        this.rowid=rowid;
    }

    public OrderDetails(long orderId, long productId, BigDecimal amount, long cnt, String productName, int rowid,BigDecimal amountPerOne){
        this.orderId=orderId;
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
        this.productName=productName;
        this.rowid=rowid;
        this.amountPerOne=amountPerOne;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public BigDecimal getAmountPerOne() {
        return amountPerOne;
    }

    public void setAmountPerOne(BigDecimal amountPerOne) {
        this.amountPerOne = amountPerOne;
    }
}
