package ru.geekbrains.persist;

import java.math.BigDecimal;

public class Cart {
    private String sessionID;
    private long productId;
    private BigDecimal amount;
    private long cnt;
    private String productName;
    private int rowid;

    public Cart(){

    }

    public Cart(String sessionID, long productId, BigDecimal amount, long cnt){
        this.sessionID=sessionID;
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
    }

    public Cart(String sessionID, long productId, BigDecimal amount, long cnt, String productName, int rowid){
        this.sessionID=sessionID;
        this.productId=productId;
        this.amount=amount;
        this.cnt=cnt;
        this.productName=productName;
        this.rowid=rowid;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
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
