package net.atos.testeatos.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.testeatos.model.Employee;
import net.atos.testeatos.repository.EmployeeRepository;

/**
 * Service class that implements the {@link net.atos.testeatos.services
 * .EmployeeService EmployeeService} interface. Defines what methods from {@link
 * net.atos.testeatos.repository.employeeRepository EmployeeRepository} will be
 * used to access the repository by {@link net.atos.testeatos.controller.EmployeeController
 *  EmployeeController}'s requests.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */
@Service
public class EmployerServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

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
  public List<Employee> findBySkill(Set<String> skills) {
    return employeeRepository.findDistinctBySkillsIn(skills);
  }
  
}
