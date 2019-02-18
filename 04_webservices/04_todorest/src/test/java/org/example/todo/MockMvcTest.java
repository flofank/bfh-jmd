package org.example.todo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MockMvcTest {

	@Autowired
	private MockMvc mockMvc;

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  int port;

	@Test
	public void exampleTest() throws Exception {
	  Todo t = new Todo();
	  t.setTitle("Testing");
	  t.setDueDate(new Date());
    mockMvc.perform(post("/todos").content(asJsonString(t)).contentType(MediaType.APPLICATION_JSON));

    mockMvc.perform(get("/todos")).andExpect(status().isOk())
      .andExpect(content().string(containsString("Testing")));
	}


  @Test
  public void exampleTest2() {
    Todo t = restTemplate.getForObject("/todos/1", Todo.class);
    assertThat(t.getTitle()).isEqualTo("Testing");
  }


  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Before
  public void init() {
    RestAssured.port = port;
    RestAssured.basePath = "/todos";
  }

  @Test
  public void exampleTest3() {
    given().when().get("/1").then().body(containsString("Testing"));
  }
}
