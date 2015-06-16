package tree;
/**
 * ��������� ������ ������ � ��������� B tree � 
 * ����������� ��������������� ����� �������, �������� � ������.
 * @param <T>
 */
public interface IBinaryTree<T> {
    /**
     * ��������� ������� � ���������
     * @param e
     * @return
     */
	boolean add(Comparable<T> e);

    /**
     * ������� ������� �� ���������
     * @param o
     * @return
     */
    boolean remove(Comparable<T> o);
    /**
     * ���������� true, ���� ������� ���������� � ���������
     * @param o
     * @return
     */
    boolean contains(Comparable<T> o);

	
}