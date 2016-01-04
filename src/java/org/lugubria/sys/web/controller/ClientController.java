/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.dto.client.ClientDto;
import org.lugubria.sys.service.ClientServices;
import org.lugubria.sys.web.form.PersonSearchForm;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping("clientaa")
public class ClientController {
    
    private List<KeyValue> criteria;
    private ClientServices clientServices;
    
    public ClientController(){
        clientServices = new ClientServices();
        
        criteria = new ArrayList<KeyValue>();
        criteria.add(new KeyValue("1", "Razon Social"));
        criteria.add(new KeyValue("2", "Nit"));
        
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showClient(Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        PersonSearchForm psform = new PersonSearchForm();
        session.setAttribute("mapcriteria", criteria);
        model.put("personSearchForm", psform);

        return "clientsearch";

    }
    
    @RequestMapping(value = "getClientAjax", method = RequestMethod.POST)
    public @ResponseBody
    String getClientAjax(@RequestParam int ci, HttpSession session) {
        ClientDto client = clientServices.getClientFromCi(ci);
        if (session.getAttribute("Person") == null) {
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(client);
    }
    
    /**
     *
     * @param client
     * @param session
     * @return
     */
    @RequestMapping(value = "uploadClientAjax", method = RequestMethod.POST)
    public @ResponseBody
    String uploadClientAjax(@RequestParam String client) {
        Gson gson = new Gson();
        ClientDto cl = gson.fromJson(client, ClientDto.class);
        clientServices.updateClient(cl);
        return "true";
    }
    
    @RequestMapping(value = "getListClientByCriteria", method = RequestMethod.GET)
    public @ResponseBody
    String getListProductByCriteria(@RequestParam int criteria, @RequestParam String textSearch, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        return "{\"aaData\": "+clientServices.getStringClientByCriteria(criteria, textSearch)+"}";
        
    }
}
