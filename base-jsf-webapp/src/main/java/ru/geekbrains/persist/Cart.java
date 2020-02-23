package ru.geekbrains.persist;

import java.math.BigDecimal;

public class Cart {
    private long productId;
    private BigDecimal amount;
    private long cnt;
    private String productName;
    private int rowid;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    private BigDecimal totalAmount;

    public Cart(){

    }

    public Cart(long productId, BigDecimal amount, long cnt){
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
    }

    public Cart(long productId, BigDecimal amount, long cnt, String productName, int rowid, BigDecimal totalAmount){
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
        this.productName=productName;
        this.rowid=rowid;
        this.totalAmount=totalAmount;
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
        this.amount=amount;
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
}
