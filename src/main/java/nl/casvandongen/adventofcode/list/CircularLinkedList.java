package nl.casvandongen.adventofcode.list;

import java.util.Iterator;
import java.util.LinkedList;

public class CircularLinkedList<E> extends LinkedList<E>
{
    @Override
    public Iterator<E> iterator()
    {
        return new CircularIterator<>(this);
    }

    public class CircularIterator<E> implements Iterator<E>
    {
        private LinkedList<E> list;
        private Iterator<E> iterator;

        public CircularIterator(LinkedList<E> list)
        {
            this.list = list;
            this.iterator = list.iterator();
        }

        @Override
        public boolean hasNext()
        {
            return !list.isEmpty();
        }

        @Override
        public E next()
        {
            return iterator.hasNext() ? iterator.next() : list.iterator().next();
        }

        @Override
        public void remove()
        {
            Iterator.super.remove();
        }
    }
}
