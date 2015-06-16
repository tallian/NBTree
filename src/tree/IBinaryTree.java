package tree;
/**
 * Коллекция хранит данные в структуре B tree и 
 * гарантирует логарифмическое время вставки, удаления и поиска.
 * @param <T>
 */
public interface IBinaryTree<T> {
    /**
     * Добавляет элемент в коллекцию
     * @param e
     * @return
     */
	boolean add(Comparable<T> e);

    /**
     * Удаляет элемент из коллекции
     * @param o
     * @return
     */
    boolean remove(Comparable<T> o);
    /**
     * Возвращает true, если элемент содержится в коллекции
     * @param o
     * @return
     */
    boolean contains(Comparable<T> o);

	
}