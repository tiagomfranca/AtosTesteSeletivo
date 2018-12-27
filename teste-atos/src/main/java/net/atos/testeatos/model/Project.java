package net.atos.testeatos.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Project entity model used by the application.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 * @see package javax.persistence.Entity Entity;
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {
	
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_sequence")
  @SequenceGenerator(name="project_sequence", sequenceName="project_sequence")
  @JsonIgnore
  private long id;

  @NotNull
  @Size(min=1)
  private String name;
  
  @NotNull
  @Size(min=1)
  private String customer;

  @Column(name="value_of_project")
  @NotNull
  @Size(min=1)
  private String valueOfProject;

  @Column(name="begin_date")
  @NotNull
  private ZonedDateTime dtBegin;
	
  @Column(name="end_date")
  private ZonedDateTime dtEnd;

  public Project() {
    super();
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
  
  public String getCustomer() {
    return customer;
  }
  
  public void setCustomer(String customer) {
    this.customer = customer;
  }
  
  public String getValueOfProject() {
    return valueOfProject;
  }
  
  public void setValueOfProject(String valueOfProject) {
    this.valueOfProject = valueOfProject;
  }
  
  public ZonedDateTime getDtBegin() {
    return dtBegin;
  }
  
  public void setDtBegin(String dtBeginStr) {
    ZonedDateTime dtBeginZdt = ZonedDateTime.parse(dtBeginStr);
    this.dtBegin = dtBeginZdt;
  }
  
  public ZonedDateTime getDtEnd() {
    return dtEnd;
  }
  
  public void setDtEnd(String dtEndStr) {
    ZonedDateTime dtEndZdt = ZonedDateTime.parse(dtEndStr);
    this.dtEnd = dtEndZdt;
  }
	
}
