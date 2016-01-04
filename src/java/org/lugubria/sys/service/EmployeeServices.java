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
import org.lugubria.sys.config.CollectionsPosition;
import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.dto.employee.EmployeeDto;
import org.lugubria.sys.domain.dto.employee.EmployeeSimpleDto;
import org.lugubria.sys.service.facade.IEmployeeRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class EmployeeServices implements IEmployeeRepository{

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlEmployee;
    private CollectionsPosition mapPosition;

    public EmployeeServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlEmployee = apli.getUrl() + "employee/";
        mapPosition = (CollectionsPosition) ctx.getBean("collectionsPosition");
    }
    
    @Override
    public boolean createEmployee(Employee employee) {
        String urlCreateEmployee = urlEmployee;
        String json = gson.toJson(employee);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlCreateEmployee, request);
        return true;
    }

    @Override
    public Map<String, String> getPosition() {
        return mapPosition.getMap();
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        String json = gson.toJson(employee);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json,headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlEmployee+"updateEmployee", request);
        return true;
    }

    @Override
    public List<EmployeeSimpleDto> listAllVendor() {
        String urlListAllVendor = urlEmployee + "getListAllVendor";

        String result12 = rt.getForObject(urlListAllVendor, String.class);
        

        Type collectionType = new TypeToken<List<EmployeeSimpleDto>>() {
        }.getType();
        List<EmployeeSimpleDto> listAll = gson.fromJson(result12, collectionType);
        return listAll;
    }
    
    @Override
    public String ciExistEmployee(int ci) {
        String urlExistEmployee = urlEmployee + "existCiEmployee?ci=" + ci;

        String result12 = rt.getForObject(urlExistEmployee, String.class);
        return result12;
    }

    @Override
    public boolean createEmployee(EmployeeDto employee) {
        String urlCreateEmployee = urlEmployee+"emplSaveDto";
        String json = gson.toJson(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlCreateEmployee, request);
        return true;
    }

    @Override
    public List<KeyValue> getAllRols() {
        String urlListAllRol = urlEmployee + "getAllRols";

        String result12 = rt.getForObject(urlListAllRol, String.class);
        

        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> listAll = gson.fromJson(result12, collectionType);
        return listAll;
    }
}
