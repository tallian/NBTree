import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class NBTree<T> implements IBinaryTree<T>, Iterable<NBTreeNode<T>>, Iterator<NBTreeNode<T>> {
	NBTreeNode<T> header;
	NBTreeNode<T> current; 
	NBTreeNode<T> link;
	NBTreeNode<T> currentIt;
	Stack<NBTreeNode<T>> terminator;
	Boolean first;
	public static final Integer ElOnPage = 2; // минимальное количество элементов на странице, оно же половина максимального количества элементов на странице
	
	NBTree() {
		header = null;
	}
	
	public void printTree(NBTreeNode<?> firstLink, Integer layer) {
		if (firstLink != null) {
			System.out.print("| level: "+ layer + " element: " + firstLink.pageInt.toString() + " |");
			System.out.println("");
			if (firstLink.firstLink != null) {
				printTree(firstLink.firstLink, layer + 1);
			}
			for (int i=0; i<firstLink.size; i++){
				if (firstLink.pageLink.get(i) != null) {
						printTree(firstLink.pageLink.get(i), layer + 1);
				}
			}
			
		}
	}
	
	public NBTreeNode find(Comparable<T> e) {
		current = header;
		
		// Найти нужный лист
		do {
			link = null;
			if (e.compareTo((T) current.pageInt.get(0)) < 0) {
				link = (NBTreeNode<T>) current.firstLink;
			} else {
				int last = current.pageInt.size()-1;
				for(int i=0; i<last; i++) {
					if ((e.compareTo((T) current.pageInt.get(i)) > 0) && (e.compareTo((T) current.pageInt.get(i+1)) < 0)) {
						if (current.pageLink.get(i) != null) {
							link = current.pageLink.get(i);
						} 
					} else if (e.compareTo((T) current.pageInt.get(i)) == 0) {
						link = (NBTreeNode<T>) current.firstLink;
						return current;
					} 
				}
				if (link == null) {
					if (e.compareTo((T) current.pageInt.get(last)) == 0) {
						link = (NBTreeNode<T>) current.firstLink;
						return current;
					} 
					if (e.compareTo((T) current.pageInt.get(last)) > 0) {
						if (current.pageLink.get(last) != null) {
							link = current.pageLink.get(last);
						}
					}
				}
			}
			if (link != null) current = link;
		} while (link != null);
		return link;
	}

	@Override
	public boolean add(Comparable<T> e) {
		if (header == null) {
			header = new NBTreeNode<T>(e, null);
			return true;
		} else {
			if (find(e) != null) 
				return false;
			
			NBTreeNode<T> right = null;
			NBTreeNode<T> left = null;
			
			//До тех пор, пока в текущей странице максимально полный набор элементов, делим её на две и один элемент добавляем в родительскую
			
			while(current.size == 2*ElOnPage) {
				Integer last = current.pageInt.size()-1;
				// Добавляем элемент на подходящую ему позицию, устанавливаем для него ссылки на поделенное пополам в предыдущей интеракции
				for(int i=0; i<last; i++) {
					if ((e.compareTo((T) current.pageInt.get(i)) > 0) && (e.compareTo((T) current.pageInt.get(i+1)) < 0)) {
						current.addElement(i, e, right);
						current.pageLink.set(i, left);
					} 
				}
				if ((e.compareTo((T) current.pageInt.get(last)) > 0)) {
					current.addElement(last, e, right);
					current.pageLink.set(last, left);
				} 
				
				// Делим пополам
				ArrayList<Comparable<T>> first = new ArrayList<Comparable<T>>(current.pageInt.subList(0, current.pageInt.size()/2));
				ArrayList<Comparable<T>> second = new ArrayList<Comparable<T>>(current.pageInt.subList(current.pageInt.size()/2+1, current.pageInt.size()));				
				
				left = new NBTreeNode<T>(first);
				right = new NBTreeNode<T>(second);
				
				// Восстанавливаем ссылки на дочерние элементы у того, что поделили.
				// Для левого
				left.firstLink = current.firstLink;
				left.pageLink = new ArrayList<NBTreeNode<T>>(current.pageLink.subList(0, current.pageInt.size()/2));
				if (left.firstLink != null) {
					left.firstLink.parent = (NBTreeNode<T>) left;
					for (int j =0; j< left.pageLink.size(); j++) {
						left.pageLink.get(j).parent = left;
					}
				}
				// Для правого
				right.firstLink = current.pageLink.get(current.pageInt.size()/2);
				right.pageLink = new ArrayList<NBTreeNode<T>>(current.pageLink.subList(current.pageInt.size()/2+1, current.pageInt.size()));
				if (right.firstLink != null) {
					right.firstLink.parent = right;
					for (int j =0; j< right.pageLink.size(); j++) {
						right.pageLink.get(j).parent = right;
					}
				}
				left.parent = current.parent;
				right.parent = current.parent;
				
				// Выбираем элемент, который надо вставить теперь.
				e = current.pageInt.get(current.pageInt.size()/2);
				
				// Если дошли до корня - создаём новый.
								
				if (current.parent == null) {
					NBTreeNode<T> newHeader = new NBTreeNode<T>(current.pageInt.get((last-1)/2+1), null);
					newHeader.firstLink = left;
					newHeader.pageLink.set(0, right);
					left.parent = newHeader;
					right.parent = newHeader;
					header = newHeader;
					current = header;
					return true;
				} else {
					current = current.parent;
				}
			}
			// Если количество элементов в узле не превышает максимума
			 // Если в начало
			Integer last = current.size-1;
			if (e.compareTo((T) current.pageInt.get(0)) < 0) {
				current.addElement(-1, e, right);
				current.firstLink = left;
				return true;
			} 
			// Если в серединц
			for(int i=0; i<last; i++) {
				if ((e.compareTo((T) current.pageInt.get(i)) > 0) && (e.compareTo((T) current.pageInt.get(i+1)) < 0)) {
					current.addElement(i, e, right);
					current.pageLink.set(i, left);
					return true;
				} 
			}
			// Если последний
			if (e.compareTo((T) current.pageInt.get(last)) > 0) {
				current.addElement(last, e, right);
				current.pageLink.set(last, left);
				return true;
			} 
			
			return true;
		}
	}
	
	public Integer getLinkIndex(NBTreeNode<T> node) {
		if (node.parent != null) {
			if (node == node.parent.firstLink) {
				return -1;
			} else {
				return node.parent.pageLink.indexOf(node);
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean remove(Comparable<T> o) {
		if (header == null) {
			return false;
		} else {
			if (find(o) == null) 
				return false;
			int delete = current.pageInt.indexOf(o);
			if (link == null) { // Если концевая страница - просто удаляем.
				current.delete(delete);
			} else { // если из середины, ищем самое правое значение левого потомка.
				NBTreeNode<T> tmp = current;
				if (delete == 0) {
					current = (NBTreeNode<T>) current.firstLink;
				} else {
					current = current.pageLink.get(delete-1);
				}
				link = current.pageLink.get(current.size-1);
				
				while (link != null){
					current = link;
					link = link.pageLink.get(current.size-1);
				}
				// Удаляем
				tmp.pageInt.set(delete, current.pageInt.get(current.size-1));
				current.delete(current.size-1);
			}
			// Если на в странице осталось меньше допустимого элементов.
		
			if (current.size < ElOnPage) {
				// Пробуем позаимствовать у соседей
				NBTreeNode<T> element;
				do {
					int index = getLinkIndex(current);
					// Выбираем соседа
					if(index==0) {
						element = (NBTreeNode<T>) current.parent.firstLink;
					} else if (index == -1) {
						element = (NBTreeNode<T>) current.parent.pageLink.get(0);
					} else {
						element = (NBTreeNode<T>) current.parent.pageLink.get(index-1);
					} 
					
					// Если у соседа больше минимума элементов
					if (element.size > ElOnPage) {
						if (index == -1 ) {
							current.addElement(current.size-1, (Comparable<T>) current.parent.pageInt.get(0), (NBTreeNode<T>) element.firstLink);
							if (element.firstLink != null) element.firstLink.parent = current;
							current.parent.pageInt.set(0, element.pageInt.get(0));
							element.delete(0);
							return true;
						} else {
							current.addElement(-1, (Comparable<T>) current.parent.pageInt.get(index), current.firstLink);
							current.firstLink = element.pageLink.get(current.size-1);
							if (current.firstLink != null) current.firstLink.parent = current;
							current.parent.pageInt.set(index, element.pageInt.get(element.size-1));
							element.delete(element.size-1);
							return true;
						}
					} else {
						// Если у соседей ровно минимум - сливаем с соседом, исключая из родительского элемента разделитель.
						// Повторяем до корня или достаточно большого родительского элемента.
						// Если элемент первый - сливаем c правым и добавляем в начало страницы.
							if (index == -1) {									
								element.addElement(-1, (Comparable<T>) current.parent.pageInt.get(0), (NBTreeNode<T>) element.firstLink);
								element.firstLink = current.pageLink.get(current.size-1);
								if (element.firstLink != null) element.firstLink.parent = element;
								current.parent.delete(0);
															
								for (int i=current.size-1; i>=1; i--) {
									element.addElement(-1, current.pageInt.get(i), (NBTreeNode<T>) element.firstLink);
									element.firstLink = current.pageLink.get(i-1);
									if (element.firstLink != null) element.firstLink.parent = element;
								}
								element.addElement(-1, current.pageInt.get(0), (NBTreeNode<T>) element.firstLink);
								if (current.firstLink != null) current.firstLink.parent = element;
								element.firstLink = current.firstLink;				
							} else { // если элемент не первый - с левым
								
								element.addElement(element.size-1, (Comparable<T>) current.parent.pageInt.get(index), (NBTreeNode<T>) current.firstLink);
								if (current.firstLink != null) current.firstLink.parent = element;
								current.parent.delete(index);
															
								for (int i=0; i<current.size; i++) {
									element.addElement(element.size-1, current.pageInt.get(i), current.pageLink.get(i));
									if (current.firstLink != null) current.pageLink.get(i).parent = element;
								}
							}
							
							if (element.parent.size == 0) {
								header = element;
							}
							else {
								current = current.parent;
							}		
						}
					}
					while (current.size < ElOnPage && header != element);			
					return true;
				// Повторяем проверку для родителя, если надо - стягиваем до корня.
				
			} else {
				return true;
			}
			
		}
	}

	@Override
	public boolean contains(Comparable<T> o) {
		if (find(o) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Iterator<NBTreeNode<T>> iterator() {
		currentIt = header;
		terminator = new Stack();
		first = true;
		return this;
	}

	@Override
	public boolean hasNext() {
		if (currentIt == header && terminator.isEmpty()) {
			return true;
		} 
		NBTreeNode<T> tmp = terminator.lastElement().parent;
		NBTreeNode<T> current = terminator.lastElement();
		if (tmp == null && first) {
			return true;
		}
		if (current.firstLink != null) {
			return true;
		} else {
			do {
				Integer index = null;
				
				if (tmp != null) {
					if (current.pageInt.equals(tmp.firstLink.pageInt)) {
						index = -1;
					} else {
						for (int i = 0; i<tmp.size; i++) {
							if (current.pageInt.equals(tmp.pageLink.get(i).pageInt)) {
								index = i;
							}
						}
					}
				} 
				if (index == null) {
					return false;
				}
				if (index == -1) {
					return true;
				} else if (index != tmp.size-1) {
					return true;
				} else {
					current = new NBTreeNode<T>(tmp.pageInt);
					tmp = tmp.parent;
					current.parent = tmp;
				}
			}
			while (tmp!=null);
		} 
		return false;
		
	}

	@Override
	public NBTreeNode<T> next() {
		if (currentIt == header && terminator.isEmpty()) {
			terminator.push(currentIt);
			return header;
		} 
		NBTreeNode<T> tmp = terminator.lastElement().parent;
		NBTreeNode<T> current = terminator.lastElement();
		if (tmp == null && first) {
			first = false;
			terminator.push(current.firstLink);
			currentIt = terminator.lastElement();
			return terminator.lastElement();
		}
		if (current.firstLink != null) {
			terminator.push(current.firstLink);
			currentIt = terminator.lastElement();
			return terminator.lastElement();
		} else {
			do {
				Integer index = null;
				
				if (tmp != null) {
					if (current.pageInt.equals(tmp.firstLink.pageInt)) {
						index = -1;
					} else {
						for (int i = 0; i<tmp.size; i++) {
							if (current.pageInt.equals(tmp.pageLink.get(i).pageInt)) {
								index = i;
							}
						}
					}
				} 

				if (index == -1) {
					terminator.push(tmp.pageLink.get(0));
					currentIt = terminator.lastElement();
					return terminator.lastElement();
				} else if (index != tmp.size-1) {
					terminator.push(tmp.pageLink.get(index+1));
					currentIt = terminator.lastElement();
					return terminator.lastElement();
				} else {
					current = new NBTreeNode<T>(tmp.pageInt);
					tmp = tmp.parent;
					current.parent = tmp;
				}
			}
			while (tmp!=null);
		} 
		return null;
		
	}

	@Override
	public void remove() {
		
	}

}
