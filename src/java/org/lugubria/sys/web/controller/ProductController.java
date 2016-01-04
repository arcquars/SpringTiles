/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.ProductDetailDto;
import org.lugubria.sys.domain.ProductDto;
import org.lugubria.sys.service.CategoryServices;
import org.lugubria.sys.service.FactoryServices;
import org.lugubria.sys.service.ProductServices;
import org.lugubria.sys.web.form.EmployeeForm;
import org.lugubria.sys.web.form.ProductSearchForm;
import org.lugubria.sys.web.translator.ProductJsonTranslator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping(value = "productpp")
public class ProductController {

    private String visibleDiv;
    private ProductServices productServices;
    private FactoryServices factoryServices;
    private CategoryServices categoryServices;
    
    private List<KeyValue> criteria;

    public ProductController() {
        productServices = new ProductServices();
        factoryServices = new FactoryServices();
        categoryServices = new CategoryServices();
        criteria = new ArrayList<KeyValue>();
        KeyValue kv = new KeyValue("1", "Nombre");
        criteria.add(kv);;
        kv = new KeyValue("2", "Codigo");
        criteria.add(kv);
        kv = new KeyValue("3", "Categoria");
        criteria.add(kv);
        visibleDiv = "hidden";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewProduct(Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:loginaa.html";
        }
        ProductSearchForm psform = new ProductSearchForm();
        session.setAttribute("mapcriteria", criteria);

        model.put("productSearchForm", psform);
        model.put("allCategory", categoryServices.getAllCategory());
        model.put("allFactory", factoryServices.getAllFactory());
        return "productsearch";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid ProductSearchForm psForm, BindingResult result, Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:loginaa.html";
        }
        if (result.hasErrors()) {
            visibleDiv = "hidden";
            return "productsearch";
        }

