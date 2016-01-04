/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.ProviderPersonDto;
import org.lugubria.sys.service.facade.IProviderRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class ProviderServices implements IProviderRepository{

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlProvider;

    public ProviderServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlProvider = apli.getUrl() + "provider/";
    }
    
    
    @Override
    public boolean providerValidateName(String newName) {
        String urlValidCategory = urlProvider + "validName?newName="+newName;

        String resultValid = rt.getForObject(urlValidCategory,String.class);
        
        return Boolean.parseBoolean(resultValid);
    }

    @Override
    public boolean createProviderPerson(ProviderPersonDto ppd) {
        String urlCreateProvider = urlProvider + "save";
        String json = gson.toJson(ppd);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateProvider, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public List<KeyValue> getAllProvider() {
        String urlAllProvider = urlProvider + "getAllProvider";

        String result12 = rt.getForObject(urlAllProvider, String.class);

        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> userss = gson.fromJson(result12, collectionType);
        return userss;
    }
    
}
