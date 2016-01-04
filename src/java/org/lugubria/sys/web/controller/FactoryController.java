/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import org.lugubria.sys.domain.Factory;
import org.lugubria.sys.service.FactoryServices;
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
@RequestMapping(value = "factorypp")
public class FactoryController {

    private String visibleDiv;
    private String visibleDivNotResult;
    private FactoryServices factoryServices;

    public FactoryController() {
        factoryServices = new FactoryServices();
        visibleDiv = "hidden";
        visibleDivNotResult = "visible";
    }
    
    
    @RequestMapping(value = "allFactoryAjax", method = RequestMethod.GET)
    public @ResponseBody String getAllFactoryAjax() {
        return KeyValueTranslator.translateKeyValueToHtml(factoryServices.getAllFactory());
    }
    
    @RequestMapping(value = "factoryCreate", method = RequestMethod.GET)
    public @ResponseBody String factoryCreate(@RequestParam String i_factory_name) {
        boolean validCreate = false;
        Factory newFactory = new Factory();
        newFactory.setDel(false);
        newFactory.setFactoryId(Integer.MIN_VALUE);
        newFactory.setName(i_factory_name);
        newFactory.setOrigin("");
        validCreate = factoryServices.factoryCreate(newFactory);
        return ""+validCreate;
//        return "true";
    }
    
    @RequestMapping(value = "factoryValidName", method = RequestMethod.GET)
    public @ResponseBody String factoryValidName(@RequestParam String i_factory_name) {
        return ""+!factoryServices.factoryValidateName(i_factory_name);
    }
    
}
