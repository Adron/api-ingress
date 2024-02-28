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

    String defaultSuccesResponse = "started";
    String defaultFailureMessage = "Invalid request";
    String stateFailMessage = "provide fail state message.";
    String phoneNumber = "12342344321"; // Example phone number
    String fromPhoneNumber = "12342346621"; // Example phone number
    String validPolicyVariable = "x-account-id=12345678";

    String urlTemplate = "/ingest";

    @Test
    void testProvideFailState() throws Exception {

        mockMvc.perform(post(urlTemplate)
                        .header("X-To", phoneNumber)
                        .header("X-From", fromPhoneNumber)
                        .header("X-Policy-Variable", validPolicyVariable)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(stateFailMessage))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(defaultFailureMessage));
    }

    @Test
    void testInitiateEndpointWithValueBody() throws Exception {
        mockMvc.perform(post(urlTemplate)
                        .header("X-To", phoneNumber)
                        .header("X-From", fromPhoneNumber)
                        .header("X-Policy-Variable", validPolicyVariable)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("whatever"))
                .andExpect(status().isOk())
                .andExpect(content().string(defaultSuccesResponse));
    }

    @Test
    void testInitiateEndpointWithNoBody() throws Exception {
        mockMvc.perform(post(urlTemplate)
                        .header("X-To", phoneNumber)
                        .header("X-From", fromPhoneNumber)
                        .header("X-Policy-Variable", validPolicyVariable)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(defaultSuccesResponse));
    }

    @Test
    void testInitiateEndpointWithNoHeader() throws Exception {
        mockMvc.perform(post(urlTemplate)
                        .header("X-To", phoneNumber)
                        .header("X-From", fromPhoneNumber)
                        .header("X-Policy-Variable", validPolicyVariable)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("initiate"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(defaultSuccesResponse)));
    }

    @Test
    void testInitiateEndpointWithHeader() throws Exception {
        String phoneNumber = "12342344321"; // Example phone number

        mockMvc.perform(post(urlTemplate)
                        .header("X-To", phoneNumber)
                        .header("X-From", fromPhoneNumber)
                        .header("X-Policy-Variable", validPolicyVariable)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("initiate"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(defaultSuccesResponse)));
    }

    @Test
    void testInitiateEndpointWithHeaderAndBody() throws Exception {

        mockMvc.perform(post(urlTemplate)
                        .header("X-To", phoneNumber)
                        .header("X-From", fromPhoneNumber)
                        .header("X-Policy-Variable", validPolicyVariable)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("this is not a spam message"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(defaultSuccesResponse)));
    }
}