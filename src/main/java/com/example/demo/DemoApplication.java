package com.example.demo;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

            UserDAO udao = new UserDAO();
            /*if(udao.insert(new User(123,"ARWA","bensalem","admin","admin"))!=null){
                System.out.println("ok");

            }else{
                System.out.println("nok");
            }*/


        udao.getAll().forEach(u -> {
            System.out.println(u);
        });

        User u = udao.findByID(3);
        if (u!=null) {
            System.out.println(udao.findByID(3));
            User oma= new User(123,"OMOU","OMOU","OMOU","OMOU");
            udao.update(oma,3);
        }else {
            System.out.println("not found");
        }

    }

}
