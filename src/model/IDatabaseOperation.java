package model;

import controller.ModelListener;

interface IDatabaseOperation {
    /**
     * Insert current object into database.
     * @return Whether the insert operation is successful.
     */
    boolean insert();

    /**
     * Delete current object.
     * @return Whether the delete operation is successful.
     */
    boolean delete();

    /**
     * Add listener of view to model and notify view when model change.
     * @return Whether the update operation is successful.
     */
    boolean update();

    /**
     * Add listener of view to model and notify view when model change.
     * @param listener The view which need to listen.
     */
    void addListener(ModelListener listener);

    /**
     * Notify view to update information when model change.
     */
    void notifyListener();

    /**
     * Parse string to avoid syntax error in database.
     * @param str Origin string which need to parse.
     * @return Parsed string.
     */
    default String stringParser(String str) {
        return str.replaceAll("'", "''");
    }
}