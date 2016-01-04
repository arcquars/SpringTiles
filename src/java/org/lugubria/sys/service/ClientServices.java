/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.dto.client.ClientDto;
import org.lugubria.sys.service.facade.IClientRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.lang.reflect.Type;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class ClientServices implements IClientRepository{

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlClient;
    
    public ClientServices(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlClient = apli.getUrl() + "client/";
    }
    
    @Override
    public ClientDto getClientFromCi(int ci) {
        String urlGetClientByCi = urlClient + "getClient?ci=" + ci;

        ClientDto client = rt.getForObject(urlGetClientByCi, ClientDto.class);
        return client;
    }

    @Override
    public void updateClient(ClientDto client) {
        String urlCreateClient = urlClient;
        String json = gson.toJson(client);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlCreateClient, request);
    }

    @Override
    public List<ClientDto> getClientByCriteria(int type, String criteria) {
        String urlListProductByCriteria = urlClient + "listClientDto?type=" + type + "&criteria=" + criteria;

        String result12 = rt.getForObject(urlListProductByCriteria, String.class);
        

        Type collectionType = new TypeToken<List<ClientDto>>() {
        }.getType();
        List<ClientDto> userss = gson.fromJson(result12, collectionType);
        return userss;
    }

    @Override
    public String getStringClientByCriteria(int type, String criteria) {
        String urlListProductByCriteria = urlClient + "listClientDto?type=" + type + "&criteria=" + criteria;

        String result12 = rt.getForObject(urlListProductByCriteria, String.class);
        return result12;
    }
}
