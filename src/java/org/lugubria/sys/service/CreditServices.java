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
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.CreditAmountDto;
import org.lugubria.sys.domain.CreditDto;
import org.lugubria.sys.domain.ListCreditDetailDto;
import org.lugubria.sys.domain.dto.credit.ListPaymentDto;
import org.lugubria.sys.service.facade.ICreditRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class CreditServices implements ICreditRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlCredit;

    public CreditServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlCredit = apli.getUrl() + "credit/";
    }

    @Override
    public List<CreditDto> getCreditByProvider(int providerId) {
        String urlListCreditDto = urlCredit + "findByProviderId?providerId=" + providerId;

        String result12 = rt.getForObject(urlListCreditDto, String.class);
        System.out.println("json:::::: "+result12);
        List<CreditDto> listCreditDto = new ArrayList<CreditDto>();
        try {
            Type collectionType = new TypeToken<List<CreditDto>>() {
            }.getType();
            listCreditDto = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listCreditDto;
    }

    @Override
    public boolean savePayment(int creditId, double payment) {
        String urlCreatePaymenttt = urlCredit + "save";
        CreditAmountDto cad = new CreditAmountDto();
        cad.setAmount(payment);
        cad.setCreditId(creditId);
        String json = gson.toJson(cad);
        System.out.println("josnresult::: " + json);

        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(json, headers);
        String resultpost = rt.postForObject(urlCreatePaymenttt, request, String.class);
        return Boolean.parseBoolean(resultpost);
    }

    @Override
    public ListCreditDetailDto getListCreditByCriteria(int providerId, int buyCancel, String dateStart, String dateEnd) {
        String urlListCreditByCriteria = urlCredit + "getListByCriteria?providerId=" + providerId+"&buyCancel="+buyCancel+"&dateStart="+dateStart+"&dateEnd="+dateEnd;

        String result12 = rt.getForObject(urlListCreditByCriteria, String.class);
        ListCreditDetailDto listCreditDto = new ListCreditDetailDto();
        try {
            Type collectionType = new TypeToken<ListCreditDetailDto>() {
            }.getType();
            listCreditDto = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listCreditDto;
    }

    @Override
    public ListPaymentDto getListPaymentByCredit(int creditId) {
        String urlListCreditByCriteria = urlCredit + "getListPaymentDto?creditId=" + creditId;

        String result12 = rt.getForObject(urlListCreditByCriteria, String.class);
        ListPaymentDto listPaymentDto = new ListPaymentDto();
        try {
            Type collectionType = new TypeToken<ListPaymentDto>() {
            }.getType();
            listPaymentDto = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listPaymentDto;
    }

    
}
