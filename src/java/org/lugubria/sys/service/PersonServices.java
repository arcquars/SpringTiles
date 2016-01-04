/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.crypt.AESCrypt;
import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.domain.EmployeeDetailtDto;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.Person;
import org.lugubria.sys.service.facade.IPersonRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class PersonServices implements IPersonRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlPerson;

    public PersonServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlPerson = apli.getUrl() + "person/";
    }

    @Override
    public Employee getEmployeeByperId(int perId) {
        String urlPersonById = urlPerson + "personbyId?perId=" + perId;
        String resultRest = rt.getForObject(urlPersonById, String.class);
        Employee employee = gson.fromJson(resultRest, Employee.class);
        try {
            employee.setPassword(AESCrypt.decrypt(employee.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(PersonServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    @Override
    public List<EmployeeDetailtDto> getPersonByCriteria(int type, String criteria) {
        String urlListPersonByCriteria = urlPerson + "findByCriteria?type=" + type + "&criteria=" + criteria;

        System.out.println("1");
        String result12 = rt.getForObject(urlListPersonByCriteria, String.class);

        System.out.println("2");
        List<EmployeeDetailtDto> userss = new ArrayList<EmployeeDetailtDto>();
        System.out.println("3");
        try {
            Type collectionType = new TypeToken<List<EmployeeDetailtDto>>() {
            }.getType();
            System.out.println("4");
            userss = gson.fromJson(result12, collectionType);
            System.out.println("5");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return userss;
    }

    @Override
    public void updatePerson(Person person) {
        String json = gson.toJson(person);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlPerson, request);
    }

    @Override
    public void deletePerson(int perId) {
        String urlDeletePerson = urlPerson + perId;
        rt.delete(urlDeletePerson);
    }

    @Override
    public boolean createEmployee(Employee person) {
        String urlCreatePerson = urlPerson + "save";
        String json = gson.toJson(person);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlCreatePerson, request);
        return true;
    }

    @Override
    public boolean validCi(int newCi) {
        String urlUserValid = urlPerson + "validCi?newCi=" + newCi;

        String result12 = rt.getForObject(urlUserValid, String.class);

        Boolean validdd = gson.fromJson(result12, Boolean.class);
        return validdd.booleanValue();
    }

    @Override
    public String getFirstNameFromCi(int ci) {
        String urlUserValid = urlPerson + "getFirstNameByCi?ci=" + ci;

        String result12 = rt.getForObject(urlUserValid, String.class);
        
        //String firstname = gson.fromJson(result12, String.class);
        //return firstname;
        return result12;
    }
    
    @Override
    public List<KeyValue> getAllBranch() {
        String urlAllBranch = urlPerson + "branch";

        String result12 = rt.getForObject(urlAllBranch, String.class);
        System.out.println("wwww:: "+result12);
        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> allBranch = gson.fromJson(result12, collectionType);
        return allBranch;
    }

    @Override
    public Map<String, String> getAllEmployeePosition() {
        String urlAllBranch = urlPerson + "employeePosition";

        String result12 = rt.getForObject(urlAllBranch, String.class);

        Type collectionType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> allEmployeePosition = gson.fromJson(result12, collectionType);
        return allEmployeePosition;
    }

    @Override
    public Person getPersonByUsernamePass(String username, String pass) {
        String urlPersonById = urlPerson + "personbyUsername?username=" + username+"&pass="+pass;
        System.out.println("url::::::::::::::: " + urlPersonById);
        String resultRest = rt.getForObject(urlPersonById, String.class);
        Person person = gson.fromJson(resultRest, Person.class);
        return person;
    }

    @Override
    public List<KeyValue> getBranchFree(int branchId) {
        String urlListBranch = urlPerson + "getBranchFree?branchId="+branchId;

        String result12 = rt.getForObject(urlListBranch, String.class);

        List<KeyValue> userss = new ArrayList<KeyValue>();
        try {
            Type collectionType = new TypeToken<List<KeyValue>>() {
            }.getType();
            userss = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return userss;
    }

    @Override
    public List<KeyValue> getBranchAllList() {
        String urlListBranch = urlPerson + "branchAllList";

        String result12 = rt.getForObject(urlListBranch, String.class);

        List<KeyValue> userss = new ArrayList<KeyValue>();
        try {
            Type collectionType = new TypeToken<List<KeyValue>>() {
            }.getType();
            userss = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return userss;
    }

    @Override
    public Person getPersonFromCi(int ci) {
        String urlGetPersonByCi = urlPerson + "getPersonByCi?ci=" + ci;

        Person person = rt.getForObject(urlGetPersonByCi, Person.class);
        
        return person;
    }

    @Override
    public Person updateP(Person person) {
        String json = gson.toJson(person);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlPerson+"updateP", request, String.class);
        return gson.fromJson(resultpost, Person.class);
        //rt.postForLocation(urlPerson+"updateP", request);
    }

}
