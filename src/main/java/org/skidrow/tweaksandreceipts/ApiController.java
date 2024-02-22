package org.skidrow.tweaksandreceipts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @PostMapping("/initiate")
    public ResponseEntity<String> initiateProcess(@RequestBody(required = false) String body) {
        if ("initiate".equals(body)) {
            return ResponseEntity.ok("started");
        } else {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }
}
