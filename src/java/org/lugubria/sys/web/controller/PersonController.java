/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import jdk.nashorn.internal.runtime.NumberToString;
import org.lugubria.sys.config.NumberToLetterConverter;
import org.lugubria.sys.crypt.AESCrypt;
import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.domain.EmployeeDetailtDto;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.Person;
import org.lugubria.sys.domain.dto.employee.EmployeeDto;
import org.lugubria.sys.service.EmployeeServices;
import org.lugubria.sys.service.PersonServices;
import org.lugubria.sys.web.form.EmployeeForm;
import org.lugubria.sys.web.form.PersonSearchForm;
import org.lugubria.sys.web.translator.PersonTranslator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping(value = "personalpp")
public class PersonController {

    private List<KeyValue> criteria;
    private String visibleDiv;
    private PersonServices personServices;
    private EmployeeServices employeeServices;

    public PersonController() {
        personServices = new PersonServices();
        employeeServices = new EmployeeServices();
        criteria = new ArrayList<KeyValue>();
        criteria.add(new KeyValue("1", "Nombres"));
        criteria.add(new KeyValue("2", "Apellido"));
        criteria.add(new KeyValue("3", "Carnet de Identidad"));
        criteria.add(new KeyValue("4", "Rol"));
        criteria.add(new KeyValue("5", "Tienda"));
        visibleDiv = "hidden";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewPersonal(Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }

//        try {
//            String password = "123";
//            System.out.println("plain pass=" + password);
//            String encryptedPassword = AESCrypt.encrypt(password);
//            System.out.println("encrypted pass=" + encryptedPassword);
//            String decryptedPassword = AESCrypt.decrypt(encryptedPassword);
//            System.out.println("decrypted pass=" + decryptedPassword);
//        } catch (Exception e) {
//            System.out.println("bug" + e.getMessage());
//        }

        PersonSearchForm psform = new PersonSearchForm();
        session.setAttribute("mapcriteria", criteria);

        List<KeyValue> branchFree = personServices.getBranchAllList();
        List<KeyValue> allRols = employeeServices.getAllRols();
        model.put("personSearchForm", psform);
        model.put("branchFree", branchFree);
        model.put("allRols", allRols);

        String literal = NumberToLetterConverter.convertNumberToLetter(new Double("25"));
        session.setAttribute("allPosition", employeeServices.getPosition());
        session.setAttribute("allBranch", personServices.getAllBranch());
        visibleDiv = "hidden";
        model.put("visibleDiv", visibleDiv);
        model.put("literal", literal);
        return "personalsearch";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid PersonSearchForm psForm, BindingResult result, Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        if (result.hasErrors()) {
            visibleDiv = "hidden";
            return "personalsearch";
        }

        List<EmployeeDetailtDto> listPerson = personServices.getPersonByCriteria(psForm.getCriteria(), psForm.getTextSearch());
        if (listPerson.size() > 0) {
            visibleDiv = "visible";
        } else {
            visibleDiv = "hidden";
        }
        model.put("visibleDiv", visibleDiv);
        model.put("listPerson", listPerson);
        return "personalsearch";
    }

