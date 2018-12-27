package net.atos.testeatos.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.testeatos.model.Employee;
import net.atos.testeatos.repository.EmployeeRepository;

/**
 * Service class that implements the {@link net.atos.testeatos.services
 * .EmployeeService EmployeeRestService} interface. Defines what methods from
 * {@link net.atos.testeatos.repository.employeeRepository EmployeeRepository}
 * will be used to access and transfer data by {@link net.atos.testeatos.controller
 * .EmployeeRestController EmployeeRestController}'s requests.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */
@Service
public class EmployeeRestServiceImpl implements EmployeeRestService {

  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public Employee update(Employee employee) {
    if(employee.getId() == 0) {
    	return new Employee();
    }
    return employeeRepository.save(employee);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> saveAll(List<Employee> employees) {
    return employeeRepository.saveAll(employees);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Employee findById(long id) {
    return employeeRepository.getOne(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> findDistinctBySkillsIn(Set<String> skills) {
    return employeeRepository.findDistinctBySkillsIn(skills);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete(Employee employee) {
    employeeRepository.delete(employee);
  }
  
}
