package linkedList;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.Queue;

/**
 * Created on 1. July. 16.
 *
 * @author Evgeniy
 */

public class MyLinkedList<T> implements Iterable<T>, Queue<T> {

    private Node<T> first;
    private Node<T> last;
    private int modificationCount = 0;
    private int size;

    private class Node<T> {

        private T value;
        private Node next;
        private Node previous;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }
    }

    public T getFirst() {
        return (T) first.value;
    }

    public T getLast() {
        return (T) last.value;
    }

    public void addFirst(T element) {

        if (first == null) {
            first = new Node(element);
            last = first;
            size++;
            modificationCount++;
            return;
        }
        Node node = new Node(element);
        first.previous = node;
        node.next = first;
        first = node;
        size++;
        modificationCount++;
    }

    @Override
    public boolean add(T element) {

        if (first == null) {
            first = new Node(element);
            last = first;
            size++;
            modificationCount++;
            return true;
        }
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(element);
        last = temp.next;
        last.previous = temp;
        size++;
        modificationCount++;
        return true;
    }

    public void add(int index, T element) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        else if (index == 0) {
            addFirst(element);
            return;
        }
        else if (index == size) {
            add(element);
            return;
        }
        modificationCount++;
        Node temp = getNode(index - 1);
        Node node = new Node(element);
        temp.next.previous = node;
        node.next = temp.next;
        node.previous = temp;
        temp.next = node;
        size++;
    }

    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        Node temp = getNode(index);
        return (T) temp.value;
    }

    public void set(int index, T value) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        modificationCount++;
        Node temp = getNode(index);
        temp.value = value;
    }

    private Node getNode(int index) {

        int i;
        Node temp;
        if (index < size / 2) {
            i = 0;
            temp = first;
            while (i != index) {
                temp = temp.next;
                i++;
            }
        } else {
            i = size - 1;
            temp = last;
            while (i != index) {
                temp = temp.previous;
                i--;
            }
        }
        return temp;
    }

    public T removeByIndex(int index){

        checkIndex(index);
        Node<T> current;
        T removedValue;

        if(index < size()/2){
            current = first;
            for(int i = 1; i < index; i++){
                current = current.next;
            }
        }else{
            current = last;
            for(int i = size-2; i > index; i--){
                current = current.previous;
            }
        }

        if (current.next != null){
            current.next.previous = current.previous;
        }
        if (current.previous != null){
            current.previous.next = current.next;
        }

        removedValue = (T) current.value;
        return removedValue;
    }

    public T removeFirst() {

        if (first == null && last == null) {
            throw new UnsupportedOperationException();
        } else if (first == last) {
            size--;
            Node temp = first;
            first = last = null;
            return (T) temp.value;
        }
        modificationCount++;
        Node temp = first;
        first = first.next;
        first.previous = null;
        size--;
        return (T) temp.value;
    }

    public T removeLast() {

        if (first == null && last == null) {
            throw new UnsupportedOperationException();
        } else if (last == first) {
            size--;
            Node temp = first;
            first = last = null;
            return (T) temp.value;
        }
        modificationCount++;
        Node temp = last;
        last = last.previous;
        last.next = null;
        size--;
        return (T) temp.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {

        Node temp = first;
        while (temp != null) {
            if (temp.value.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean checkIndex(int index) {
        if (index > 0 || index <= size()){
            return true;
        }
        throw new IllegalArgumentException("Invalid value of index. Should be from range [ 0 ; " + size + "] ");
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {

        modificationCount++;
        Node<T> current = first;

        while (current != null){
            if (current.value.equals(o)){
                current.next.previous = current.previous;
                current.previous.next = current.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
        modificationCount = 0;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public ListIterator<T> iterator() {

        return new ListIterator<T>() {

            int modCount = 0;
            Node<T> current = first;
            int currentIndex = 0;
            Node<T> returnedNode = null;

            @Override
            public boolean hasNext() {
                return (current != null && current.next != null);
            }

            @Override
            public T next() {
                returnedNode = current;
                current = current.next;
                currentIndex++;
                return (T) returnedNode.value;
            }

            @Override
            public boolean hasPrevious() {
                return (current != null && current.previous != null);
            }

            @Override
            public T previous() {
                returnedNode = current;
                current = current.previous;
                currentIndex--;
                return returnedNode.value;
            }

            @Override
            public int nextIndex() {
                return currentIndex++;
            }

            @Override
            public int previousIndex() {
                return currentIndex--;
            }

            @Override
            public void remove() {

                checkMods();
                modCount++;
                if (returnedNode == null) {
                    throw new IndexOutOfBoundsException();
                }
                if (returnedNode == first) {
                    MyLinkedList.this.removeFirst();
                } else if (returnedNode == last) {
                    MyLinkedList.this.removeLast();
                } else {
                    returnedNode.next.previous = returnedNode.previous;
                    returnedNode.previous.next = returnedNode.next;
                    returnedNode = null;
                }
            }

            @Override
            public void set(T t) {
                checkMods();
                modCount++;
                returnedNode.value = t;
            }

            @Override
            public void add(T t) {

                checkMods();
                modCount++;
                if (returnedNode == first) {
                    MyLinkedList.this.addFirst(t);
                } else if (returnedNode == last) {
                    MyLinkedList.this.add(t);
                } else {
                    Node node = new Node(t);
                    returnedNode.next.previous = node;
                    node.next = returnedNode.next;
                    returnedNode.next = node;
                    node.previous = returnedNode;
                }
            }

            private void checkMods() {
                if (this.modCount != MyLinkedList.this.modificationCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    public void print() {

        Node temp = first;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
