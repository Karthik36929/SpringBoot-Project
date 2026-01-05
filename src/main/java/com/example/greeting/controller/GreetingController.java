
package com.example.greeting.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class GreetingController {

  @GetMapping("/greet")
  public Map<String,Object> greet() {
    Map<String, Object> response = new HashMap<>(); response.put("message", "Hello, Welcome!"); response.put("timestamp", java.time.LocalDateTime.now().toString()); return response;
  }

  @GetMapping("/greet/{name}")
  public Map<String,Object> greetByName(@PathVariable String name) {
    Map<String, Object> response = new HashMap<>(); response.put("message", "Hello, " + name + "!"); response.put("timestamp", java.time.LocalDateTime.now().toString()); return response;
  }

  @GetMapping("/greet/custom")
  public Map<String,Object> customGreet(@RequestParam String name, @RequestParam String language) {
    Map<String, Object> response = new HashMap<>(); String greeting = ""; switch(language.toLowerCase()) { case "spanish": greeting = "Hola, " + name + "!"; break; case "french": greeting = "Bonjour, " + name + "!"; break; case "german": greeting = "Guten Tag, " + name + "!"; break; case "italian": greeting = "Ciao, " + name + "!"; break; default: greeting = "Hello, " + name + "!"; break; } response.put("message", greeting); response.put("language", language); response.put("timestamp", java.time.LocalDateTime.now().toString()); return response;
  }

  @PostMapping("/greet")
  public Map<String,Object> createGreeting(@RequestBody Map<String,String> request) {
    Map<String, Object> response = new HashMap<>(); String name = request.getOrDefault("name", "Guest"); String message = request.getOrDefault("message", "Hello"); String fullGreeting = message + ", " + name + "!"; response.put("greeting", fullGreeting); response.put("status", "success"); response.put("timestamp", java.time.LocalDateTime.now().toString()); return response;
  }
}
