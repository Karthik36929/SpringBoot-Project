
package com.example.greeting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private GreetingController controller;

  @Test
  void testGreet() throws Exception {
    // Test GET /greet
    mockMvc.perform(get("/api/greet")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").exists());
  }

  @Test
  void testGreetByName() throws Exception {
    // Test GET /greet/{name}
    mockMvc.perform(get("/api/greet/{name}", "TestValue")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hello, TestValue!"));
  }

  @Test
  void testCustomGreet() throws Exception {
    // Test GET /greet/custom with query parameters
    mockMvc.perform(get("/api/greet/custom")
            .param("name", "Alice")
            .param("language", "spanish")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hola, Alice!"))
            .andExpect(jsonPath("$.language").value("spanish"));
  }

  @Test
  void testCreateGreeting() throws Exception {
    // Test POST /greet
    String requestBody = "{\"name\":\"TestName\",\"language\":\"english\"}";
    mockMvc.perform(post("/api/greet")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").exists());
  }
}
