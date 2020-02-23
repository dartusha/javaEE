package ru.geekbrains.controller;

import ru.geekbrains.persist.Cart;
import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

@SessionScoped
@Named
public class CartController implements Serializable {

    private HashMap<Long,Cart> cartList=new HashMap<Long, Cart>();
    private Integer cnt=0;
    private BigDecimal sm=new BigDecimal(0);

    public BigDecimal getSm() {
        sm=new BigDecimal(0);
        for (HashMap.Entry<Long,Cart> pair: cartList.entrySet()) {
            sm=sm.add(pair.getValue().getTotalAmount());
        }
        return sm;
    }

    public HashMap<Long, Cart> getCartList() {
        return cartList;
    }

    public void setCartList(HashMap<Long, Cart> cartList) {
        this.cartList = cartList;
    }

    public void buyProduct(Product product){
        Cart cart=new Cart();
        cart.setRowid(++cnt);
        cart.setProductId(product.getId());
        cart.setProductName(product.getName());
        if (cartList.containsKey(product.getId()))
           cart.setCnt(cartList.get(product.getId()).getCnt()+1);
        else
            cart.setCnt(1L);
        cart.setAmount(product.getPrice());
        cart.setTotalAmount(cart.getAmount().multiply(new BigDecimal(cart.getCnt())));
        cartList.put(product.getId(),cart);
    }

    public void emptyCart(){
        cartList.clear();
    }

    public void deleteCart(Long ln){
        cartList.remove(ln);
    }

    public void changeCnt(Cart cart){
        cart.setTotalAmount(cart.getAmount().multiply(new BigDecimal(cart.getCnt())));
        cartList.put(cart.getProductId(),cart);
    }

    public void createOrder(){

    }


}
