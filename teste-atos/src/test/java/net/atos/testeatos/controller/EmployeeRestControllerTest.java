package net.atos.testeatos.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.atos.testeatos.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRestControllerTest {

  private MockMvc mockMvc;

  @Mock
  private Employee employee;
  
  @Autowired
  private WebApplicationContext wbc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(wbc).build();
  }

  @Test
  public void testFindAllEmployees() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/employees/list"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value("2000,00"));

  }
  
  @Test
  public void testAddListOfEmployees() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
	employee = new Employee();
	employee.setName("Test Employee");
	employee.setRole("Test Role");
	employee.setSalary("9999,99");
	employee.setManager("Test Manager");
	employee.setGcm("00");

    mockMvc.perform(
        MockMvcRequestBuilders.post("/employees/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(employee)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testGetEmployeeById() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/employees/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType("application/json;charset=UTF-8"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value("2000,00"));
  }

  @Test
  public void testSearchBySkills() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/employees/filter/Scrum"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType("application/json;charset=UTF-8"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value("99999,99"));
  }

  @Test
  public void testGetAllSkills() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/employees/skills"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType("application/json;charset=UTF-8"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("rest"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("DevOps"));
  }
  
  @Test
  public void testUpdateEmployee() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
	employee = new Employee();
	employee.setName("New Employee");
	employee.setRole("Test Role");
	employee.setSalary("9999,99");
	employee.setManager("Test Manager");
	employee.setGcm("00");
	
	mockMvc.perform(
	    MockMvcRequestBuilders.put("/employees/edit/3")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(mapper.writeValueAsString(employee)))
	    .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testRemoveEmployee() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.delete("/employees/remove/4"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}
