/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import org.lugubria.sys.domain.BuyProducts;
import org.lugubria.sys.service.BuyServices;
import org.lugubria.sys.service.CreditServices;
import org.lugubria.sys.service.ProviderServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping(value = "buypp")
public class BuyController {
    
    private BuyServices buyServices;

    public BuyController() {
        this.buyServices = new BuyServices();
    }
    
    @RequestMapping(value = "getListBuyProductByCreditId", method = RequestMethod.POST)
    public @ResponseBody String paymentSaveAjax(@RequestParam int creditId) {
        BuyProducts list = buyServices.getBuyProductByCreditId(creditId);
        Gson gson = new Gson();
        
        return gson.toJson(list);
    }
}
