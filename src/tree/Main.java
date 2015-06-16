package tree;
public class Main {

	public static void main(String[] args) {
		NBTree<Integer> tree = new NBTree<Integer>();
		for(int i= 5; i< 29; i++) {
			tree.add(i);
		}
		System.out.println("Достроили дерево: ");
		tree.printTree(tree.header, 1);
		
		System.out.println("Дерево содержит элемент 28: " + tree.contains(28));
		tree.remove(28);
		System.out.println("Удалили листовой элемент 28: ");
		tree.printTree(tree.header, 1);
		System.out.println("Дерево содержит элемент 28: " + tree.contains(28));
		tree.remove(25);
		System.out.println("Удалили не листовой элемент 25: ");
		tree.printTree(tree.header, 1);
		tree.remove(7);
		System.out.println("Удалили не листовой элемент 7: ");
		tree.printTree(tree.header, 1);
		tree.remove(13);
		System.out.println("Удалили не листовой элемент 13: ");
		tree.printTree(tree.header, 1);

		System.out.println("Выводим все элементы при помощи итератора: ");
		for (NBTreeNode<Integer> node : tree) {
			System.out.println(node.pageInt.toString());
		}
		
	}

}
