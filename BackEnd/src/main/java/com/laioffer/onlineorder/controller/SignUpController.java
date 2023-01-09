package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Customer;
import com.laioffer.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import com.laioffer.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SignUpController {

    private CustomerService customerService;
    @Autowired
    public SignUpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)

    public void signUp(@RequestBody Customer customer){
        //exception
        //response.setStatus(500);
        //successful
        //response.setStatus(201);
        //只有添加了RequestMapping的annoation的方法才能被search到
        customerService.signUp(customer);
        //customerService.signUp(customer);



    }
}
