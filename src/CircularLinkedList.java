import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Class for circular linked-lists.
 *
 * @author Hwansu Kim (Billy)
 * @version 05.13.2022
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E>, Iterable<E>{

    /** first node reference. */
    private Node<E> front;
    /** last node reference. */
    private Node<E> end;
    /** count of nodes in the list. */
    private int size;


    /**
     * Class constructor.
     */
    public CircularLinkedList() {
        front = null;
        end = null;
        size = 0;
    }

    /**
     * Retrieves a count of elements being maintained by the list.
     *
     * @return the size of the list (count of elements)
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Retrieves the data at the specified position in the list
     *
     * @param position 0-based index for the list; must be in the range 0 to size - 1
     * @return the data in the specified position in the list
     */
    @Override
    public E get(int position) {
        checkPositionRange(position);
        if (size == 0) {
            throw new NoSuchElementException("The list is empty.");
        }
        Node<E> current = front;
        if (position == size - 1) {
            return end.data;
        } else {
            int currentPos = 0;
            while (currentPos != position) {
                current = current.next;
                currentPos++;
            }
            return current.data;
        }
    }

    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param value the element to add to the list
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (front == null) {
            front = newNode;
            end = newNode;
            newNode.next = front;
        } else {
            Node<E> current = front;
            while (current.next != front) {
                current = current.next;
            }
            current.next = newNode;
            end = current.next;
            end.next = front;
        }
        size++;
    }

    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        Node<E> current = front;
        Node<E> previous = end;
        while (current.data != value && current != end) {
            previous = current;
            current = current.next;
        }
        if (current == end && current.data != value) {
            return false;
        } else if (current == front) {
            front = current.next;
            current.next = null;
            size--;
            return true;
        } else if (current == end) {
            end = previous;
            previous.next = front;
            current.next = null;
            size--;
            return true;
        } else {
            previous.next = current.next;
            current.next = null;
            size--;
            return true;
        }

    }

    /**
     * Removes the node at the specified position in the list
     *
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {
        checkPositionRange(position);
        Node<E> current = front;
        Node<E> previous = end;
        int currentPos = 0;
        while (currentPos != position) {
            previous = current;
            current = current.next;
            currentPos++;
        }
        if (current == front) {
            front = current.next;
            current.next = null;
        } else if (current == end) {
            end = previous;
            previous.next = front;
            current.next = null;
        } else {
            previous.next = current.next;
            current.next = null;
        }
        size--;
    }

    /**
     * Retrieves an iterator over the list's elements.  Do not do other list operations like add or remove
     * from within an iterator loop; the results are not guaranteed to function as you might expect
     *
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }


    /**
     * Inner class for nodes
     */
    private static class Node<T> {
        /** object to be held. */
        public T data;
        /** reference to the next node. */
        public Node<T> next;

        /**
         * Inner class constructor.
         *
         * @param data the object to be held.
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }


    /**
     * Inner class for outer class iterator.
     */
    private class CircularLinkedListIterator implements Iterator<E> {

        /** Current node by count (Index-based; starts at 0). */
        private int position;

        /**
         * Inner class constructor.
         */
        public CircularLinkedListIterator() {
            position = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return front != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result;
            if (position == size - 1) {
                result = get(position);
                position = 0;
            } else {
                result = get(position);
                position++;
            }
            return result;
        }
    }

    /**
     * Exception check for out of range values by node position.
     *
     * @param target the number to be checked.
     */
    private void checkPositionRange(int target) {
        if (target < 0 || target > size - 1) {
            throw new IllegalArgumentException("Position range must be between 0 and size - 1.");
        }
    }
}
