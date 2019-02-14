package com.group14.Storage;

/*
 * 2019-02-11
 */

/**
 * This interface represents storage. The storage can store any type of object as long as it implements com.group14.Storage.IStorable.
 *
 * @param <T> type of vehicle
 * @see IStorable
 */
public interface IStorage<T extends IStorable> {
    /**
     * Load an com.group14.Storage.IStorable to the storage.
     *
     * @param <T> type of vehicle
     * @see IStorable
     */
    void loadStorable(T storable);

    /**
     * Unloads the com.group14.Storage.IStorable and returns it.
     *
     * @return T type of vehicle
     * @see IStorable
     */
    T unloadStorable();

}
