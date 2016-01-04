/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.lugubria.sys.config.Apli;
import java.lang.reflect.Type;
import org.lugubria.sys.domain.BuyProducts;
import org.lugubria.sys.service.facade.IBuyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class BuyServices implements IBuyRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlBuy;

    public BuyServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlBuy = apli.getUrl() + "buy/";
    }

    @Override
    public boolean saveBuy(BuyProducts buyProducts) {
        String urlCreateCategory = urlBuy + "save";
        String json = gson.toJson(buyProducts);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateCategory, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public BuyProducts getBuyProductByCreditId(int creditId) {
        String urlListCreditByCriteria = urlBuy + "getListByCreditId?creditId=" + creditId;

        String result12 = rt.getForObject(urlListCreditByCriteria, String.class);
        BuyProducts buyProducts = new BuyProducts();
        try {
            Type collectionType = new TypeToken<BuyProducts>() {
            }.getType();
            buyProducts = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return buyProducts;
    }
}
