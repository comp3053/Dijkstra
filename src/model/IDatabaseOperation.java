package model;

import controller.ModelListener;

import java.util.ArrayList;

interface IDatabaseOperation<T> {
    boolean insert();

    boolean delete();

    void addListener(ModelListener listener);

    void notifyListener();
}
