package controller;

import java.util.ArrayList;

interface DatabaseController<T> {
    ArrayList<T> getAll() throws Exception;
    boolean insert(T t);
}
