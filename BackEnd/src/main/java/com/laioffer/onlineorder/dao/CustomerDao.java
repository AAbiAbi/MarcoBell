package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.laioffer.onlineorder.entity.Authorities;
//import com.laioffer.onlineorder.entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;


@Repository
public class CustomerDao {
//通过hibernate提供的api对数据库进行操作，增删改查

    @Autowired
    //通过spring去做injection
    private SessionFactory sessionFactory;

    public void signUp(Customer customer){
    //向数据库里添加record
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");//
        authorities.setEmail(customer.getEmail());

        Session session = null;//通过sessionFactory接口里
        try {
            //通过session object对数据库进行操作，交互
            session = sessionFactory.openSession();//openSession api得到session object，若session为null，opensession没有执行成功
            session.beginTransaction();//对三张表提供一致性，fail时rollback
            session.save(authorities);//保存用户的author
            session.save(customer);//关于用户注册时的数据要保存，即连性质导致cart也save
            session.getTransaction().commit();//执行

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();//若session不为空，即commit出现exception，rollback
        } finally {
            if (session != null) {
                session.close();//释放connection，解除占用
            }
        }


    }

    public Customer getCustomer(String email){//丢email查看顾客存不存在
        Customer customer = null;
        try (Session session = sessionFactory.openSession()) {
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;//return写在try catch里面，自动做close： session.close()因为Session implement autoclose
    }

}

