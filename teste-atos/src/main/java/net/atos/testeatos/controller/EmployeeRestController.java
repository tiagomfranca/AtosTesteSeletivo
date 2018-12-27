package net.atos.testeatos.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.atos.testeatos.model.Employee;
import net.atos.testeatos.services.EmployeeRestService;

/**
 * Handles HTTP requests to the application's REST endpoints. Allows the user to
 * perform standard CRUD operations on the Employee's data repository using
 * JSON.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

  @Autowired
  private EmployeeRestService employeeRestService;

  /**
   * Returns a {@link org.springframework.http.ResponseEntity ResponseEntity}
   * with a JSON object of a list of employees in its body. In case the case of
   * an empty data repository, the ResponseEntity returned will have the Not
   * Found header and an empty body.
   * 
   * @return ResponseEntity with the servers response to the request. Can be a
   * JSON object array of the Employees in the repository or have no content
   * aside from the header
   * 
   * @see org.springframework.http.ReponseEntity ResponseEntity
   */
  @GetMapping("/list")
  public ResponseEntity<List<Employee>> findAllEmployees() {
    List<Employee> employeeList = employeeRestService.findAll();

    if (employeeList.size() == 0) {
      return ResponseEntity.notFound().build();
	}
    
    return ResponseEntity.ok(employeeList);
  }

  /**
   * Saves the list of employees in the repository if it's valid. Returns a 
   * {@link org.springframework.http.ResponseEntity ResponseEntity} with the
   * appropriate response from the server.
   * 
   * @param employees List of employees to be saved in the data repository
   * @return ResponseEntity with the servers response to the request. Can be a
   * List of the saved Employees or have no content aside from the header
   */
  @PostMapping("/add")
  public ResponseEntity<List<Employee>> addListOfEmployees(
      @RequestBody List<@Valid Employee> employees) {

    if (employees.size() == 0) {
      return ResponseEntity.noContent().build();
    }
    try {
      employeeRestService.saveAll(employees);
    } catch (ConstraintViolationException e) {
      return ResponseEntity.badRequest().build();
    }
    
    return ResponseEntity.ok(employees);
  }

  /**
   * Returns a {@link org.springframework.http.ResponseEntity ResponseEntity}
   * with a JSON object of the employee entity with the given identifier. The
   * ResponseEntity's body will be empty if no employee with that identifier
   * could be found. 
   * 
   * @param id The identifier of the employee entity.
   * @return ResponseEntity with the servers response to the request. Can be a
   * single Employee entity or have no content and a Not Found header
   */
  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
	  Employee employee = new Employee();
    try {
      employee = employeeRestService.findById(id);
      employee.getName(); //throws EntityNotFoundException if employee wasn't found
      
      return ResponseEntity.ok(employee);
      
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }    
  }

  /**
   * Returns a {@link org.springframework.http.ResponseEntity ResponseEntity}
   * with a JSON object of a list of employees in its body. The employees
   * returned are only the ones that have any of the skills from the parameter.
   * In case the case of no employees being found, the ResponseEntity returned
   * will have the Not Found header and an empty body.
   * 
   * @param skills Set of skills that will be used to filter the employee
   * entities by
   * @return ResponseEntity with the servers response to the request. Can be a
   * list of employees filtered by skill or have no content aside from the header
   */
  @GetMapping("/filter/{skills}")
  public ResponseEntity<List<Employee>> searchBySkills(@PathVariable Set<String> skills) {
    List<Employee> employees = employeeRestService.findDistinctBySkillsIn(skills);
    
    if (employees.size() == 0) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(employees);
  }
  
  /**
   * Returns a {@link org.springframework.http.ResponseEntity ResponseEntity}
   * of a JSON object that has all the skills employees have in the repository.
   * 
   * @return ResponseEntity with a list of all the employee skills from the 
   * repository in JSON format
   */
  @GetMapping("/skills")
  public Set<String> getAllSkills() {
    Set<String> skills = new HashSet<String>();
    List<Employee> employeeList = employeeRestService.findAll();

    employeeList.stream()
        .forEach(employee -> employee.getSkills().stream()
          .forEach(skills::add)); //Collects employees' skills into skills Set

    return skills;
  }
  
  /**
   * Updates the Employee instance with the given identifier with the new
   * informations from the parameter. Returns a {@link org.springframework.http
   * .ResponseEntity ResponseEntity} with either the updated employee in its
   * body or an empty response with the Not Found header.
   * 
   * @param id Identifier for the employee to be updated
   * @param employee Employee instance with updated data
   * @return ResponseEntity with the servers response to the request. Can be a
   * single Employee entity or have no content and a Not Found header
   */
  @PutMapping("/edit/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable long id,
      @Valid @RequestBody Employee employee) {
    Employee existingEmployee = new Employee();
	try {
	  existingEmployee = employeeRestService.findById(id);
	  existingEmployee.getName(); //throws EntityNotFoundException if employee wasn't found
      BeanUtils.copyProperties(employee, existingEmployee, "id");
      existingEmployee = employeeRestService.update(existingEmployee);

      return ResponseEntity.ok(existingEmployee);
	} catch (EntityNotFoundException e) {
	  return ResponseEntity.notFound().build();
	} catch (ConstraintViolationException e) {
	  return ResponseEntity.badRequest().build();
	}   
  }

  /**
   * Deletes an Employee entity from the repository. Returns an empty {@link org
   * .springframework.http.ResponseEntity ResponseEntity} on success with a No
   * Content header or a Not Found header if the employee was not found.
   * 
   * @param id Identifier of the employee to be deleted
   * @return A ResponseEntity with no content
   */
  @DeleteMapping("/remove/{id}")
  public ResponseEntity<Employee> removeEmployee(@PathVariable long id) {
    Employee employee = employeeRestService.findById(id);

    if (employee == null) {
      return ResponseEntity.notFound().build();
    }
    employeeRestService.delete(employee);

    return ResponseEntity.noContent().build();
  }
  
}
