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
import org.lugubria.sys.business.Category;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.service.facade.ICategoryRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class CategoryServices implements ICategoryRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlCategory;

    public CategoryServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlCategory = apli.getUrl() + "category/";
    }

    @Override
    public List<Category> searchByCriteria(String criteria) {
        String urlListCategoryByCriteria = urlCategory + "findByCriteria?criteria=" + criteria;

        String result12 = rt.getForObject(urlListCategoryByCriteria, String.class);

        List<Category> listCategorys = new ArrayList<Category>();
        try {
            Type collectionType = new TypeToken<List<Category>>() {
            }.getType();
            listCategorys = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listCategorys;
    }

    @Override
    public boolean categoryDelete(int catId) {
        String urlDeleteCategory = urlCategory + catId;
        boolean resultDelete = false;
        ResponseEntity<String> exchange = rt.exchange(urlDeleteCategory, HttpMethod.DELETE, null, String.class);
        if (exchange.getBody().equalsIgnoreCase("true")) {
            resultDelete = true;
        }
        return resultDelete;
    }

    @Override
    public boolean categoryUpdate(Category category) {
        boolean resultUpdate = false;

        String json = gson.toJson(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        ResponseEntity<String> exchange = rt.exchange(urlCategory, HttpMethod.POST, request, String.class);

        System.err.println("ressss::: " + exchange.getBody());
        return Boolean.parseBoolean(exchange.getBody());
    }

    @Override
    public boolean categoryCreate(Category category) {
        String urlCreateCategory = urlCategory + "save";
        String json = gson.toJson(category);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateCategory, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public boolean categoryValidateName(String newName) {
        String urlValidCategory = urlCategory + "validName?newName=" + newName;

        String resultValid = rt.getForObject(urlValidCategory, String.class);

        return Boolean.parseBoolean(resultValid);
    }

    @Override
    public List<KeyValue> getAllCategory() {
        String urlAllcategory = urlCategory + "getAllCategory";

        String result12 = rt.getForObject(urlAllcategory, String.class);

        Type collectionType = new TypeToken<List<KeyValue>>() {
        }.getType();
        List<KeyValue> userss = gson.fromJson(result12, collectionType);
        return userss;
    }
}
