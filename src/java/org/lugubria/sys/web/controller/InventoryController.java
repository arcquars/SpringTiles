/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.Produces;
import org.lugubria.sys.domain.*;
import org.lugubria.sys.domain.dto.employee.EmployeeSimpleDto;
import org.lugubria.sys.service.*;
import org.lugubria.sys.web.form.FormBranch;
import org.lugubria.sys.web.form.FormReserve;
import org.lugubria.sys.web.form.ProductSearchForm;
import org.lugubria.sys.web.form.SaleGainForm;
import org.lugubria.sys.web.form.SaleReserveForm;
import org.lugubria.sys.web.form.SalesAmountForm;
import org.lugubria.sys.web.modelDatabase.ReserveJsonList;
import org.lugubria.sys.web.translator.ReserveJsonTranslator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping(value = "inventorypp")
public class InventoryController {

    private List<KeyValue> criteria;
    private String visibleTable;
    private String visibleTableEmpty;
    private CategoryServices categoryServices;
    private ProductServices productServices;
    private ProviderServices providerServices;
    private FactoryServices factoryServices;
    private RecordProductServices recordProduct;
    private BuyServices buyservices;
    private BranchServices branchServices;
    private DeliveryServices deliveryServices;
    private RecordProductBranchServices rpbServices;
    private SaleServices saleServices;
    private DetailRefundServices detailRefundServices;
    private PersonServices personServices;
    private ReserveServices reserveServices;
    private EmployeeServices employeeServices;

    public InventoryController() {
        categoryServices = new CategoryServices();
        productServices = new ProductServices();
        providerServices = new ProviderServices();
        factoryServices = new FactoryServices();
        recordProduct = new RecordProductServices();
        buyservices = new BuyServices();
        branchServices = new BranchServices();
        deliveryServices = new DeliveryServices();
        rpbServices = new RecordProductBranchServices();
        saleServices = new SaleServices();
        detailRefundServices = new DetailRefundServices();
        personServices = new PersonServices();
        reserveServices = new ReserveServices();
        employeeServices = new EmployeeServices();
        
        criteria = new ArrayList<KeyValue>();
        KeyValue kv = new KeyValue("1", "Nombre");
        criteria.add(kv);
        kv = new KeyValue("2", "Codigo");
        criteria.add(kv);
        kv = new KeyValue("3", "Categoria");
        criteria.add(kv);
        visibleTable = "none";
        visibleTableEmpty = "block";
    }

    @ModelAttribute("allProvider")
    public List<KeyValue> getAllProvider() {
        return providerServices.getAllProvider();
    }

