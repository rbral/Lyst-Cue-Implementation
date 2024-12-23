package org.example;

public interface ICue<T>
{
    public void encue(T value);
    public T decue();
    public T peek();
    public boolean isEmpty();
}
