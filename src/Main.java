public class Main {

	public static void main(String[] args) {
		NBTree<Integer> tree = new NBTree<Integer>();
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.add(10);
		tree.add(11);
		tree.add(12);
		tree.add(13);
		//tree.printTree(tree.header, 1);
		tree.add(14);
		tree.add(15);
		tree.add(16);
		tree.add(17);
		tree.add(18);
		tree.add(19);
		tree.add(20);
		tree.add(21);
		tree.add(22);
		tree.add(23);
		tree.add(24);
		tree.add(25);
		tree.add(26);
		tree.add(27);
		tree.add(28);
		System.out.println("Достроили дерево: ");
		tree.printTree(tree.header, 1);
		
		
		tree.remove(28);
		System.out.println("Удалили листовой элемент 28: ");
		tree.printTree(tree.header, 1);
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
