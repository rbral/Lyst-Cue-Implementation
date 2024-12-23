package org.example;

public interface ILyst<T>
{
    public void addFirst(T value);
    public void addLast(T value);
    public T removeFirst();
    public T removeLast();
    public boolean remove(T value);
    public Boolean contains(T value);
    public Boolean addBefore(T value, T before);
    public Boolean addAfter(T value, T after);
    public T getFirst();
    public T getLast();
    public T getNext(T value);
    public T getPrev(T value);
    public int size();
}