    @ModelAttribute("allBranch")
    public List<KeyValue> getAllBranch() {
        return branchServices.getAllBranch();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewProduct(Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        ProductSearchForm psform = new ProductSearchForm();
        session.setAttribute("mapcriteria", criteria);
        model.put("productSearchForm", psform);
        session.setAttribute("mapCategory", categoryServices.getAllCategory());
        session.setAttribute("mapProvider", providerServices.getAllProvider());
        session.setAttribute("mapFactory", factoryServices.getAllFactory());

        List<RecordProductDto> listRpdto = null;
        RecordProductList rpList = recordProduct.getListByCriteria(1, "%");
        listRpdto = rpList.getList();
        model.put("grantotal", Math.round(rpList.getGranTotal() * 100.0) / 100.0);
        model.put("listProduct", listRpdto);
        model.put("visibleTable", "none");
        model.put("visibleTableEmpty", "block");
        if (listRpdto.size() > 0) {
            model.put("visibleTable", "block");
            model.put("visibleTableEmpty", "none");
        }
        return "inventorysearch";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid ProductSearchForm psForm, BindingResult result, Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        if (result.hasErrors()) {
            visibleTable = "none";
            visibleTableEmpty = "block";
            model.put("visibleTable", visibleTable);
            model.put("visibleTableEmpty", visibleTableEmpty);
            return "inventorysearch";
        }
        List<RecordProductDto> listRpdto = null;
        RecordProductList rpList = null;
        if (psForm.getCriteria() == 3) {
            rpList = recordProduct.getListByCriteria(psForm.getCriteria(), "" + psForm.getCategory());
            listRpdto = rpList.getList();
        } else {
            rpList = recordProduct.getListByCriteria(psForm.getCriteria(), "" + psForm.getTextSearch());
            listRpdto = rpList.getList();
        }
        if (listRpdto.size() > 0) {
            System.out.println("Result list size:: " + listRpdto.size());
            visibleTable = "block";
            visibleTableEmpty = "none";
            model.put("listProduct", listRpdto);
        } else {
            visibleTable = "none";
            visibleTableEmpty = "block";
        }
        model.put("grantotal", rpList.getGranTotal());
        model.put("visibleTable", visibleTable);
        model.put("visibleTableEmpty", visibleTableEmpty);
        return "inventorysearch";
    }

    @RequestMapping(value = "createProvider", method = RequestMethod.GET)
    public String viewCreateProvider() {
        return "createProvider";
    }

    @RequestMapping(value = "createFactory", method = RequestMethod.GET)
    public String viewCreateFactory() {
        return "createFactory";
    }

    @RequestMapping(value = "inventoryNewProductAjax", method = RequestMethod.GET)
    public @ResponseBody
    String inventoryNewProductAjax(@RequestParam String inames, @RequestParam String fp_i_codorigen, @RequestParam String fp_i_category, @RequestParam String fp_i_description, @RequestParam String fp_s_provider, @RequestParam String fp_i_amount, @RequestParam String fp_i_priceunit, @RequestParam String fp_i_pricetotal, @RequestParam String tipobuy, @RequestParam String fp_i_payment) {
        ProductRegisterInventaryDto pid = new ProductRegisterInventaryDto();
        pid.setAmountBuy(Integer.parseInt(fp_i_amount));
        pid.setCategoryId(Integer.parseInt(fp_i_category));
        pid.setCodigoProduct(fp_i_codorigen);
        pid.setDescriptionProduct(fp_i_description);
        pid.setNameproduct(inames);
        if (!fp_i_payment.isEmpty()) {
            pid.setPayment(Double.parseDouble(fp_i_payment));
        }
        pid.setPriceTotalBuy(Double.parseDouble(fp_i_pricetotal));
        pid.setPriceunitBuy(Double.parseDouble(fp_i_priceunit));
        pid.setProviderId(Integer.parseInt(fp_s_provider));
        pid.setTypeBuy(Boolean.parseBoolean(tipobuy));

        System.out.println("Entro a crear prodcuto desde inventario!!!");
        return "" + productServices.createProductInventory(pid);
    }

    @RequestMapping(value = "createBuy", method = RequestMethod.GET)
    public String viewCreateBuy(@RequestParam String ids, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        System.out.println("Entro a crear Compra!!!");
        List<RecordProductDto> listRpd = productServices.getListRecordProduct(ids);
        System.out.println("size::::::: " + listRpd.size());
        BuyProducts buyProduct = new BuyProducts();
        buyProduct.setLisRpd(listRpd);
        buyProduct.setCredit("F");
        buyProduct.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));

