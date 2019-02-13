/* IStorage.java
 * 2019-02-11
 */

/**
 * This interface represents storage. The storage can store any type of object as long as it implements IStorable.
 *
 * @param <T> type of vehicle
 * @see IStorable
 */
public interface IStorage<T extends IStorable> {
    /**
     * Load an IStorable to the storage.
     *
     * @param <T> type of vehicle
     * @see IStorable
     */
    void loadStorable(T storable);

    /**
     * Unloads the IStorable and returns it.
     *
     * @return T type of vehicle
     * @see IStorable
     */
    T unloadStorable();

}
