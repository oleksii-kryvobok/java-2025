package com.calculator.controller;

import com.calculator.dto.FormulaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SavedFormulaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String jwtToken = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJHUWc3YjY4N2lMMFlHM0stdXhmQkdhUzljUGZHRGpBLXJ0VmE4VWVJbW8wIn0.eyJleHAiOjE3NDg5MDQwNzgsImlhdCI6MTc0ODkwMzc3OCwiYXV0aF90aW1lIjoxNzQ4OTAzNjU3LCJqdGkiOiJvbnJ0YWM6ODIwYTgyMGYtNWVkZC00Y2VmLWI4MWItZDk2Mzc0ZTE2OGY5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9zbWFydGNhbGMiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiYTY5YThmM2ItZWM1Zi00MTgyLWI0NGUtOWNhODZmZTY5YjNjIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoic21hcnRjYWxjLWNsaWVudCIsInNpZCI6IjMwMjQ3YWU3LTNmZjYtNDI0Yi1hNTllLTNjZjI4ZmJhZjJjMCIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgxIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXNtYXJ0Y2FsYyIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgcm9sZXMgcHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IlRlc3QgVGVzdCIsInByZWZlcnJlZF91c2VybmFtZSI6InRlc3R1c2VyIiwiZ2l2ZW5fbmFtZSI6IlRlc3QiLCJmYW1pbHlfbmFtZSI6IlRlc3QiLCJlbWFpbCI6InRlc3RAdGVzdC5jb20ifQ.JK23MTn6k1pJypD6SevZByXLFWQ7lc7HTjfqIz8Q22s00-Ld6qmeBfPnyrmshZJaihEBL0r9L-ZjbecIbAD6vFoOccyYTmHXLBXFJcE7rlb1FSmjLCjcb097PlO6TyD922jmWMocf_Fran7Sx9WyFvBqh27Zt40HRqVOtxixplAgTDPXyPkjo4czrbumloJOs5r8RXjNSuHqqiL-9_YbD-OWjxlCr2AiyJoJxr1WZFUv4EiqT3VYe7TFgm-vmYIZC5VVQnDDHzvKqVX-i2CTg5dyPj2UdwZE9aOYwUp0dXJlP67zH0kVunLOD7ZGObhyksHjeoaTLaxSPQGGjNFnNg"; // вставити справжній токен

    @Test
    public void testSaveFormula() throws Exception {
        FormulaDTO dto = new FormulaDTO();
        dto.setName("myformula");
        dto.setExpression("2+3");

        mockMvc.perform(post("/api/formulas")
                        .header("Authorization", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("myformula"));
    }
}
