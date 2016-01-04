/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.RecordProductBranchDto;
import org.lugubria.sys.service.CategoryServices;
import org.lugubria.sys.service.RecordProductBranchServices;
import org.lugubria.sys.service.RecordProductServices;
import org.lugubria.sys.service.SaleServices;
import org.lugubria.sys.web.form.ProductSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping("salesman")
public class SalesmanController {

    private List<KeyValue> criteria;
    private CategoryServices categoryServices;
    private RecordProductBranchServices rpbServices;
    private RecordProductServices recordProduct;
    private SaleServices saleServices;

    public SalesmanController() {
        categoryServices = new CategoryServices();
        recordProduct = new RecordProductServices();
        rpbServices = new RecordProductBranchServices();
        saleServices = new SaleServices();
        criteria = new ArrayList<KeyValue>();
        KeyValue kv = new KeyValue("1", "Nombre");
        criteria.add(kv);
        kv = new KeyValue("2", "Codigo");
        criteria.add(kv);
        kv = new KeyValue("3", "Categoria");
        criteria.add(kv);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showBranch(Map model) {
        String visibleTable = "false";
        model.put("visibleTable", visibleTable);
        model.put("mapcriteria", criteria);
        model.put("mapCategory", categoryServices.getAllCategory());
        ProductSearchForm psform = new ProductSearchForm();
        model.put("productSearchForm", psform);
        return "pp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid ProductSearchForm psForm, BindingResult result, Map model, HttpSession session) {
        String visibleTable = "true";
        if (session.getAttribute("Person") == null) {
            return "redirect:loginaa/1.html";
        }
        if (result.hasErrors()) {
            return "pp";
        }
        List<RecordProductBranchDto> list = null;
        if (psForm.getCriteria() == 3) {
            System.out.println("xxx:::: " + psForm.getCriteria() + " " + psForm.getCategory());
            list = rpbServices.getListRpbByBranch(1, psForm.getCriteria(), psForm.getCategory() + "");
            System.out.println("size::::: " + list.size());
            model.put("searchText", psForm.getCategory());
        } else {
            System.out.println("yyy:::: " + psForm.getCriteria() + " " + psForm.getTextSearch());
            list = rpbServices.getListRpbByBranch(1, psForm.getCriteria(), psForm.getTextSearch());
            model.put("searchText", psForm.getTextSearch());
        }
        if (list.size() > 0) {
            System.out.println("Result list size:: " + list.size());
            model.put("listProduct", list);
        } else {
            visibleTable = "false";
        }
        model.put("visibleTable", visibleTable);
        model.put("mapcriteria", criteria);
        model.put("mapCategory", categoryServices.getAllCategory());
        return "pp";
    }
}
