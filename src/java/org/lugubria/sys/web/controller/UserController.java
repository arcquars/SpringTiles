/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.crypt.AESCrypt;
import org.lugubria.sys.domain.Person;
import org.lugubria.sys.service.DetailBuyServices;
import org.lugubria.sys.service.UserServices;
import org.lugubria.sys.web.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.lugubria.sys.service.PersonServices;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping("loginaa")
public class UserController {

    private UserServices userServices;
    private PersonServices personServices;
    private DetailBuyServices detailBuyServices;

    public UserController() {
        userServices = new UserServices();
        personServices = new PersonServices();
        detailBuyServices = new DetailBuyServices();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showLogin(Map model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "formLogin";

    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid LoginForm loginForm, BindingResult result, Map model, HttpSession session) throws Exception {
        if (result.hasErrors()) {
            return "formLogin";
        }
        loginForm = (LoginForm) model.get("loginForm");
        loginForm.setPassword(AESCrypt.encrypt(loginForm.getPassword()));
        boolean validUser = userServices.validateUser(loginForm.getUsername(), loginForm.getPassword());
        if (validUser) {
            Person person = personServices.getPersonByUsernamePass(loginForm.getUsername(), loginForm.getPassword());

            if (person != null) {
                session.setAttribute("Person", person);
                session.setAttribute("userId", userServices.getUserId(loginForm.getUsername(), loginForm.getPassword()));
            }
            session.setAttribute("mList", userServices.getMenuMain());
            session.setAttribute("loginstatus", validUser);
            session.setAttribute("nameProject", session.getServletContext().getContextPath());
            
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            Apli apli = (Apli) ctx.getBean("myApli");
            session.setAttribute("urlServices", apli.getUrl());
            //model.put("contextPath", session.getServletContext().getContextPath());
            return "helloaa";
        }
        model.put("errorUserVal", "Usuario no registrado");
        return "formLogin";
    }

    @RequestMapping(value = "registropp", method = RequestMethod.GET)
    public String showRegistro() {
        System.out.println("Entro al controller hello!!!");
        return "registro";
    }

    @RequestMapping(value = "/{error_id}", method = RequestMethod.POST)
    public String showLogin(@PathVariable int error_id, Map model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        if (error_id == 1) {
            model.put("sessionExpired", "La session expiro!!");
        }
        return "formLogin";

    }

    @RequestMapping(value = "productAverage", method = RequestMethod.GET)
    public String showpp(@RequestParam int id, Map model) {
        List<Double> result = detailBuyServices.getDatesBuyByProductId(id);
        model.put("result", result);
        return "productAverage";

    }
    
    @RequestMapping(value = "productAverageAjax", method = RequestMethod.POST)
    public @ResponseBody String showppAjax(@RequestParam int id) {
        List<Double> result = detailBuyServices.getDatesBuyByProductId(id);
        String resultString = "";
        if(result.size()>0){
            resultString += "Precio mas alto de compra: "+result.get(0)+" | ";
            resultString += "Precio promedio de compra: "+result.get(1)+" | ";
            resultString += "Precio mas bajo de compra: "+result.get(2);
        }else{
            resultString += "Sin historial de compra";
        }
        return resultString;

    }
    
    @RequestMapping(value = "productCosteAverageJson", method = RequestMethod.GET)
    public @ResponseBody
        String getProductCosteAverage(@RequestParam int prodId, Map model) {
        List<Double> result = detailBuyServices.getDatesBuyByProductId(prodId);
        Gson gson = new Gson();
        return gson.toJson(result);

    }
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String showError(Map model) {
        return "error";

    }

    @RequestMapping(value = "sessionValid", method = RequestMethod.GET)
    public @ResponseBody
    String getPersonNameAjax(HttpSession session) {
        String validSession = "true";
        if (session.getAttribute("Person") == null) {
            validSession = "false";
        }
        return validSession;
    }
}
