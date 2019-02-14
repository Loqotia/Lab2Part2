package com.group14.Storage;

/*
 * 2019-02-11
 */

/**
 * This interface represents an Object that can be stored in com.group14.Storage.IStorage.
 */
public interface IStorable {
    /**
     * Set a storage for the com.group14.Storage.IStorable.
     *
     * @param storage the storage that the object implementing this interface will be stored in.
     * @see IStorage
     */
    void setStorage(IStorage storage);

    /**
     * Unload the com.group14.Storage.IStorable from the storage it is currently in.
     */
    void unloadFromStorage();

    /**
     * Returns whether the com.group14.Storage.IStorable is stored or not.
     */
    boolean isStored();
}
