/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

/**
 *
 * @author Noelia Intriago
 */
public interface List<E> {
    boolean addFirst(E element);
    boolean addLast(E element);
    boolean add(E element, int index);
    E get(int index);
    int size();
    boolean isEmpty();
    boolean remove(int index);
    boolean contains(E element);
    boolean removeFirst();
    boolean removeLast();
    E getFirst();
    E getLast();
}