        List<ProductDetailDto> listProduct = productServices.getProductByCriteria(psForm.getCriteria(), psForm.getTextSearch());
        if (listProduct.size() > 0) {
            visibleDiv = "visible";
        } else {
            visibleDiv = "hidden";
        }
        System.err.println("Tamaño resultado::: " + listProduct.size());
        model.put("visibleDiv", visibleDiv);
        model.put("listProduct", listProduct);
        return "productsearch";
    }

    @RequestMapping(value = "getListProductByCriteria", method = RequestMethod.GET)
    public @ResponseBody
    String getListProductByCriteria(@RequestParam int criteria, @RequestParam String textSearch, @RequestParam String _, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        List<ProductDetailDto> listProduct = productServices.getProductByCriteria(criteria, textSearch);

        Gson gson = new Gson();
        String result = gson.toJson(ProductJsonTranslator.productoToProductjson(listProduct));
        
        return result;
    }

    @RequestMapping(value = "getListProductByCriteriaUpload", method = RequestMethod.GET)
    public @ResponseBody
    String getListProductByCriteriaUpload(@RequestParam int criteria, @RequestParam String textSearch, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        List<ProductDetailDto> listProduct = productServices.getProductByCriteria(criteria, textSearch);

        Gson gson = new Gson();
        String result = gson.toJson(ProductJsonTranslator.productoToProductjsonupload(listProduct));

        return result;
    }

    @RequestMapping(value = "productCreate", method = RequestMethod.GET)
    public @ResponseBody
    String productCreate(HttpSession session, @RequestParam String iname, @RequestParam String fp_i_codorigen, @RequestParam String fp_i_price, @RequestParam String fp_s_category, @RequestParam String fp_s_factory, @RequestParam String fp_i_description) {
        if (session.getAttribute("Person") == null) {
            return "redirect:loginaa.html";
        }
        ProductDto product = new ProductDto();
        product.setCategoryId(Integer.parseInt(fp_s_category));
        product.setFactoryId(new Integer(fp_s_factory));
        product.setPriceSale(Double.parseDouble(fp_i_price));
        product.setCodOrigin(fp_i_codorigen);
        product.setDel(false);
        product.setDescription(fp_i_description);
        product.setName(iname);
        product.setProductId(0);
        product.setUrlPhoto("");
//        boolean val = true;
        boolean val = productServices.createProduct(product);
        return "" + val;
//        return "true";
    }

    @RequestMapping(value = "productDelete", method = RequestMethod.GET)
    public @ResponseBody
    String deleteProductById(@RequestParam int proId) {
        //productServices.deleteProduct(proId);
        return "" + 1;
    }

    @RequestMapping(value = "productEdit/productEdit", method = RequestMethod.POST)
    public String productEditSubmit(@Valid EmployeeForm personForm, BindingResult result, Map model, HttpSession session) {
        if (result.hasErrors()) {
            visibleDiv = "hidden";
            model.put("visibleDiv", visibleDiv);
            return "productEdit";
        }
        System.out.println("Perid:::::::::::::: " + personForm.getPerId());
        //personService.updatePerson(PersonTranslator.personFromToPerson(personForm));
        //personServices.updatePerson(PersonTranslator.personFromToPerson(personForm));
        return "redirect:../../productpp.html";
    }

    @RequestMapping(value = "productValidNameAjax", method = RequestMethod.GET)
    public @ResponseBody
    String productValidName(@RequestParam String iname) {
        return "" + !productServices.productValidateName(iname);
    }

    @RequestMapping(value = "productValidNameEditAjax", method = RequestMethod.GET)
    public @ResponseBody
    String productValidNameEdit(@RequestParam int prodId, @RequestParam String iproductName) {
        return "" + !productServices.validNameEdit(iproductName, prodId);
    }

    @RequestMapping(value = "createProduct", method = RequestMethod.GET)
    public String viewCreateProduct(Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        model.put("allCategory", categoryServices.getAllCategory());
        model.put("allFactory", factoryServices.getAllFactory());
        
        return "createProduct";
    }

    @RequestMapping(value = "validateSession", method = RequestMethod.GET)
    public @ResponseBody
    String validateSession(HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "editProduct", method = RequestMethod.GET)
    public String viewEditProduct(@RequestParam int prodId, Model model) {
        ProductDto pDto = productServices.getProductDtoById(prodId);
        model.addAttribute("pDto", pDto);
        model.addAttribute("mapFactory", factoryServices.getAllFactory());
        return "editProduct";
    }

    @RequestMapping(value = "editProductAjax", method = RequestMethod.GET)
    public @ResponseBody
    String viewEditProductAjax(@RequestParam int prodId) {
        Gson gson = new Gson();
        ProductDto pDto = productServices.getProductDtoById(prodId);
        return gson.toJson(pDto);
    }
    
    @RequestMapping(value = "priceSaleAverageAjax", method = RequestMethod.GET)
    public @ResponseBody
    String getPriceSaleAverageAjax(@RequestParam int prodId) {
        List<Double> lps = productServices.getDatesPriceSaleByProductId(prodId);
        String detail = "";
        if(lps.size() >2){
            detail = "Precio Max: "+lps.get(0)+"; Precio Prom: "+lps.get(1)+"; Precio Min: "+lps.get(2);
        }else{
            detail = "No tiene precio de venta inicial.";
        }
        return detail;
    }

    @RequestMapping(value = "brachListAjax", method = RequestMethod.GET)
    public @ResponseBody
    String productEdit(@RequestParam String iname, @RequestParam String fp_i_codorigen, @RequestParam String fp_s_category, @RequestParam String fp_s_factory, @RequestParam String fp_i_description) {
        System.out.println("Entro a editar producto ajax!!!");
        return "true";
    }

    @RequestMapping(value = "productSaveEditAjax", method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public @ResponseBody
    String productSaveEditAjax(HttpSession session, @RequestBody final ProductDto pDto) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        pDto.setPriceSale((double) Math.round(pDto.getPriceSale() * 100) / 100);
        productServices.updateProductDto(pDto);
        return "true";
    }
}
