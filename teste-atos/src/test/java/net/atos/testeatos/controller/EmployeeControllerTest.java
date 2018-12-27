package net.atos.testeatos.controller;

import org.hamcrest.Matchers;
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

import net.atos.testeatos.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

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
  public void testShowEmployeeOverview() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("list"))
        .andExpect(MockMvcResultMatchers.model().attribute("employees", Matchers.hasSize(4)));
  }
  
  @Test
  public void testShowFilteredEmployeeOverview() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/filter")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("activeFilter", "Scrum").param("skills", ""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("list"))
        .andExpect(MockMvcResultMatchers.model().attribute("employees", Matchers.hasSize(1)));
  }

}
