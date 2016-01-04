/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.business.Branch;
import org.lugubria.sys.service.BranchServices;
import org.lugubria.sys.service.RecordProductBranchServices;
import org.lugubria.sys.web.form.CategorySearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping(value = "branchpp")
public class BranchController {
    
    private String visibleDiv;
    private String visibleDivNotResult;
    private BranchServices branchServices;
    private RecordProductBranchServices recordProductBranchServices;
    
    public BranchController() {
        branchServices = new BranchServices();
        recordProductBranchServices = new RecordProductBranchServices();
        visibleDiv = "hidden";
        visibleDivNotResult = "visible";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewProduct(Map model, HttpSession session) {
        if(session.getAttribute("Person") == null){
            return "redirect:/loginaa.html";
        }
        CategorySearchForm psform = new CategorySearchForm();
        
        List<Branch> listBranch = branchServices.searchByCriteria("");
        
        model.put("categorySearchForm", psform);
        model.put("listBranch", listBranch);
        return "branchsearch";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid CategorySearchForm psForm, BindingResult result, Map model, HttpSession session) {
        if(session.getAttribute("Person") == null){
            return "redirect:/loginaa.html";
        }
        if (result.hasErrors()) {
            return "branchsearch";
        }
        
        List<Branch> listBranch = branchServices.searchByCriteria(psForm.getTextSearch());
        
        model.put("visibleDiv", visibleDiv);
        model.put("visibleDivNot", visibleDivNotResult);
        model.put("listBranch", listBranch);
        return "branchsearch";
    }
    
    @RequestMapping(value = "branchDelete", method = RequestMethod.GET)
    public @ResponseBody String branchDelete(@RequestParam int branchId, HttpSession session) {
        if(session.getAttribute("Person") == null){
            return "Session Expiro.";
        }
        boolean validDelete = branchServices.branchDelete(branchId);
        return ""+validDelete;
        
    }
    
    @RequestMapping(value = "branchUpdate", method = RequestMethod.GET)
    public @ResponseBody String branchUpdate(@RequestParam int icatId, @RequestParam String iname, @RequestParam String iaddress, @RequestParam int iphone, @RequestParam String icriteria) {
        Branch branchUpdate = new Branch();
        branchUpdate.setBranchId(new Integer(icatId));
        branchUpdate.setAddress(iaddress);
        branchUpdate.setDel(false);
        branchUpdate.setName(iname);
        branchUpdate.setPhone(new Integer(iphone));
        boolean validUpdate = branchServices.branchUpdate(branchUpdate);
        
        List<Branch> listBranch = branchServices.searchByCriteria(icriteria);
        Gson gson = new Gson();
        
        if(validUpdate){
            return gson.toJson(listBranch);
        }
        //return "false";
        return null;
        
    }
    
    @RequestMapping(value = "branchValidName", method = RequestMethod.GET)
    public @ResponseBody String branchValidateName(@RequestParam String i_create_name) {
        boolean validValidNameUpdate = branchServices.branchValidateName(i_create_name);
        return ""+!validValidNameUpdate;
    }
    
    @RequestMapping(value = "branchValidNameUpdate", method = RequestMethod.GET)
    public @ResponseBody String branchValidateNameUpdate(@RequestParam String iname, @RequestParam int branchId) {
        boolean validValidNameUpdate = branchServices.branchValidateName(iname, branchId);
        //return "false";
        return ""+!validValidNameUpdate;
    }
    
    @RequestMapping(value = "branchCreate", method = RequestMethod.GET)
    public @ResponseBody String branchCreate(@RequestParam String i_create_name, @RequestParam String i_create_address, @RequestParam int i_create_phone) {
        Branch branchCreate = new Branch();
        branchCreate.setAddress(i_create_address);
        branchCreate.setBranchId(Integer.MIN_VALUE);
        branchCreate.setDel(false);
        branchCreate.setName(i_create_name);
        branchCreate.setPhone(i_create_phone);
        boolean validCreate = branchServices.branchCreate(branchCreate);
        
        List<Branch> listBranch = branchServices.searchByCriteria("");
        Gson gson = new Gson();
        
        if(validCreate){
            return gson.toJson(listBranch);
        }
        
        return null;
    }

    @RequestMapping(value = "branchValidDelete", method = RequestMethod.POST)
    public @ResponseBody String branchValidateDelete(@RequestParam int branchId) {
        boolean validValidNameUpdate = recordProductBranchServices.validDeleteByBranchId(branchId);
        return ""+!validValidNameUpdate;
    }
}
