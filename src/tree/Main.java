package tree;
public class Main {

	public static void main(String[] args) {
		NBTree<Integer> tree = new NBTree<Integer>();
		for(int i= 5; i< 29; i++) {
			tree.add(i);
		}
		System.out.println("��������� ������: ");
		tree.printTree(tree.header, 1);
		
		System.out.println("������ �������� ������� 28: " + tree.contains(28));
		tree.remove(28);
		System.out.println("������� �������� ������� 28: ");
		tree.printTree(tree.header, 1);
		System.out.println("������ �������� ������� 28: " + tree.contains(28));
		tree.remove(25);
		System.out.println("������� �� �������� ������� 25: ");
		tree.printTree(tree.header, 1);
		tree.remove(7);
		System.out.println("������� �� �������� ������� 7: ");
		tree.printTree(tree.header, 1);
		tree.remove(13);
		System.out.println("������� �� �������� ������� 13: ");
		tree.printTree(tree.header, 1);

		System.out.println("������� ��� �������� ��� ������ ���������: ");
		for (NBTreeNode<Integer> node : tree) {
			System.out.println(node.pageInt.toString());
		}
		
	}

}
