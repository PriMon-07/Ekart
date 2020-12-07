package com.infosys.ekart.model;

import java.util.List;

public class CartValue {
	
	List<Cart> cartList;
    private Float totalprice;
    private Float totaldeliverycharge;
    private Float grandtotal;
    
    public Float getTotalprice() {
          return totalprice;
    }
    public void setTotalprice(Float totalprice) {
          this.totalprice = totalprice;
    }
    public Float getTotaldeliverycharge() {
          return totaldeliverycharge;
    }
    public void setTotaldeliverycharge(Float totaldeliverycharge) {
          this.totaldeliverycharge = totaldeliverycharge;
    }
    public Float getGrandtotal() {
          return grandtotal;
    }
    public void setGrandtotal(Float grandtotal) {
          this.grandtotal = grandtotal;
    }
    public List<Cart> getCartList() {
          return cartList;
    }
    public void setCartList(List<Cart> cartList) {
          this.cartList = cartList;
    }


}
