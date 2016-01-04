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
import org.lugubria.sys.domain.Factory;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.service.facade.IFactoryRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class FactoryServices implements IFactoryRepository{

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlFactory;

    public FactoryServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlFactory = apli.getUrl() + "factory/";
    }
    
    
    @Override
    public List<KeyValue> getAllFactory() {
        String urlAllcategory = urlFactory + "getAllFactory";

        String result12 = rt.getForObject(urlAllcategory, String.class);

        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> kvFactory = gson.fromJson(result12, collectionType);
        return kvFactory;
    }

    @Override
    public boolean factoryCreate(Factory factory) {
        String urlCreateFactory = urlFactory + "save";
        String json = gson.toJson(factory);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateFactory, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public boolean factoryValidateName(String newName) {
        String urlValidFactory = urlFactory + "validName?newName=" + newName;

        String resultValid = rt.getForObject(urlValidFactory, String.class);

        return Boolean.parseBoolean(resultValid);
    }
    
}
