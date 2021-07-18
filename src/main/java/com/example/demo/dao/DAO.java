package com.example.demo.dao;

import java.util.List;

public interface DAO <T,Int> {
    public T insert(T e);
    public List<T> getAll();
    public T findByID(int id);
    public boolean update(T e, int id);
    public boolean delete(T e);

/*
    public List<T> findByMarque(String marque);*/

}
