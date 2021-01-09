package com.klasscode.depansmwen.Model;

import java.util.List;

public interface DatabaseManager<T> {

    static final int DATABASE_VERSION= 1;
    static final String DATABASE_NAME= "depansMwen.db";

    boolean insert(T t);
    boolean update(T t);
    T get(int id);
    List<T> getAll( int id );
    boolean delete(T t);


}
