/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.business.Category;
import org.lugubria.sys.service.CategoryServices;
import org.lugubria.sys.web.form.CategorySearchForm;
import org.lugubria.sys.web.translator.KeyValueTranslator;
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
@RequestMapping(value = "categorypp")
public class CategoryController {

    private String visibleDiv;
    private String visibleDivNotResult;
    private CategoryServices categoryServices;

    public CategoryController() {
        categoryServices = new CategoryServices();
        visibleDiv = "hidden";
        visibleDivNotResult = "visible";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewProduct(Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect: loginaa.html";
        }
        CategorySearchForm psform = new CategorySearchForm();
        List<Category> listCategory = categoryServices.searchByCriteria("");
        model.put("categorySearchForm", psform);
        model.put("listCategory", listCategory);
        return "categorysearch";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid CategorySearchForm psForm, BindingResult result, Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect: /loginaa.html";
        }
        if (result.hasErrors()) {
            return "categorysearch";
        }

        List<Category> listCategory = categoryServices.searchByCriteria(psForm.getTextSearch());
        System.out.println("Size list :::: " + listCategory.size());
        model.put("listCategory", listCategory);
        return "categorysearch";
    }

    @RequestMapping(value = "categoryDelete", method = RequestMethod.GET)
    public @ResponseBody
    String categoryDelete(@RequestParam int catId) {
        boolean validDelete = categoryServices.categoryDelete(catId);
        System.out.println("entro a dele" + validDelete);
        return "" + validDelete;
    }

    @RequestMapping(value = "categoryUpdate", method = RequestMethod.GET)
    public @ResponseBody
    String categoryUpdate(@RequestParam String iname, @RequestParam int icatId, @RequestParam String iTextSearch) {
        Category categoryUpdate = new Category();
        categoryUpdate.setCategoryId(new Integer(icatId));
        categoryUpdate.setCategoryName(iname);
        categoryUpdate.setDel(new Boolean(false));

        boolean validUpdate = categoryServices.categoryUpdate(categoryUpdate);

        List<Category> listCategory = categoryServices.searchByCriteria(iTextSearch);
        Gson gson = new Gson();

        if (validUpdate && listCategory.size() > 0) {
            return gson.toJson(listCategory);
        }
        return null;
    }

    @RequestMapping(value = "categoryCreate", method = RequestMethod.GET)
    public @ResponseBody
    String categoryCreate(@RequestParam String inameCreate, @RequestParam String iTextSearchNewCategory) {
        boolean validCreate = false;
        Category categoryCreate = new Category();
        categoryCreate.setDel(new Boolean(false));
        categoryCreate.setCategoryName(inameCreate);
        categoryCreate.setCategoryId(new Integer(0));

        if (categoryServices.categoryCreate(categoryCreate)) {
            List<Category> listCategory = categoryServices.searchByCriteria(iTextSearchNewCategory);
            Gson gson = new Gson();
            return gson.toJson(listCategory);
        }
        return null;
    }

    @RequestMapping(value = "categoryValidName", method = RequestMethod.GET)
    public @ResponseBody
    String categoryValidName(@RequestParam String iname_cat_create) {
        return "" + !categoryServices.categoryValidateName(iname_cat_create);
    }

    @RequestMapping(value = "categoryCreteAjax", method = RequestMethod.GET)
    public @ResponseBody
    String categoryCreateAjax(@RequestParam String iname_cat_create) {
        Category categoryNew = new Category();
        categoryNew.setCategoryId(Integer.MIN_VALUE);
        categoryNew.setCategoryName(iname_cat_create);
        categoryNew.setDel(false);
        categoryServices.categoryCreate(categoryNew);
        return KeyValueTranslator.translateKeyValueToHtml(categoryServices.getAllCategory());
    }

    @RequestMapping(value = "allCategoryAjax", method = RequestMethod.GET)
    public @ResponseBody
    String getAllCategoryAjax() {
        return KeyValueTranslator.translateKeyValueToHtml(categoryServices.getAllCategory());
    }

    @RequestMapping(value = "createCategory", method = RequestMethod.GET)
    public String viewCreateProduct() {
        return "createCategory";
    }

}
