package net.atos.testeatos.services;

import java.util.List;
import java.util.Set;

import net.atos.testeatos.model.Employee;

/**
 * Defines data access operations to the system's repository that can be used by
 * {@link net.atos.testeatos.controller.EmployeeController EmployeeController}
 * to return {@link java.util.List lists} of {@link net.atos.testeatos.model.
 * Employee employees} used in the controller's methods.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */
public interface EmployeeService {
	
  /**
   * Returns a list that contains all the employees stored in the repository, 
   * ordered by their primary identification field.
   * 
   * @return A list of all the registered employees
   */
  public List<Employee> findAll();
  
  /**
   * Returns a list of all the employees stored in the repository that have any
   * of the skills contained in the <code>skills</code> {@link java.util.Set
   * set}. 
   * 
   * @param skills Set containing the skills that will be used to filter the
   * employees by
   * @return A filtered list of employees
   */
  public List<Employee> findBySkill(Set<String> skills);
  
}
