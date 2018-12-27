package net.atos.testeatos.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.atos.testeatos.model.Employee;
import net.atos.testeatos.services.EmployeeService;

/**
 * Handles user requests related to the employee overview page.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */

@Controller
@RequestMapping({ "/", "" })
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /**
   * Adds a list of all the employees and all the skills stored in the database
   * to the list.hmtl template model.
   * 
   * @param model model that will be used by the list template. Instatiated by
   * Spring's container
   * @return redirects the user to the list template
   */
  @RequestMapping("")
  public String showEmployeeOverview(Model model) {
    List<Employee> allEmployees = employeeService.findAll();
    Set<String> skills = new HashSet<String>();

    allEmployees.stream()
        .forEach(employee -> employee.getSkills().stream()
            .forEach(skills::add));
    
    model.addAttribute("employees", allEmployees);
    model.addAttribute("skills", skills);

    return "list";
  }

  /**
   * Adds a list of all the employees that have any of the skills requested by
   * the user to the list.html template model. Also returns the list of skills
   * used to filter the employees by and the complete list of skills to the view
   * template.
   *  
   * @param activeFilter set of Strings provided by the user to filter the
   * employees by
   * @param skills set of Strings containing all the skills returned from the
   * database 
   * @param model model that will be used by the list template. Instatiated by
   * Spring's container
   * @return a filtered list of employees
   */
  @RequestMapping(value = "/filter", method = RequestMethod.POST)
  public String showFilteredEmployeeOverview(
      @RequestParam Set<String> activeFilter,
      @RequestParam Set<String> skills, Model model) {
	  
    List<Employee> allEmployees = 
        employeeService.findBySkill(activeFilter);
    Set<String> allSkills = new HashSet<String>();
    
    skills.stream().forEach(allSkills::add);
    
    String activeFilterReturn = activeFilter.stream()
        .map(s -> s + ", ") //concatenates ", " to the skill value
        .collect(Collectors.joining());
    int lenghtWithoutComma =  activeFilterReturn.length() - 2;
    
    activeFilterReturn = activeFilterReturn.substring(0, lenghtWithoutComma);
    
    model.addAttribute("employees", allEmployees);
    model.addAttribute("skills", allSkills);
    model.addAttribute("activeFilter", activeFilterReturn);

    return "list";
  }
}
