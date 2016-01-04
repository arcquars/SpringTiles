/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import java.util.Map;
import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.dto.employee.EmployeeDto;
import org.lugubria.sys.domain.dto.employee.EmployeeSimpleDto;

/**
 *
 * @author angel
 */
public interface IEmployeeRepository {
    
    public boolean createEmployee(Employee epmloyee);
    
    public Map<String, String> getPosition();
    
    public boolean updateEmployee(Employee employee);
    
    public List<EmployeeSimpleDto> listAllVendor();
    
    public String ciExistEmployee(int ci);
    
    public boolean createEmployee(EmployeeDto employee);
    
    public List<KeyValue> getAllRols();
}
