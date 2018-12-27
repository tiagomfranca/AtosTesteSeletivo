package net.atos.testeatos.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import net.atos.testeatos.model.Employee;

/**
 * Implements {@link org.springframework.data.jpa.repository JpaRepository},
 * defining standard CRUD operations to the data repository.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
  
  /**
   * Returns a list containing instances of all the employee entities in the
   * repository that have any of the skills contained in the parameter set.
   * 
   * @param skills Set of skills 
   * @return A List with instances of the employee entities that have any of the
   * skills in the parameter.
   * @see {@link org.springframework.data.jpa.repository JpaRepository}
   */
  public List<Employee> findDistinctBySkillsIn(@Param("skills") Set<String> skills);

}
