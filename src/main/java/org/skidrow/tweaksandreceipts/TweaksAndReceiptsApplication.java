package org.skidrow.tweaksandreceipts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TweaksAndReceiptsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweaksAndReceiptsApplication.class, args);
    }

}

