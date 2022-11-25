package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.model.Email;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String emailservice()
    {
        return "welcome to my email service";
    }

//	Email Controller to Send Email

    //@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody Email emailModel) {
        emailService.sendEmail(emailModel.getMessage(), emailModel.getSubject(), emailModel.getRecipient());
        System.out.println(emailModel);
        return ResponseEntity.ok("Done...");
    }

}
