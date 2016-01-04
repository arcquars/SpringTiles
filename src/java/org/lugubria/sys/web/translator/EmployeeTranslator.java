/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.translator;

import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.web.form.EmployeeForm;

/**
 *
 * @author angel
 */
public class EmployeeTranslator {
    public static Employee employeeFormToEmployee(EmployeeForm employeeForm){
        Employee employee = null;
        if (employeeForm == null) 
            return null;
        employee = new  Employee();
        employee.setAddress(employeeForm.getAddress());
        employee.setBranchId(employeeForm.getBranch());
        employee.setCi(employeeForm.getCi());
        employee.setDel(employeeForm.isDel());
        employee.setEmail(employeeForm.getEmail());
        employee.setEmpId(0);
        employee.setFirstname(employeeForm.getFirstname());
        employee.setLastname(employeeForm.getLastname());
        employee.setNames(employeeForm.getNames());
        employee.setPerId(employeeForm.getPerId());
        employee.setPhoneAddress(employeeForm.getPhone_address());
        employee.setPhoneMobil(employeeForm.getPhone_mobil());
        employee.setPosition(employeeForm.getPosition()+"");
        return employee;
    }
}
