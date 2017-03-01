package com.rkaneko.example.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceOneController {

    @RequestMapping(path = "/api/service-one", method = RequestMethod.GET)
    public ServiceOneOutputForm run(@Validated @RequestBody ServiceOneInputForm inputForm) {
        if (inputForm.getId() % 2 == 0) {
            return new ServiceOneOutputForm("Bob", true);
        }
        return new ServiceOneOutputForm("Brian", false);
    }
}
