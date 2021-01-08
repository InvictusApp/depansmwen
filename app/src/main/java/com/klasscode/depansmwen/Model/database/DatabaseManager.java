package com.klasscode.depansmwen.Model.database;

import java.util.List;

public interface DatabaseManager<T> {

    boolean insert(T t);
    boolean update(T t);
    T get(int id);
    List<T> getAll();
    boolean delete(T t);

}
