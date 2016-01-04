/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.*;
import org.lugubria.sys.service.facade.ISaleRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class SaleServices implements ISaleRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlSale;

    public SaleServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlSale = apli.getUrl() + "sale/";
    }

    @Override
    public ListProductSale getListByProductIdsAndBranchId(String ids, int branchId) {
        String urlSaleproducts = urlSale + "getListByIds?ids=" + ids+"&branchId="+branchId;
        String resultRest = rt.getForObject(urlSaleproducts, String.class);
        
        ListProductSale lps = gson.fromJson(resultRest, ListProductSale.class);
        
        return lps;
    }

    @Override
    public ProductReserve getListReserveByProductIdsAndBranchId(String ids, int branchId) {
        String urlSaleproducts = urlSale + "getListForReserveByIds?ids=" + ids+"&branchId="+branchId;
        String resultRest = rt.getForObject(urlSaleproducts, String.class);
        
        ProductReserve lps = gson.fromJson(resultRest, ProductReserve.class);
        
        return lps;
    }
    
    @Override
    public String saveListproductSale(ListProductSale lps) {
        String urlCreataListProductSale = urlSale + "save";
        String json = gson.toJson(lps);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreataListProductSale, request, String.class);
        return resultpost;
    }

    @Override
    public ListSaleDetailGain getSaleDetail(int branchId, String dateStart, String dateEnd) {
        String urlSaleDetail = urlSale + "getListByDates?branchId="+branchId+"&dateStart="+dateStart+"&dateEnd="+dateEnd;
        String resultRest = rt.getForObject(urlSaleDetail, String.class);
        
        ListSaleDetailGain lps = gson.fromJson(resultRest, ListSaleDetailGain.class);
        
        return lps;
    }

    @Override
    public boolean saveListproductReserve(ProductReserve pr) {
        String urlCreataListProductReserve = urlSale + "saveReserve";
        String json = gson.toJson(pr);
        System.out.println(json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        
        String resultpost = rt.postForObject(urlCreataListProductReserve, request, String.class);
            
        return Boolean.parseBoolean(resultpost);
        
    }

    @Override
    public List<ReserveDto> getListReserve(int branchId, String dateReserve, boolean all) {
        String urlSaleDetail = urlSale + "getListReserve?branchId="+branchId+"&dateReserve="+dateReserve+"&all="+all;
        String resultRest = rt.getForObject(urlSaleDetail, String.class);
        
        Type collectionType = new TypeToken<List<ReserveDto>>() {
        }.getType();
        List<ReserveDto> reserveDto = gson.fromJson(resultRest, collectionType);
        
        return reserveDto;
    }

    @Override
    public ReserveDetail getReserveDetail(int reserveId) {
        String urlSaleDetail = urlSale + "getReserveDetail?reserveId="+reserveId;
        String resultRest = rt.getForObject(urlSaleDetail, String.class);
        
        ReserveDetail rd = gson.fromJson(resultRest, ReserveDetail.class);
        
        return rd;
    }

    @Override
    public boolean reserveUpdate(int reserveId) {
        String urlSaleDetail = urlSale + "reserveUpdate?reserveId="+reserveId;
        String resultRest = rt.getForObject(urlSaleDetail, String.class);
        
        boolean rd = gson.fromJson(resultRest, boolean.class);
        
        return rd;
    }

    @Override
    public ListSalesAccount getSaleAccount(int branchId, String dateStart, String dateEnd) {
        String urlSaleDetail = urlSale + "getListAccountByDates?branchId="+branchId+"&dateStart="+dateStart+"&dateEnd="+dateEnd;
        String resultRest = rt.getForObject(urlSaleDetail, String.class);
        
        ListSalesAccount lps = gson.fromJson(resultRest, ListSalesAccount.class);
        
        return lps;
    }

    @Override
    public ListSalesAccount getListSaleAccount(int branchId, String dateStart, String dateEnd) {
        String urlSalesAccount = urlSale + "getListAccountByDates?branchId="+branchId+"&dateStart="+dateStart+"&dateEnd="+dateEnd;
        String resultRest = rt.getForObject(urlSalesAccount, String.class);
        
        ListSalesAccount lps = gson.fromJson(resultRest, ListSalesAccount.class);
        
        return lps;
    }

    @Override
    public SalesAmount getLastSaleAmount(int branchId, int userId) {
        String urlSalesAmount = urlSale + "getLastSalesAmount?branchId="+branchId+"&userId="+userId;
        String resultRest = rt.getForObject(urlSalesAmount, String.class);
        
        SalesAmount lps = gson.fromJson(resultRest, SalesAmount.class);
        
        return lps;
    }

    @Override
    public String[] getLastSaleAmountDates(int branchId) {
        String urlSalesAmount = urlSale + "getLastSalesAmountDates?branchId="+branchId;
        String resultRest = rt.getForObject(urlSalesAmount, String.class);
        
        String [] lps = gson.fromJson(resultRest, String[].class);
        
        return lps;
    }

    @Override
    public boolean saveSalesAccount(SalesAmount sa) {
        String urlSaveSalesAccount = urlSale + "saveSalesAccount";
        String json = gson.toJson(sa);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlSaveSalesAccount, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public List<KeyValue> lastSaleAmountDateByBranchId(long branchId) {
        String urlSaleproducts = urlSale + "lastSaleAmountDateByBranchId?&branchId="+branchId;
        String resultRest = rt.getForObject(urlSaleproducts, String.class);
        
        List<KeyValue> rest = gson.fromJson(resultRest, new TypeToken<List<KeyValue>>(){}.getType());
        
        return rest;
    }
}
