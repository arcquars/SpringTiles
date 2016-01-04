/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import java.util.List;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.ListProductRefund;
import org.lugubria.sys.service.facade.IDetailBuyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class DetailBuyServices implements IDetailBuyRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlDetailRefund;

    public DetailBuyServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlDetailRefund = apli.getUrl() + "detailbuy/";
    }

    
    public ListProductRefund getListByIdsAndBranchId(String ids, int branchId) {
        String urlRefundproducts = urlDetailRefund + "getListByIds?ids=" + ids+"&branchId="+branchId;
        String resultRest = rt.getForObject(urlRefundproducts, String.class);
        
        ListProductRefund lps = gson.fromJson(resultRest, ListProductRefund.class);
        
        return lps;
    }

    public boolean saveListProductRefund(ListProductRefund lpr) {
        String urlCreateRefund = urlDetailRefund + "save";
        String json = gson.toJson(lpr);
        System.out.println("josnresult::: " + json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreateRefund, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public List<Double> getDatesBuyByProductId(int productId) {
        String urlRefundproducts = urlDetailRefund + "getdatesbuybyproductid?proId=" + productId;
        String resultRest = rt.getForObject(urlRefundproducts, String.class);
        
        List<Double> lps = gson.fromJson(resultRest, List.class);
        
        return lps;
    }
}
