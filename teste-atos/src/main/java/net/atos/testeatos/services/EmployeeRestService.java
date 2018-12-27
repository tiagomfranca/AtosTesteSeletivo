package net.atos.testeatos.services;

import java.util.List;
import java.util.Set;

import net.atos.testeatos.model.Employee;

/**
 * Defines data management operations to the system's repository that can be
 * used by {@link net.atos.testeatos.controller.EmployeeRestController EmployeeRestController}
 * to manage the repository through REST endpoints.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */
public interface EmployeeRestService {
	
  /**
   * Updates the parametrized {@link net.atos.testeatos.model.Employee employee
   * } employee into the repository. Since even a single employee object posted
   * will be treated as an array when inserting into the repository, should all
   * be handled by {@link net.atos.testeatos.services.EmployeeRestService#saveAll 
   * saveAll}. Because of that, if <code>employee</code>'s ID is 0, the object
   * won't be persisted.
   * 
   * @param employee The employee object to be persisted
   * @return The Employee object persisted. Can be a new instance of Employee,
   * which happens when 
   */
  public Employee update(Employee employee);
  
  /**
   * Saves all the employee instances contained in the <code>employees</code>
   * list in the repository. The returned list is identical to the parameter.
   * 
   * @param employees List of Employees to be saved
   * @return List of Employees that was saved.
   */
  public List<Employee> saveAll(List<Employee> employees);
  
  /**
   * Returns a list of all employees stored in the repository.
   * 
   * @return A list containing all the employees stored in the repository.
   */
  public List<Employee> findAll();
  
  /**
   * Returns an instance of the employee entity with the given identifier.
   *  
   * @param id Unique key of the employee
   * @return An instance of the employee with the given identifier.
   */
  public Employee findById(long id);
  
  /**
   * Returns a list of all the employees stored in the repository that have any
   * of the skills contained in the <code>skills</code> {@link java.util.Set
   * set}.
   * 
   * @param skills Set containing the skills that will be used to filter the
   * employees by
   * @return A filtered list of employees
   */
  public List<Employee> findDistinctBySkillsIn(Set<String> skills);
  
  /**
   * Deletes the employee entity associated with this employee instance in the
   * repository.
   * 
   * @param employee Instance of the employee to be removed from the repository.
   */
  public void delete(Employee employee);
}
