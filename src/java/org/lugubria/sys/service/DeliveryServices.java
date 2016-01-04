/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.DeliveryBranch;
import org.lugubria.sys.service.facade.IDeliveryRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class DeliveryServices implements IDeliveryRepository{

     private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlDelivery;

    public DeliveryServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlDelivery = apli.getUrl() + "delivery/";
    }
    @Override
    public boolean saveDelivery(DeliveryBranch db) {
        String urlCreateDelivery = urlDelivery + "saveDelivery";
        String json = gson.toJson(db);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateDelivery, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }
    
}
