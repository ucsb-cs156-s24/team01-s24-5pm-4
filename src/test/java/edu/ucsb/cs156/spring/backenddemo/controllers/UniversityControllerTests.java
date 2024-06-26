package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UniversityController.class)
public class UniversityControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UniversityQueryService mockUniversityQueryService;

    @Test
    public void test_getUniversities() throws Exception {
        String fakeJsonResult="{ \"fake\" : \"result\" }";
        String name = "University of California";
        when(mockUniversityQueryService.getJSON(name)).thenReturn(fakeJsonResult);

        String url = String.format("/api/university/get?name=%s", name);

        MvcResult response = mockMvc
                .perform(get(url).contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        String responseString = response.getResponse().getContentAsString();

        assertEquals(fakeJsonResult, responseString);
    }
}