    @RequestMapping(value = "personEdit/{personId}", method = RequestMethod.GET)
    public String personEdit(@PathVariable int personId, Map model, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "redirect:/loginaa.html";
        }
        Employee employee = personServices.getEmployeeByperId(personId);
        List<KeyValue> branchFree = personServices.getBranchFree(employee.getBranchId());
        EmployeeForm personForm = PersonTranslator.personToPersonForm(employee);
        personForm.px();
        model.put("personForm", personForm);
        model.put("branchFree", branchFree);
        visibleDiv = "hidden";
        model.put("visibleDiv", visibleDiv);
        return "personEdit";
    }

    @RequestMapping(value = "personEdit/personEdit", method = RequestMethod.POST)
    public String personEditSubmit(@Valid EmployeeForm personForm, BindingResult result, Map model, HttpSession session) {
        if (result.hasErrors()) {
            visibleDiv = "hidden";
            model.put("visibleDiv", visibleDiv);
            return "personEdit";
        }
        employeeServices.updateEmployee(PersonTranslator.employyeFormFromEmployee(personForm));
        return "redirect:../../personalpp.html";
    }

    @RequestMapping(value = "personDelete", method = RequestMethod.GET)
    public @ResponseBody
    String personDelete(@RequestParam int perId) {
        personServices.deletePerson(perId);
        return "" + 1;
    }

    @RequestMapping(value = "personCreate", method = RequestMethod.GET)
    public @ResponseBody
    String personCreate(@RequestParam String inames, @RequestParam String fp_i_firstname, @RequestParam String fp_i_lastname, @RequestParam String fp_i_ci, @RequestParam String fp_i_address, @RequestParam String fp_i_phone_home, @RequestParam String fp_i_phone_mobil, @RequestParam String fp_i_email, @RequestParam String fp_s_position, @RequestParam String fp_s_branch) {
        System.out.println("inames:::::: " + fp_s_position + " || " + fp_s_position + " || " + fp_i_email);
        int ci = 0;
        int phone_home = 0;
        int phone_mobil = 0;
        int brancId = 0;
        int emplId = 0;
        if (!fp_i_ci.isEmpty()) {
            ci = Integer.parseInt(fp_i_ci);
        }
        if (!fp_i_phone_home.isEmpty()) {
            phone_home = Integer.parseInt(fp_i_phone_home);
        }
        if (!fp_i_phone_mobil.isEmpty()) {
            phone_mobil = Integer.parseInt(fp_i_phone_mobil);
        }
        if (!fp_s_position.isEmpty()) {
            emplId = Integer.parseInt(fp_s_position);
        }
        if (!fp_s_branch.isEmpty()) {
            brancId = Integer.parseInt(fp_s_branch);
        }
        Employee person = new Employee();
        person.setAddress(fp_i_address);
        person.setCi(ci);
        person.setDel(false);
        person.setEmail(fp_i_email);
        person.setFirstname(fp_i_firstname);
        person.setLastname(fp_i_lastname);
        person.setNames(inames);
        person.setPerId(0);
        person.setPhoneAddress(phone_home);
        person.setPhoneMobil(phone_mobil);
        person.setEmpId(emplId);
        person.setBranchId(brancId);
        boolean val = personServices.createEmployee(person);

        return "1";
    }

    @RequestMapping(value = "personCreate", method = RequestMethod.POST)
    public @ResponseBody
    String personCreatePost(@RequestParam String person) {
        Gson gson = new Gson();
        Person per = gson.fromJson(person, Person.class);
        per = personServices.updateP(per);

        return gson.toJson(per);
    }

    @RequestMapping(value = "getPerson", method = RequestMethod.POST)
    public @ResponseBody
    String personCreatePost(@RequestParam int ci) {
        Person per = personServices.getPersonFromCi(ci);
        Gson gson = new Gson();
        return gson.toJson(per);
//        if(per != null)
//            return gson.toJson(per);
//        return "{}";
    }

    @RequestMapping(value = "employeeCreate", method = RequestMethod.GET)
    public @ResponseBody
    String employeeCreate(@RequestParam String inames, @RequestParam String fp_i_firstname, @RequestParam String fp_i_lastname, @RequestParam String fp_i_ci, @RequestParam String fp_i_address, @RequestParam String fp_i_phone_home, @RequestParam String fp_i_phone_mobil, @RequestParam String fp_i_email, @RequestParam String fp_s_position, @RequestParam String fp_s_branch) {
        int ci = 0;
        int phone_home = 0;
        int phone_mobil = 0;
        int brancId = 0;
        int emplId = 0;
        if (!fp_i_ci.isEmpty()) {
            ci = Integer.parseInt(fp_i_ci);
        }
        if (!fp_i_phone_home.isEmpty()) {
            phone_home = Integer.parseInt(fp_i_phone_home);
        }
        if (!fp_i_phone_mobil.isEmpty()) {
            phone_mobil = Integer.parseInt(fp_i_phone_mobil);
        }
        if (!fp_s_branch.isEmpty()) {
            brancId = Integer.parseInt(fp_s_branch);
        }
        Employee person = new Employee();

        person.setAddress(fp_i_address);
        person.setCi(ci);
        person.setDel(false);
        person.setEmail(fp_i_email);
        person.setFirstname(fp_i_lastname);
        person.setLastname(fp_i_lastname);
        person.setNames(inames);
        person.setPerId(0);
        person.setPhoneAddress(phone_home);
        person.setPhoneMobil(phone_mobil);
        person.setEmpId(emplId);
        person.setBranchId(brancId);
        person.setPosition(fp_s_position);
        boolean val = employeeServices.createEmployee(person);

        return "1";
    }

    @RequestMapping(value = "personValidateCi", method = RequestMethod.GET)
    public @ResponseBody
    String personValidateCi(@RequestParam int fp_i_ci) {
        System.out.println("Ci a comprobar::: " + fp_i_ci);
        return "" + !personServices.validCi(fp_i_ci);
    }

    @RequestMapping(value = "branchJson", method = RequestMethod.GET)
    public @ResponseBody
    String productEdit(@RequestParam String valfunction, @RequestParam int branchId) {
        List<KeyValue> branchList;
        if (valfunction.equalsIgnoreCase("Administrador")) {
            branchList = personServices.getBranchFree(branchId);
        } else {
            branchList = personServices.getBranchAllList();
        }
        Gson gson = new Gson();
        String result = gson.toJson(branchList);
        //return branchList;
        return result;
    }

    @RequestMapping(value = "getListPersonByCriteria", method = RequestMethod.GET)
    public @ResponseBody
    String getListProductByCriteria(@RequestParam int criteria, @RequestParam String textSearch, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        Gson gson = new Gson();
        return "{\"aaData\": " + gson.toJson(personServices.getPersonByCriteria(criteria, textSearch)) + "}";

    }

    @RequestMapping(value = "ciExistEmployee", method = RequestMethod.GET)
    public @ResponseBody
    String ciExistEmployee(@RequestParam int ci, HttpSession session) {
        if (session.getAttribute("Person") == null) {
            return "false";
        }
        return employeeServices.ciExistEmployee(ci);

    }

    @RequestMapping(value = "uploadEmployeeAjax", method = RequestMethod.POST)
    public @ResponseBody
    String uploadClientAjax(@RequestParam String client) throws Exception {
        Gson gson = new Gson();
        EmployeeDto cl = gson.fromJson(client, EmployeeDto.class);
        cl.setPassword(AESCrypt.encrypt(cl.getPassword()));
        employeeServices.createEmployee(cl);
        return "true";
    }
}
