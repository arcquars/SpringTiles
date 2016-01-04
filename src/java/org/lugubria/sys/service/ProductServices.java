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
import org.lugubria.sys.domain.*;
import org.lugubria.sys.service.facade.IProductRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class ProductServices implements IProductRepository{

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlProduct;

    public ProductServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlProduct = apli.getUrl() + "product/";
    }
    
    
    @Override
    public boolean createProduct(ProductDto product) {
        String urlCreateProduct = urlProduct;
        String json = gson.toJson(product);
        System.out.println("josnresult::: " + json);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json,headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        //rt.postForLocation(urlCreateProduct, request);
        String resultpost = rt.postForObject(urlCreateProduct, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public List<ProductDetailDto> getProductByCriteria(int type, String criteria) {
        String urlListProductByCriteria = urlProduct + "findByCriteria?type=" + type + "&criteria=" + criteria;

        String result12 = rt.getForObject(urlListProductByCriteria, String.class);
        

        Type collectionType = new TypeToken<List<ProductDetailDto>>() {
        }.getType();
        List<ProductDetailDto> userss = gson.fromJson(result12, collectionType);
        return userss;
    }

    @Override
    public Map<String, String> getAllCategory() {
        String urlAllcategory = urlProduct + "getAllCategory";

        String result12 = rt.getForObject(urlAllcategory, String.class);

        Type collectionType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> userss = gson.fromJson(result12, collectionType);
        System.out.println("map categoris::::::::: "+userss.size());
        return userss;
    }

    @Override
    public void deleteProduct(int proId) {
        String urlDeleteProduct = urlProduct+"?id="+proId;
        rt.delete(urlDeleteProduct);
    }

    @Override
    public void updateProduct(ProductDto product) {
        String json = gson.toJson(product);
        System.out.println("josnresult::: " + json);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json,headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlProduct+"updateProduct", request);
    }

    @Override
    public boolean productValidateName(String newName) {
        String urlValidProduct = urlProduct + "validName?newName=" + newName;

        String resultValid = rt.getForObject(urlValidProduct, String.class);

        return Boolean.parseBoolean(resultValid);
    }

    @Override
    public boolean createProductInventory(ProductRegisterInventaryDto prid){
        return false;
    }

    @Override
    public List<RecordProductDto> getListRecordProduct(String ids) {
        String urlListProductRecord = urlProduct + "getListByIds?ids=" + ids;

        String result12 = rt.getForObject(urlListProductRecord, String.class);
        

        Type collectionType = new TypeToken<List<RecordProductDto>>() {
        }.getType();
        List<RecordProductDto> userss = gson.fromJson(result12, collectionType);
        
        if(userss.size()>0)
            System.out.println("sss:::::::::::: "+userss.get(0).getCategoryName());
        
        return userss;
    }

    @Override
    public DeliveryBranch getListDeliveryProduct(String ids) {
        String urlDeliveryProduct = urlProduct + "getDeliveryBranch?ids=" + ids;
        String resultRest = rt.getForObject(urlDeliveryProduct, String.class);
        DeliveryBranch db = gson.fromJson(resultRest, DeliveryBranch.class);
        return db;
    }

    @Override
    public ProductDto getProductDtoById(int id) {
        String urlgetProductDto  = urlProduct + "getProductDtoById?id="+id;

        String result12 = rt.getForObject(urlgetProductDto, String.class);

        ProductDto pDto = gson.fromJson(result12, ProductDto.class);
        return pDto;
    }

    @Override
    public boolean validNameEdit(String newName, int prodId) {
        String urlValidProduct = urlProduct + "validNameEdit?newName=" + newName+"&prodId="+prodId;

        String resultValid = rt.getForObject(urlValidProduct, String.class);

        return Boolean.parseBoolean(resultValid);
    }

    @Override
    public void updateProductDto(ProductDto productDto) {
        String json = gson.toJson(productDto);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json,headers);
        //String resultpost = rt.postForObject(urlPerson, request, String.class);
        rt.postForLocation(urlProduct+"updateProductPrice", request);
    }

    @Override
    public List<Double> getDatesPriceSaleByProductId(int productId) {
        String urlRefundproducts = urlProduct + "getDatesPriceSaleByProductid?proId=" + productId;
        String resultRest = rt.getForObject(urlRefundproducts, String.class);
        
        List<Double> lps = gson.fromJson(resultRest, List.class);
        
        return lps;
    }
    
}
