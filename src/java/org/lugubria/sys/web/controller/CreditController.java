/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.domain.CreditDto;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.ListCreditDetailDto;
import org.lugubria.sys.domain.dto.credit.ListPaymentDto;
import org.lugubria.sys.service.CreditServices;
import org.lugubria.sys.service.ProviderServices;
import org.lugubria.sys.web.form.CreditForm;
import org.lugubria.sys.web.modelDatabase.CreditJsonList;
import org.lugubria.sys.web.translator.CreditTranslator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping(value = "creditpp")
public class CreditController {

    private ProviderServices providerServices;
    private CreditServices creditServices;

    public CreditController() {
        this.providerServices = new ProviderServices();
        this.creditServices = new CreditServices();
    }

    @ModelAttribute("allProvider")
    public List<KeyValue> getAllProvider() {
        return providerServices.getAllProvider();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewProduct(Model model, HttpSession session) {
        if(session.getAttribute("Person") == null){
            return "redirect: loginaa.html";
        }
        CreditForm creditForm = new CreditForm();
        model.addAttribute("creditForm", creditForm);
        model.addAttribute("allProvider", providerServices.getAllProvider());
        return "creditMain";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid CreditForm creditForm, BindingResult result, Model model, HttpSession session) {
        if(session.getAttribute("Person") == null){
            return "redirect: loginaa.html";
        }
        if (result.hasErrors()) {
            return "creditMain";
        }
        List<CreditDto> list = creditServices.getCreditByProvider(creditForm.getProviderId());
        model.addAttribute("listCredit", list);
        if (list.size() > 0) {
            String listValid = "true";
            model.addAttribute("listValid", listValid);
        }
        System.out.println("tamñana de list:: " + list.size());
        return "creditMain";
    }

    @RequestMapping(value = "paymentCreteAjax", method = RequestMethod.GET)
    public String paymentCreateAjax(@RequestParam int i_creditid, @RequestParam double i_saldo, @RequestParam double i_payment, Model model) {
        System.out.println("entro a payment ajaxx");
        model.addAttribute("creditId", i_creditid);
        model.addAttribute("saldo", i_saldo);
        model.addAttribute("total", i_payment);
        return "paymentCreteAjax";
    }
    
    @RequestMapping(value = "paymentSaveAjax", method = RequestMethod.GET)
    public @ResponseBody String paymentSaveAjax(@RequestParam int i_creditid, @RequestParam double i_saldo, @RequestParam int provider_id ) {
        System.out.println("entro a payment save ajaxx:: "+provider_id);
        boolean vv = creditServices.savePayment(i_creditid, i_saldo);
        //return ""+vv;
        
        List<CreditDto> list = creditServices.getCreditByProvider(provider_id);
        Gson gson = new Gson();
        if(list.size() > 0 && vv)
            return gson.toJson(list);
        else
            return "false";
    }
    
    @RequestMapping(value = "getListCreditByProvider", method = RequestMethod.GET)
    public @ResponseBody String getListCreditByProviderAjax(@RequestParam int providerId, HttpSession session) {
        System.out.println("entro a payment save ajaxx:: "+providerId);
        if(session.getAttribute("Person") == null){
            return "null";
        }
        //List<CreditDto> list = creditServices.getCreditByProvider(creditId);
        CreditJsonList creditList = new CreditJsonList();
        creditList.setAaData(creditServices.getCreditByProvider(providerId));
        Gson gson = new Gson();
        
        return gson.toJson(creditList);
    }
    
    @RequestMapping(value = "reportCredit", method = RequestMethod.GET)
    public String viewReportCredit(Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        model.addAttribute("provider", providerServices.getAllProvider());
        return "reportCredit";
    }
    
    @RequestMapping(value = "getListBuyCredit", method = RequestMethod.GET)
    public @ResponseBody
    String getListBuyCreditAjax(@RequestParam int fReportCredit_provider, @RequestParam int f_cancel, @RequestParam String f_dateIni, @RequestParam String f_dateEnd) {
        Gson gson = new Gson();
        ListCreditDetailDto list = creditServices.getListCreditByCriteria(fReportCredit_provider, f_cancel, f_dateIni, f_dateEnd);
        CreditTranslator.addAction(list);
        String result = gson.toJson(list);
        return result;
    }
    
    @RequestMapping(value = "getListPaymentAjax", method = RequestMethod.POST)
    public @ResponseBody String getListPaymentAjax(@RequestParam int creditId) {
        ListPaymentDto list = creditServices.getListPaymentByCredit(creditId);
        Gson gson = new Gson();
        
        return gson.toJson(list);
    }
}
