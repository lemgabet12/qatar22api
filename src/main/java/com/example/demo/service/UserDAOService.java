package com.example.demo.service;


import com.example.demo.dao.DAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
 import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional  // Each of them is a db sql transaction
public class UserDAOService implements DAO<User, Long> {

    UserDAO userDAO = new UserDAO();

    public User insert(User a) {
        return  ( userDAO.insert(a));
    }


    public List<User> getAll() {
        return userDAO.getAll() ;
    }


    public User findByID(int id) {
        return userDAO.findByID(id) ;

    }

    public boolean update(User a, int id) {
        return userDAO.update(a,id) ;

    }

    public boolean delete(User a) {
        return userDAO.delete(a) ;

    }



}
