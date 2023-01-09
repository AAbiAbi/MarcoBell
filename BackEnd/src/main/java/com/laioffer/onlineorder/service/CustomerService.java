package com.laioffer.onlineorder.service;

import com.laioffer.onlineorder.dao.CustomerDao;
import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private CustomerDao customerDao;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public CustomerService(CustomerDao customerDao,PasswordEncoder passwordEncoder){
        //return new
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;

    }

    public void signUp(Customer customer){
        Cart cart = new Cart();//要做即连操作，所以要先把cart创建出来，再set一下
        customer.setCart(cart);

        customer.setEnabled(true);//默认是false，framework在登录操作会去✊是否是true
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));//password保存在db里是加密的方法，是一个one way的方法
        //所以sign in的时候也要如此
        customerDao.signUp(customer);

        //customerDao.signUp(customer);
    }//

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }
}



