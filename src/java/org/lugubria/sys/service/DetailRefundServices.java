/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.ListProductRefund;
import org.lugubria.sys.service.facade.IDetailRefundRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class DetailRefundServices implements IDetailRefundRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlDetailRefund;

    public DetailRefundServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlDetailRefund = apli.getUrl() + "refund/";
    }

    
    @Override
    public ListProductRefund getListByIdsAndBranchId(String ids, int branchId) {
        String urlRefundproducts = urlDetailRefund + "getListByIds?ids=" + ids+"&branchId="+branchId;
        String resultRest = rt.getForObject(urlRefundproducts, String.class);
        
        ListProductRefund lps = gson.fromJson(resultRest, ListProductRefund.class);
        
        return lps;
    }

    @Override
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
}
