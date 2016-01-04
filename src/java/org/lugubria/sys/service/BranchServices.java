/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.lugubria.sys.business.Branch;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.service.facade.IBranchRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class BranchServices implements IBranchRepository {

    private final RestTemplate rt;
    private final Apli apli;
    private final Gson gson;
    private final String urlBranch;

    public BranchServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlBranch = apli.getUrl() + "branch/";
    }

    @Override
    public List<Branch> searchByCriteria(String criteria) {
        String urlListBranchByCriteria = urlBranch + "findByCriteria?criteria=" + criteria;

        String result12 = rt.getForObject(urlListBranchByCriteria, String.class);

        List<Branch> listBranch = new ArrayList<Branch>();
        try {
            Type collectionType = new TypeToken<List<Branch>>() {
            }.getType();
            listBranch = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listBranch;
    }

    @Override
    public boolean branchDelete(int catId) {
        String urlDeleteBranch = urlBranch + catId;
        boolean resultDelete = false;
        ResponseEntity<String> exchange = rt.exchange(urlDeleteBranch, HttpMethod.DELETE, null, String.class);
        if(exchange.getBody().equalsIgnoreCase("true"))
            resultDelete=true;
        return resultDelete;
    }

    @Override
    public boolean branchUpdate(Branch branch) {
        boolean resultUpdate = false;
        
        String json = gson.toJson(branch);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        ResponseEntity<String> exchange = rt.exchange(urlBranch, HttpMethod.POST, request, String.class);

        return Boolean.parseBoolean(exchange.getBody());
    }

    @Override
    public boolean branchCreate(Branch branch) {
        String urlCreateBranch = urlBranch + "save";
        String json = gson.toJson(branch);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateBranch, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public boolean branchValidateName(String newName, int branchId) {
        String urlBranchValidName = urlBranch + "validName?newName=" + newName+"&branchId="+branchId;

        String result12 = rt.getForObject(urlBranchValidName, String.class);
        boolean validname = Boolean.parseBoolean(result12);
        return validname;
    }

    @Override
    public List<KeyValue> getAllBranch() {
        String urlAllBranch = urlBranch + "getAllBranch";

        String result12 = rt.getForObject(urlAllBranch, String.class);

        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> userss = gson.fromJson(result12, collectionType);
        return userss;
    }

    @Override
    public List<KeyValue> getAllBranchWithMain() {
        String urlAllBranch = urlBranch + "getAllBranchWithMain";

        String result12 = rt.getForObject(urlAllBranch, String.class);

        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> listBranchWithMain = gson.fromJson(result12, collectionType);
        return listBranchWithMain;
    }

    @Override
    public boolean branchValidateName(String newName) {
        String urlBranchValidName = urlBranch + "validNameBranch?newName=" + newName;

        String result12 = rt.getForObject(urlBranchValidName, String.class);
        boolean validname = Boolean.parseBoolean(result12);
        return validname;
    }
}