        model.addAttribute("lisRpdObject", buyProduct);
        return "createBuy";
    }

    @RequestMapping(value = "createBuy", method = RequestMethod.POST)
    public String viewCreateBuy(@ModelAttribute("lisRpdObject") BuyProducts buyProducts, ModelMap model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        if (buyProducts.getProviderId() == 0) {
            model.put("Error", "Elija un proveedor!!");
            return "createBuy";
        }
        boolean valid = false;

        Iterator<RecordProductDto> list = buyProducts.getLisRpd().iterator();
        while (list.hasNext()) {
            RecordProductDto recordProductDto = list.next();
            if (recordProductDto.getAmount() == 0) {
                valid = true;
                break;
            }

        }
        if (valid) {
            model.put("Error", "Las cantidades no pueden ser cero!!");
            return "createBuy";
        }
        buyservices.saveBuy(buyProducts);

        return "redirect:../inventorypp.html";
    }

    @RequestMapping(value = "deliveryBranch", method = RequestMethod.GET)
    public String viewDeliveryBranch(@RequestParam String ids, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = df.format(new Date());
        DeliveryBranch db = productServices.getListDeliveryProduct(ids);
        
        model.addAttribute("dateNow", formattedDate);
        model.addAttribute("deliveryBranch", db);
        return "deliveryBranch";
    }

    @RequestMapping(value = "deliveryBranch", method = RequestMethod.POST)
    public String viewDeliveryBranch(@ModelAttribute("deliveryBranch") DeliveryBranch deliveryBranch, ModelMap model, HttpSession session) {
        if (deliveryBranch.getBranchId() == 0) {
            model.addAttribute("error", "Seleccione una Sucursal");
            return "deliveryBranch";
        }
        deliveryBranch.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        Iterator<DeliveryProducts> iterator = deliveryBranch.getDeliveryProduct().iterator();
        while (iterator.hasNext()) {
            DeliveryProducts deliveryProducts = iterator.next();
            if (deliveryProducts.getAmount() <= 0) {
                model.addAttribute("error", "La cantidad de entrega no puede ser cero");
                return "deliveryBranch";
            }

        }
        deliveryServices.saveDelivery(deliveryBranch);
        return "redirect:../inventorypp.html";
    }

    @RequestMapping(value = "inventoryBranch", method = RequestMethod.GET)
    public String viewInventoryBranch(Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        FormBranch formBranch = new FormBranch();
        model.addAttribute("formBranch", formBranch);
        model.addAttribute("mapcriteria", criteria);
        model.addAttribute("mapCategory", categoryServices.getAllCategory());
        boolean visible = false;
        model.addAttribute("visible", visible);
        return "inventoryBranch";
    }

    @RequestMapping(value = "inventoryBranch", method = RequestMethod.POST)
    public String viewInventoryBranchPost(@Valid FormBranch formBranch, BindingResult result, Model model) {
        model.addAttribute("mapcriteria", criteria);
        model.addAttribute("mapCategory", categoryServices.getAllCategory());
        List<RecordProductBranchDto> list = null;
        String textt = formBranch.getTextSearch();
        if (formBranch.getCriteria() == 3) {
            textt = formBranch.getCategory() + "";
        }
        list = rpbServices.getListRpbByBranch(formBranch.getBranchId(), formBranch.getCriteria(), textt);
        boolean visible = false;
        if (list.size() > 0) {
            visible = true;
        }
        model.addAttribute("visible", visible);
        System.out.println("List size:: " + list.size());
        model.addAttribute("formBranch", formBranch);
        model.addAttribute("listProduct", list);
        if (result.hasErrors()) {
            System.out.println("Entro al error");
            return "inventoryBranch";
        }
        return "inventoryBranch";
    }

    @RequestMapping(value = "createSale", method = RequestMethod.GET)
    public String viewCreateSale(@RequestParam String ids, @RequestParam int branchId, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        ListProductSale list = saleServices.getListByProductIdsAndBranchId(ids, branchId);
        list.setBranchId(branchId);
        
        List<EmployeeSimpleDto> listVendor = employeeServices.listAllVendor();

        model.addAttribute("listVendor", listVendor);
        model.addAttribute("listProductSale1", list);
        if (list != null) {
            System.out.println("Lista no nula y es de tamaño:: " + list.getListProductSale().size());
        }

        return "createSale";
    }

    @RequestMapping(value = "createSale", method = RequestMethod.POST)
    public String viewCreateSalePost(@ModelAttribute("listProductSale1") ListProductSale list, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        System.out.println("entro a crear Venta POST!!");
        System.out.println("branchId:: " + list.getBranchId());
        System.out.println("fechaVenta:: " + list.getDateSale());
        System.out.println("credit:: " + list.isCredit());
        System.out.println("Total:: " + list.getTotal());
        list.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        boolean valid;
        String errorCi = "";
        
        if(list.getClientId()>= 0 && !saleServices.saveListproductSale(list).equals(""))
//        if(list.getClientId()>= 0)
            valid = true;
        else{
            errorCi = "Ci no valido.";
            model.addAttribute("Error", errorCi);
            return "createSale";
        }
        if (list.getBranchId() == 0) {
            return "redirect:../inventorypp.html";
        } else {
            return "redirect:inventoryBranch.html";
        }
    }

    @RequestMapping(value = "createReserve", method = RequestMethod.GET)
    public String viewCreateReserve(@RequestParam String ids, @RequestParam int branchId, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        ProductReserve list = saleServices.getListReserveByProductIdsAndBranchId(ids, branchId);
        list.setBranchId(branchId);
        model.addAttribute("listProductReserve", list);
        model.addAttribute("branchName", list.getBranchName());
        return "createReserve";
    }

    @RequestMapping(value = "createReserve", method = RequestMethod.POST)
    public String viewCreateReservePost(@ModelAttribute("listProductSale1") ProductReserve list, Model model, HttpSession session) throws ParseException {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        list.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(list.getDateSale());
        
        boolean valid = saleServices.saveListproductReserve(list);
        if (list.getBranchId() == 0) {
            return "redirect:../inventorypp.html";
        } else {
            return "redirect:inventoryBranch.html";
        }
    }

    @RequestMapping(value = "createRefund", method = RequestMethod.GET)
    public String viewCreateRefund(@RequestParam String ids, @RequestParam int branchId, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        System.out.println("entro a crear devolucion!!");

        model.addAttribute("listProductRefund", detailRefundServices.getListByIdsAndBranchId(ids, branchId));
        return "createRefund";
    }

    @RequestMapping(value = "createRefund", method = RequestMethod.POST)
    public String viewCreateRefund(@ModelAttribute("listProductRefund") ListProductRefund list, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        System.out.println("entro a crear devolucion!!");
        list.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        boolean valid = detailRefundServices.saveListProductRefund(list);
        System.out.println("valid :: " + valid);
        return "redirect:inventoryBranch.html";
    }

    @RequestMapping(value = "saleGain", method = RequestMethod.GET)
    public String viewSaleGain(Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        model.addAttribute("allBranch1", branchServices.getAllBranchWithMain());

        return "saleGain";
    }

    @RequestMapping(value = "saleGain", method = RequestMethod.POST)
    public String viewSaleGainPost(@ModelAttribute("saleGainForm") SaleGainForm saleGainForm, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        if (saleGainForm.getBranchId() == -1) {
            model.addAttribute("error", "Seleccione una Sucursal");
            return "saleGain";
        }
        System.out.println("branchId::: " + saleGainForm.getBranchId());
        ListSaleDetailGain lsdg = saleServices.getSaleDetail(saleGainForm.getBranchId(), saleGainForm.getDateStart(), saleGainForm.getDateEnd());
        if (lsdg.getList().size() > 0) {
            model.addAttribute("visible", true);
            model.addAttribute("lsdg", lsdg);
        }
        return "saleGain";
    }

    @RequestMapping(value = "salesAccount", method = RequestMethod.GET)
    public String viewSalesAccount(Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        
        SaleGainForm saleGainForm = new SaleGainForm();
        
        SalesAmountForm saleAmountForm = new SalesAmountForm();
        
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        int branchId = saleGainForm.getBranchId();
        
        List<KeyValue> listDates = saleServices.lastSaleAmountDateByBranchId(branchId);
        String dateStart = listDates.get(0).getValue();
        String dateEnd = listDates.get(1).getValue();
        if(dateStart.equalsIgnoreCase("false"))
            dateStart = "";
        saleGainForm.setDateStart(dateStart);
        
        ListSalesAccount lsdg = saleServices.getListSaleAccount(0, dateStart, dateEnd);
        if (lsdg.getListSales().size() > 0) {
            model.addAttribute("lsdg", lsdg);
        }
        saleGainForm.setBranchId(0);
        saleGainForm.setDateEnd(dateEnd);
        
        saleAmountForm.setBranchId(saleGainForm.getBranchId());
        saleAmountForm.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        saleAmountForm.setAmount(lsdg.getTotalReserve()+lsdg.getTotalReserveClose()+lsdg.getTotalSale()+lsdg.getTotalCreditPayment());
        saleAmountForm.setTotalSale(lsdg.getTotalSale());
        saleAmountForm.setTotalReserveClose(lsdg.getTotalReserveClose());
        saleAmountForm.setTotalReserveOpen(lsdg.getTotalReserve());
        saleAmountForm.setTotalSaleCredit(lsdg.getTotalCreditPayment());
        saleAmountForm.setBranchId(saleGainForm.getBranchId());
        saleAmountForm.setDateEnd(saleGainForm.getDateEnd());
        saleAmountForm.setDateStart(saleGainForm.getDateStart());
        
        model.addAttribute("allBranch1", branchServices.getAllBranchWithMain());
        model.addAttribute("salesAcountForm", saleGainForm);
        model.addAttribute("saleAmountForm", saleAmountForm);
        
        return "salesAccount";
    }
    
    @RequestMapping(value = "salesAccount", method = RequestMethod.POST)
    public String viewSalesAccountPost(@ModelAttribute("salesAcountForm") SaleGainForm saleGainForm, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        
        if (saleGainForm.getBranchId() == -1) {
            model.addAttribute("error", "Seleccione una Sucursal");
            return "salesAccount";
        }

        ListSalesAccount lsdg = saleServices.getListSaleAccount(saleGainForm.getBranchId(), saleGainForm.getDateStart(), saleGainForm.getDateEnd());
        if (lsdg.getListSales().size() > 0) {
            model.addAttribute("lsdg", lsdg);
        }
        Person person = (Person)session.getAttribute("Person");
        
        SalesAmountForm saleAmountForm = new SalesAmountForm();
        double dd = (double)Math.round((lsdg.getTotalReserve()+lsdg.getTotalReserveClose()+lsdg.getTotalSale()+lsdg.getTotalCreditPayment()) * 100) / 100;
        saleAmountForm.setAmount(dd);
        saleAmountForm.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        saleAmountForm.setTotalSale(lsdg.getTotalSale());
        saleAmountForm.setTotalReserveClose(lsdg.getTotalReserveClose());
        saleAmountForm.setTotalReserveOpen(lsdg.getTotalReserve());
        saleAmountForm.setTotalSaleCredit(lsdg.getTotalCreditPayment());
        saleAmountForm.setBranchId(saleGainForm.getBranchId());
        saleAmountForm.setDateEnd(saleGainForm.getDateEnd());
        saleAmountForm.setDateStart(saleGainForm.getDateStart());
//        saleAmountForm.set
        model.addAttribute("personName", person.getNames());
        model.addAttribute("branchIPost", saleGainForm.getBranchId());
        model.addAttribute("allBranch1", branchServices.getAllBranchWithMain());
        model.addAttribute("salesAcountForm", saleGainForm);
        model.addAttribute("saleAmountForm", saleAmountForm);
        return "salesAccount";
    }
    
    @RequestMapping(value = "salesAccountSave", method = RequestMethod.POST)
    public String viewSalesAccountSave(@ModelAttribute("saleAmountForm") SalesAmountForm saleAmountForm, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        SalesAmount sa = new SalesAmount();
        sa.setActive(Boolean.TRUE);
        sa.setAmount(saleAmountForm.getAmount());
        sa.setBranchId(saleAmountForm.getBranchId());
        sa.setDateEnd(saleAmountForm.getDateEnd());
        sa.setDateStart(saleAmountForm.getDateStart());
        sa.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        
        saleServices.saveSalesAccount(sa);
        return "redirect:/inventorypp/salesAccount.html";
    }
    
    @RequestMapping(value = "saleReserve", method = RequestMethod.GET)
    public String viewSaleReserve(Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        return "saleReserve";
    }
    
    @RequestMapping(value = "saleReserveListAjax", method = RequestMethod.GET)
    public @ResponseBody
        String viewSaleReserveAjax(@RequestParam int branchId, @RequestParam String reserveDate, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        Gson gson = new Gson();
        boolean all = true;
        if(branchId != -1){
            all = false;
        }
        ReserveJsonList rjList = ReserveJsonTranslator.reserveToReservejson(saleServices.getListReserve(branchId, reserveDate, all));
        return gson.toJson(rjList);
    }
    
    @RequestMapping(value = "saleReserve", method = RequestMethod.POST)
    public String viewSaleReservePost(@ModelAttribute("saleReserveForm") SaleReserveForm saleReserveForm, Model model, HttpSession session) {
        
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        if (saleReserveForm.getBranchId() == -1) {
            model.addAttribute("error", "Seleccione una Sucursal");
            return "saleGain";
        }
        
        System.out.println("branchId::: " + saleServices.getListReserve(0, "2014-06-04", true).size());
        return "saleReserve";
    }
    
    @RequestMapping(value = "getPersonName", method = RequestMethod.POST)
    public @ResponseBody
    String getPersonNameAjax(@RequestParam int ci) {
        String firstname = personServices.getFirstNameFromCi(ci);
        if (firstname != null) {
            return firstname;
        }
        return "";
    }
    
    @RequestMapping(value = "getPersonAjax", method = RequestMethod.POST)
    public @ResponseBody
    String getPersonAjax(@RequestParam int ci, HttpSession session) {
        Person person = personServices.getPersonFromCi(ci);
        if (session.getAttribute("Person") == null) {
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(person);
    }

    @RequestMapping(value = "createPersonAjax", method = RequestMethod.POST)
    public @ResponseBody
    String createPersonAjax(@RequestParam int fp_i_ci, @RequestParam String p_firstname) {
        //String firstname = personServices.getFirstNameFromCi(ci);
        Person person = new Person();
        person.setCi(fp_i_ci);
        person.setFirstname(p_firstname);

        personServices.updatePerson(person);
        return p_firstname;
    }
    
    @RequestMapping(value = "validateCiAjax", method = RequestMethod.POST)
    public @ResponseBody
    String validateCiAjax(@RequestParam int fp_i_ci) {
        boolean valid = personServices.validCi(fp_i_ci);
        return "" + !valid;
    }

    @RequestMapping(value = "saveSaleAjax", method = RequestMethod.POST)
    public @ResponseBody
    String createSaleAjax(@RequestParam String jsonSale, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        Gson gson = new Gson();
        
        ListProductSale list = gson.fromJson(jsonSale, ListProductSale.class);
        
        String idSale = saleServices.saveListproductSale(list);
        //String idSale = "true";
        
        return idSale;
//        return "xxxx";
//        if (list.getBranchId() == 0) {
//            return gson.toJson(idSale);
//        } else {
//            return i;
//        }
    }
    
    @RequestMapping(value = "saveReserveAjax", method = RequestMethod.POST)
    public @ResponseBody
    String createReserveAjax(@RequestParam String jsonSale, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        Gson gson = new Gson();
        
        ProductReserve list = gson.fromJson(jsonSale, ProductReserve.class);

        list.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        
        boolean valid = saleServices.saveListproductReserve(list);
        //boolean valid = true;
        if(valid){
            if (list.getBranchId() == 0) {
                return "/inventorypp.html";
            } else {
                return "/inventoryBranch.html";
            }
        }else
            return "";
        
    }
    
    @RequestMapping(value = "recordProductAjax", method = RequestMethod.POST)
    public @ResponseBody
    String recordProductAjax(@RequestParam String criteria, @RequestParam String category, 
            @RequestParam String textSearch, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        List<RecordProductDto> listRpdto = null;
        if (criteria.equalsIgnoreCase("3")) {
            listRpdto = recordProduct.getListByCriteriaPrint(Integer.parseInt(criteria), category);
        } else {
            listRpdto = recordProduct.getListByCriteriaPrint(Integer.parseInt(criteria), textSearch);
        }
        Gson gson = new Gson();
        String list = gson.toJson(listRpdto);
        return list;
    }
    
    @RequestMapping(value = "recordProductByBranchIdAjax", method = RequestMethod.POST)
    public @ResponseBody
    String recordProductByBranchIdAjax(@RequestParam String branchId, @RequestParam String criteria, @RequestParam String category, 
            @RequestParam String textSearch, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        
        List<RecordProductBranchDto> list = null;
        String textt = textSearch;
        if (criteria.equalsIgnoreCase("3")) {
            textt = category;
        }
        list = rpbServices.getListRpbByBranch(Integer.parseInt(branchId), Integer.parseInt(criteria), textt);
        
        Gson gson = new Gson();
        String list1 = gson.toJson(list);
        return list1;
    }
    
    @RequestMapping(value = "reserveDetail", method = RequestMethod.GET)
    public String viewCreateReserve(@RequestParam String idReserve, Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(new Date());
        ReserveDetail rd = saleServices.getReserveDetail(Integer.parseInt(idReserve));
        model.addAttribute("reserveDetail", rd);
        model.addAttribute("dateNow", formattedDate);
        return "reserveDetail";
    }
    
    @RequestMapping(value = "reserveDetailAjax", method = RequestMethod.POST)
    public @ResponseBody
    String reserveDetailAjax(@RequestParam int reserveId, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(new Date());
        ReserveDetail rd = saleServices.getReserveDetail(reserveId);
        rd.setDatePick(formattedDate);
        Gson gson = new Gson();
        
        return gson.toJson(rd);
//        return "truepp";
    }
    
    @RequestMapping(value = "reserveSaveAjax", method = RequestMethod.POST)
    public @ResponseBody
    String reserveSaveAjax(@RequestParam int reserveId, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        return saleServices.reserveUpdate(reserveId)+"";
//        return "truepp";
    }
    
    @RequestMapping(value = "saleAmountSaveAjax", method = RequestMethod.POST)
    public @ResponseBody
        String saleAmountSaveAjax(@RequestParam int branchId, @RequestParam double amount, @RequestParam String dateStart, @RequestParam String dateEnd, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        SalesAmount sa = new SalesAmount();
        sa.setActive(Boolean.TRUE);
        sa.setAmount(amount);
        sa.setBranchId(branchId);
        sa.setDateEnd(dateEnd);
        sa.setDateStart(dateStart);
        sa.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        
        return ""+saleServices.saveSalesAccount(sa);
//        return "truepp";
    }
        
    @RequestMapping(value = "getDatesSalesAccoutnByBranchId", method = RequestMethod.POST)
    public @ResponseBody
        String getDatesSalesAccountByBranchId(@RequestParam int branchId, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "";
        }
        
        String[] datesAmount = new SaleServices().getLastSaleAmountDates(branchId);
        Gson gson = new Gson();
        return gson.toJson(datesAmount);
//        return "truepp";
    }    
        
    @RequestMapping(value = "reserveStore", method = RequestMethod.GET)
    public String viewInventoryReserveStore(Model model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        FormReserve formBranch = new FormReserve();
        List<RecordProductDto> list = reserveServices.getListProductReserve(formBranch.getBranchId());
        model.addAttribute("formBranch", formBranch);
        model.addAttribute("allBranch1", branchServices.getAllBranchWithMain());
        model.addAttribute("listProduct", list);
        boolean visible = true;
        model.addAttribute("visible", visible);
        return "reserveStore";
    }
    
    @RequestMapping(value = "reserveStore", method = RequestMethod.POST)
    public String viewInventoryReserveStorePost(@Valid FormReserve formBranch, BindingResult result, Model model) {
        
        model.addAttribute("allBranch1", branchServices.getAllBranchWithMain());
        List<RecordProductDto> list = null;
        
        //list = rpbServices.getListRpbByBranch(formBranch.getBranchId(), formBranch.getCriteria(), textt);
        list = reserveServices.getListProductReserve(formBranch.getBranchId());
        boolean visible = false;
        if (list.size() > 0) {
            visible = true;
        }
        model.addAttribute("visible", visible);
        System.out.println("aaaaaaaaa  List size:: " + list.size());
        model.addAttribute("formBranch", formBranch);
        model.addAttribute("listProduct", list);
        
        if (result.hasErrors()) {
            System.out.println("Entro al error");
            return "reserveStore";
        }
        
        return "reserveStore";
    }
    
    @RequestMapping(value = "saleSelect", method = RequestMethod.GET)
    public String saleSelect(HttpSession session, Model model) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        
        model.addAttribute("allBranch", branchServices.getAllBranchWithMain());
        
        return "saleSelect";
    }
    
    @RequestMapping(value = "saleReceive", method = RequestMethod.GET)
    public String saleReceive(HttpSession session, Model model) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        
        model.addAttribute("allBranch", branchServices.getAllBranchWithMain());
        
        return "saleReceive";
    }
    
    @RequestMapping(value = "saleReport", method = RequestMethod.GET)
    public String saleReport(HttpSession session, Model model) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        
        model.addAttribute("allBranch", branchServices.getAllBranchWithMain());
        
        return "saleReport";
    }
    
        
}