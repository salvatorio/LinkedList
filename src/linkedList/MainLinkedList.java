package linkedList;

/**
 * Created on 1. July. 16.
 *
 * @author Evgeniy
 */

public class MainLinkedList {

    public static void main(String[] args) {

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();

        System.out.println("Is empty = " + linkedList.isEmpty());

        linkedList.add(100);
        linkedList.add(200);
        linkedList.add(300);
        linkedList.add(400);
        linkedList.add(500);
        linkedList.add(600);
        linkedList.add(650);

        linkedList.print();
        System.out.println("Is empty = " + linkedList.isEmpty());
        System.out.println("Does list contains element '-50' : " + linkedList.contains(-50));

        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        linkedList.add(2, -50);
        linkedList.print();
        System.out.println("Does list contains element '-50' : " + linkedList.contains(-50));

        linkedList.add(4, -150);
        linkedList.print();

        linkedList.add(53);
        linkedList.print();

        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                           "; Size = " + linkedList.size());

        linkedList.remove(200);
        linkedList.print();

        System.out.println(linkedList.get(7));

        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        System.out.println("Element #3 = " + linkedList.get(2));
        linkedList.set(2, -10000);

        System.out.println("Element #3 = " + linkedList.get(2));
        linkedList.print();

        linkedList.addFirst(-1);
        linkedList.print();

        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        System.out.println("Element #1 = " + linkedList.get(0));
        System.out.println("Element #4 = " + linkedList.get(3));

        linkedList.print();

        System.out.println("Before removing first and last elements: First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        linkedList.removeLast();
        linkedList.print();

        linkedList.removeFirst();
        linkedList.print();

        System.out.println("After removing first and last elements: First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        for (Integer i: linkedList) {
            System.out.print(i + " ");
        }
    }
}
