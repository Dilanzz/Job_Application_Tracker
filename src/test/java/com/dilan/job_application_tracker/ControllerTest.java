package com.dilan.job_application_tracker;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.servlet.ServletWebServerFactory;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Service jobApplicationService;

    @Test
    public void createThrows201() throws Exception {
        String json = """
                {
                    "company": "Apple",
                    "role": "Software Engineer"
                }
                """;

        mockMvc.perform(post("/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void invalidThrows400() throws Exception {
        String json = """
                {
                    "company": "",
                    "role": "Software Engineer"
                }
                """;

        mockMvc.perform(post("/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void notFoundThrows404() throws Exception {
        when(jobApplicationService.getJobApplicationById(999)).thenThrow(new JobApplicationNotFoundException(999));

        mockMvc.perform(get("/applications/999")).andExpect(status().isNotFound());
    }

    @Test
    void noContentReturns204() throws Exception {

        mockMvc.perform(delete("/applications/1")).andExpect(status().isNoContent());
    }
}
