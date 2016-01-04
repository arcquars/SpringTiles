/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import java.util.Map;
import org.lugubria.sys.domain.Employee;
import org.lugubria.sys.domain.EmployeeDetailtDto;
import org.lugubria.sys.domain.KeyValue;
import org.lugubria.sys.domain.Person;

/**
 *
 * @author angel
 */
public interface IPersonRepository {
    
    public Employee getEmployeeByperId(int perId);
    
    public List<EmployeeDetailtDto> getPersonByCriteria(int type, String criteria);
    
    public void updatePerson(Person person);
    
    public Person updateP(Person person);
    
    public void deletePerson(int perId);
    
    public boolean createEmployee(Employee person);
    
    public boolean validCi(int newCi);
    
    public String getFirstNameFromCi(int ci);
            
    public List<KeyValue> getAllBranch();
    
    public Map<String, String> getAllEmployeePosition();
    
    public Person getPersonByUsernamePass(String username, String pass);
    
    public List<KeyValue> getBranchFree(int branchId);
    
    public List<KeyValue> getBranchAllList();
    
    public Person getPersonFromCi(int ci);
}
