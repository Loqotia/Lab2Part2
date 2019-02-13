/* IStorable.java
 * 2019-02-11
 */

/**
 * This interface represents an Object that can be stored in IStorage.
 */
public interface IStorable {
    /**
     * Set a storage for the IStorable.
     *
     * @param storage the storage that the object implementing this interface will be stored in.
     * @see IStorage
     */
    void setStorage(IStorage storage);

    /**
     * Unload the IStorable from the storage it is currently in.
     */
    void unloadFromStorage();

    /**
     * Returns whether the IStorable is stored or not.
     */
    boolean isStored();
}
