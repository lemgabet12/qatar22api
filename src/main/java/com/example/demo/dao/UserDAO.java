package com.example.demo.dao;


import com.example.demo.model.User;
import com.example.demo.service.UserDAOService;
import com.example.demo.technique.Crude;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO implements DAO<User, Long> {

    private Crude crude = new Crude();
    @Override
    public User insert(User a) {
        String sql =
                "INSERT INTO user(FirstName,LastName,login ,pwd ) VALUES ('"
                        + a.getFirstName() + "','" +a.getLastName() + "','" +
                        a.getLogin() + "','" + a.getPwd() + "')";
        System.out.println(sql);
        if( crude.exeCreate(sql))
            return  a;
        else
            return null;
    }


    @Override
    public List<User> getAll() {
        try {
            String sql = "SELECT * FROM user";
            ResultSet rs = crude.exeRead(sql);
            List<User> liste = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setFirstName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setPwd(rs.getString(5));

                liste.add(u);
            }
            return liste;
        } catch (SQLException ex) {
            // Logger.getLogger(UserDAOService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public User findByID(int id) {
        try {
            String sql = "SELECT * FROM user WHERE id = "+id;
            ResultSet rs = crude.exeRead(sql);
            User u = new User();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setFirstName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setPwd(rs.getString(5));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean update(User a, int id) {
        String sql =
                "UPDATE user SET FirstName='" + a.getFirstName() + "', LastName='"
                        + a.getLastName() + "' , login= '" + a.getLogin() + "', pwd ='"+a.getPwd() +"' WHERE id=" + id;
        System.out.print("sql"+ sql);
        return crude.exeUpdate(sql);
    }

    @Override
    public boolean delete(User a) {
        String sql = "DELETE FROM user WHERE id=" + a.getId();
        return crude.exeDelete(sql);
    }


/*
    @Override
    public boolean delete(User e) {
        return false;
    }



    @Override
    public List<User> findByMarque(String marque) {
        return null;
    }*/

}
