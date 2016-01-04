/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.translator;

import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.domain.Person;
import org.lugubria.sys.web.form.EmployeeForm;

/**
 *
 * @author angel
 */
public class PersonTranslator {

    public static EmployeeForm personToPersonForm(Employee employee) {
        EmployeeForm personForm = null;

        if (employee == null) {
            return null;
        }
        personForm = new EmployeeForm();

        personForm.setPerId(employee.getPerId());
        personForm.setNames(employee.getNames());
        personForm.setFirstname(employee.getFirstname());
        personForm.setLastname(employee.getLastname());
        personForm.setCi(employee.getCi());
        personForm.setAddress(employee.getAddress());
        personForm.setPhone_address(employee.getPhoneAddress());
        personForm.setPhone_mobil(employee.getPhoneMobil());
        personForm.setEmail(employee.getEmail());
        personForm.setDel(employee.isDel());
        personForm.setBranch(employee.getBranchId());
        personForm.setPosition(employee.getPosition());
        
//        personForm.setPassword(employee.getPassword());
        return personForm;
    }

    public static Person personFromToPerson(EmployeeForm personForm) {
        Person personUpdate = null;

        if (personForm == null) {
            return null;
        }

        personUpdate = new Person();
        personUpdate.setAddress(personForm.getAddress());
        personUpdate.setCi(personForm.getCi());
        personUpdate.setDel(personForm.isDel());
        personUpdate.setEmail(personForm.getEmail());
        personUpdate.setFirstname(personForm.getFirstname());
        personUpdate.setLastname(personForm.getLastname());
        personUpdate.setNames(personForm.getNames());
        personUpdate.setPerId(new Integer(personForm.getPerId()));
        personUpdate.setPhoneAddress(new Integer(personForm.getPhone_address()));
        personUpdate.setPhoneMobil(new Integer(personForm.getPhone_mobil()));

        return personUpdate;
    }

    public static Employee employyeFormFromEmployee(EmployeeForm personForm) {
        Employee employeeUpdate = null;

        if (personForm == null) {
            return null;
        }

        employeeUpdate = new Employee();
        employeeUpdate.setAddress(personForm.getAddress());
        employeeUpdate.setCi(personForm.getCi());
        employeeUpdate.setDel(personForm.isDel());
        employeeUpdate.setEmail(personForm.getEmail());
        employeeUpdate.setFirstname(personForm.getFirstname());
        employeeUpdate.setLastname(personForm.getLastname());
        employeeUpdate.setNames(personForm.getNames());
        employeeUpdate.setPerId(new Integer(personForm.getPerId()));
        employeeUpdate.setPhoneAddress(new Integer(personForm.getPhone_address()));
        employeeUpdate.setPhoneMobil(new Integer(personForm.getPhone_mobil()));
        
        employeeUpdate.setBranchId(personForm.getBranch());
        if(personForm.getPosition().equalsIgnoreCase("Administrador"))
            employeeUpdate.setPosition("Administrador");
        else
            employeeUpdate.setPosition("Vendedor");

        return employeeUpdate;
    }
}
