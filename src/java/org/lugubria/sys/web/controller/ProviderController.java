/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import org.lugubria.sys.domain.ProviderPersonDto;
import org.lugubria.sys.service.ProviderServices;
import org.lugubria.sys.web.translator.KeyValueTranslator;
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
@RequestMapping(value = "providerpp")
public class ProviderController {

    private String visibleDiv;
    private String visibleDivNotResult;
    private ProviderServices providerServices;

    public ProviderController() {
        providerServices = new ProviderServices();
        visibleDiv = "hidden";
        visibleDivNotResult = "visible";
    }
    

    @RequestMapping(value = "providerValidName", method = RequestMethod.GET)
    public @ResponseBody String providerValidName(@RequestParam String i_cprovider_businessname) {
        return ""+!providerServices.providerValidateName(i_cprovider_businessname);
    }
    
    @RequestMapping(value = "createNewProvider", method = RequestMethod.GET)
    public @ResponseBody String createProvider(@RequestParam String i_cprovider_businessname, @RequestParam String inames, 
            @RequestParam String fp_i_firstname, @RequestParam String fp_i_lastname, 
            @RequestParam String fp_i_ci, @RequestParam String fp_i_address, 
            @RequestParam String fp_i_phone_home, @RequestParam String fp_i_phone_mobil, 
            @RequestParam String fp_i_email, @RequestParam String fp_i_userId,
            @RequestParam boolean fp_i_del) {
        System.out.println("Entor a crear Provider:::"+fp_i_userId);
            
        ProviderPersonDto ppd = new ProviderPersonDto();
        ppd.setAddress(fp_i_address);
        ppd.setAgentNames(inames);
        
        ppd.setCompanyName(i_cprovider_businessname);
        ppd.setEmail(fp_i_email);
        ppd.setFirstname(fp_i_firstname);
        ppd.setLastname(fp_i_lastname);
        ppd.setDel(fp_i_del);
        
        if(!fp_i_ci.isEmpty())
            ppd.setCi(new Integer(fp_i_ci));
        if(!fp_i_phone_home.isEmpty())
            ppd.setPhoneHome(new Integer(fp_i_phone_home));
        if(!fp_i_phone_mobil.isEmpty())
            ppd.setPhoneMobil(new Integer(fp_i_phone_mobil));
        if(!fp_i_userId.isEmpty())
            ppd.setPerId(new Integer(fp_i_userId));
        
        return ""+providerServices.createProviderPerson(ppd);
        //return "true";
    }
    

    @RequestMapping(value = "allProviderAjax", method = RequestMethod.GET)
    public @ResponseBody String getAllProviderAjax() {
        String resultTranslator = KeyValueTranslator.translateKeyValueToHtml(providerServices.getAllProvider());
        System.out.println("ResultTranslator:: "+resultTranslator);
        return resultTranslator;
    }
}
