package controller;

import java.util.ArrayList;

interface DatabaseController<T> {
    public ArrayList<T> getAll() throws Exception;
    public boolean insert(T t);
}
