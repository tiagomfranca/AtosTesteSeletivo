package net.atos.testeatos.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Employee entity model used by the application.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 * @see package javax.persistence.Entity Entity;
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
	
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_sequence")
  @SequenceGenerator(name="employee_sequence", sequenceName="employee_sequence")
  @JsonIgnore
  private long id;

  @NotNull
  @Size(min=1)
  private String name;

  @NotNull
  private String role;
  
  @NotNull
  @Size(min=1)
  private String salary;

  @NotNull
  @Size(min=1)
  private String manager;

  @NotNull
  @Size(min=1)
  private String gcm;

  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
  private List<Project> projects;

  @ElementCollection
  private Set<String> skills;

  @ElementCollection
  private Set<String> certification;

  public Employee() {
    super();
    this.skills = new HashSet<String>();
    this.projects = new ArrayList<Project>();
    this.certification = new HashSet<String>();
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getRole() {
    return role;
  }
  
  public void setRole(String role) {
    this.role = role;
  }
  
  public String getSalary() {
    return salary;
  }
  
  public void setSalary(String salary) {
    this.salary = salary;
  }
  
  public String getManager() {
    return manager;
  }
  
  public void setManager(String manager) {
    this.manager = manager;
  }
  
  public String getGcm() {
    return gcm;
  }
  
  public void setGcm(String gcm) {
    this.gcm = gcm;
  }
  
  public List<Project> getProjects() {
    return projects;
  }
  
  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
  
  public Set<String> getSkills() {
    return skills;
  }
  
  public void setSkills(Set<String> skills) {
    this.skills = skills;
  }
  
  public Set<String> getCertification() {
    return certification;
  }
  
  public void setCertification(Set<String> certification) {
    this.certification = certification;
  }
  
}