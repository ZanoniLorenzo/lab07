package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private final T[] implArray;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] array){
        this(
            array,
            new Predicate<T>() {
                T elem = null;
                public boolean test(T elem){
                    return this.elem == null;
                }
            });
    }

    public IterableWithPolicyImpl(T[] array, Predicate<T> filter){
        implArray = array;
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
       return new MyIterator();
    }

    class MyIterator implements Iterator<T>{
        private int current = 0;

        public boolean hasNext(){
            while(current < implArray.length == true && filter.test(implArray[current]) == false){
                current++;
            }
            return current < implArray.length;
        }

        public T next(){
            if (hasNext()){
                return implArray[current++];
            }
            throw new NoSuchElementException();
        }
    }
}