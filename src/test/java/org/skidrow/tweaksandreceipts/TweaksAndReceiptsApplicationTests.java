package org.skidrow.tweaksandreceipts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TweaksAndReceiptsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testInitiateEndpoint() throws Exception {
        mockMvc.perform(post("/initiate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("initiate"))
                .andExpect(status().isOk())
                .andExpect(content().string("started"));
    }

    @Test
    void testfailEndpoint() throws Exception {
        mockMvc.perform(post("/initiate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("fail this test"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Invalid request"));
    }

    @Test
    void testInitiateEndpointWithEmptyBody() throws Exception {
        mockMvc.perform(post("/initiate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(""))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Invalid request"));
        // Note this capability necessitated the attribute addition like this
        // (@RequestBody(required = false) String body)
    }

    @Test
    void testInitiateEndpointWithHeader() throws Exception {
        String phoneNumber = "12342344321"; // Example phone number

        mockMvc.perform(post("/initiate")
                        .header("source-address", phoneNumber)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("initiate"))
                .andExpect(status().isOk())
                // Assuming the endpoint echoes back the phone number or uses it in its response
                .andExpect(content().string(containsString(phoneNumber)));
    }
}