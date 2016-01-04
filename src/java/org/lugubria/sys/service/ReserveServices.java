/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.RecordProductDto;
import org.lugubria.sys.service.facade.IReserveRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class ReserveServices implements IReserveRepository{
    
    final private RestTemplate rt;
    final private Apli apli;
    final private Gson gson;
    final private String urlReserveRest;

    public ReserveServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlReserveRest = apli.getUrl() + "reserve/";
    }

    @Override
    public List<RecordProductDto> getListProductReserve(int branchId) {
        String urlListProduct = urlReserveRest + "getListProductReserve?branchId=" + branchId;

        String result12 = rt.getForObject(urlListProduct, String.class);
        

        Type collectionType = new TypeToken<List<RecordProductDto>>() {
        }.getType();
        List<RecordProductDto> listProduct = gson.fromJson(result12, collectionType);
        
        return listProduct;
    }
}
