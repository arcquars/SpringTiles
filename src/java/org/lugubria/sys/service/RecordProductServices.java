/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.RecordProductDto;
import org.lugubria.sys.domain.RecordProductList;
import org.lugubria.sys.service.facade.IRecordProductRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class RecordProductServices implements IRecordProductRepository{

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlRecordProduct;

    public RecordProductServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlRecordProduct = apli.getUrl() + "recordProduct/";
    }
    
    
    
    @Override
    public RecordProductList getListByCriteria(int type, String criteria) {
        String urlListProductByCriteria = urlRecordProduct + "findByCriteria?type=" + type + "&criteria=" + criteria;

        String result12 = rt.getForObject(urlListProductByCriteria, String.class);
        

        Type collectionType = new TypeToken<RecordProductList>() {
        }.getType();
        RecordProductList rpList = gson.fromJson(result12, collectionType);
        
        return rpList;
    }
    
    @Override
    public List<RecordProductDto> getListByCriteriaPrint(int type, String criteria) {
        String urlListProductByCriteria = urlRecordProduct + "findByCriteriaPrint?type=" + type + "&criteria=" + criteria;

        String result12 = rt.getForObject(urlListProductByCriteria, String.class);
        

        Type collectionType = new TypeToken<List<RecordProductDto>>() {
        }.getType();
        List<RecordProductDto> userss = gson.fromJson(result12, collectionType);
        
        return userss;
    }
    
}
