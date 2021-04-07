package com.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerPageController {
    @GetMapping(value="/seller")
    public String admin(){
        return "redirect:seller_category_list";
    }

    @GetMapping(value="/seller_category_list")
    public String listCategory(){
        return "seller/listCategory";
    }

    @GetMapping(value="/seller_order_list")
    public String listOrder(){
        return "seller/listOrder";

    }

    @GetMapping(value="/seller_product_list")
    public String listProduct(){
        return "seller/listProduct";

    }

    @GetMapping(value="/seller_product_edit")
    public String editProduct(){
        return "seller/editProduct";

    }

    @GetMapping(value="/seller_productImage_list")
    public String listProductImage(){
        return "seller/listProductImage";

    }

    @GetMapping(value="/seller_propertyValue_edit")
    public String editPropertyValue(){
        return "seller/editPropertyValue";

    }
}