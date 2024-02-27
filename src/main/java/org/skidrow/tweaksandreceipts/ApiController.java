package org.skidrow.tweaksandreceipts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    String defaultSuccesResponse = "started";
    String defaultFailureMessage = "Invalid request";
    String provideFailState = "provide fail state message.";

    @PostMapping("/initiate")
    public ResponseEntity<String> initiateProcess(@RequestBody(required = false) String body,
                                                  @RequestHeader(value = "X-From", required = true) String fromPhoneNumber,
                                                  @RequestHeader(value = "X-To", required = true) String toPhoneNumber,
                                                  @RequestHeader(value = "X-Policy-Variable", required = true) String policyVariable) {
        if (!isValidPolicyVariable(policyVariable)) {
            return ResponseEntity.badRequest().body("The x-account-id has not been provided. Message rejected.");
        } else if (body != null && body.equals(provideFailState)) {
            return ResponseEntity.badRequest().body(defaultFailureMessage);
        } else {
            return ResponseEntity.ok(defaultSuccesResponse);
        }
    }

    private boolean isValidPolicyVariable(String policyVariable) {
        if (policyVariable != null && !policyVariable.isEmpty()) {
            // Check if the header contains "x-account-id"
            String[] variables = policyVariable.split(",");
            for (String variable : variables) {
                String[] keyValue = variable.split("=");
                if (keyValue.length == 2 && keyValue[0].trim().equals("x-account-id")) {
                    return true;
                }
            }
        }
        return false;
    }
}
