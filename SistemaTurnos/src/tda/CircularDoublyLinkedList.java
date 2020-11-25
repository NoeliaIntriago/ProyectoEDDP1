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
public class CircularDoublyLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int current;
    
    private class Node<E>{
        private E data;
        private Node<E> previous;
        private Node<E> next;
        
        public Node(E data){
            this.data = data;
        }
    }
    
    @Override
    public boolean addFirst(E element) {
        if(element == null) return false;
        Node<E> n = new Node<>(element);
        if(isEmpty()){
            first = last = n;
        }else{
            n.next = first;
            first.previous = n;
            first = n;
            n.previous = last;
            last.next = first;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if(element == null) return false;
        Node<E> n = new Node<>(element);
        if(isEmpty()){
            first = last = n;
        }else{
            last.next = n;
            n.previous = last;
            last = n;
            n.next = first;
            first.previous = n;
        }
        current++;
        return true;
    }

    @Override
    public boolean add(E element, int index) {
        if(element == null || index < 0 || index > current) return false;
        Node<E> n = new Node<>(element);
        if(index == 0) return addFirst(element);
        if(index == current) return addLast(element);
        int i = 0; Node<E> f = first;
        while(i != index){
            f = f.next;
            i++;
        }
        n.previous = f.previous;
        n.next = f;
        f.previous.next = n;
        current++;
        return true;
    }

    @Override
    public E get(int index) {
        if(isEmpty() || index < 0 || index >= current) return null;
        if(index == 0) return getFirst();
        if(index == current-1) return getLast();
        Node<E> f = first;
        for(int i = 0; i < index; i++){
            f = f.next;
        }
        return f.data;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public boolean remove(int index) {
        if(index < 0 || index >= current) return false;
        if(index == 0) return removeFirst();
        if(index == current-1) return removeLast();
        int i = 0; Node<E> f = first;
        while(i != index){
            f = f.next;
            i++;
        }
        f.previous.next = f.next;
        f.next.previous = f.previous;
        f.next = f.previous = null;
        f.data = null;
        current--;
        return true;
    }

    @Override
    public boolean contains(E element) {
        Node<E> p = last.next;
        do{
            if(p.data.equals(element)) return true;
            p = p.next;
        }while(p != last.next);
        return false;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        if(first == last){
            first.data = null;
            first = last = null;
        }else{
            Node<E> tmp = first;
            first = first.next;
            last.next = first;
            first.previous = last.next;
            tmp.data = null;
            tmp.next = null;   
        }
        current--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        if(first == last){
            first.data = null;
            first = last = null;
        }else{
            last.data = null;
            Node<E> prev = last.previous;
            prev.next = first;
            last.previous = null;
            last = prev;
            first.previous = last;
        }
        current--;
        return true;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return first.data;
    }

    @Override
    public E getLast() {
        if(isEmpty()) return null;
        return last.data;
    }
    
    @Override
    public String toString(){
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Node<E> i = first; i != last; i = i.next){
            sb.append(i.data);
            sb.append(", ");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }
}
