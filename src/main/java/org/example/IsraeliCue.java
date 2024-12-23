package org.example;

import java.util.function.BiPredicate;

/**
 * Israeli Cue - an extension of the Cue class
 * When a new element is enqueued, it searches for a friend
 * starting at the head of the queue moving down the line.
 * When it finds a friend, it is enqueued in front of the friend.
 *
 * @param <T> generic class with parameter T
 * @author Rivka Bral
 */
public class IsraeliCue<T> extends Cue<T>
{
    /**
     * encue - enqueue an element based on friendship criteria.
     * If a friend is found, the element is inserted before the friend.
     * Otherwise, it is added to the end of the queue.
     *
     * @param value    the element to add
     * @param isFriend the BiPredicate that determines if two elements are friends
     */

    public void encue(T value, BiPredicate<T, T> isFriend)
    {
        boolean friendFound = false;

        Lyst.ForwardIterator it = this.forwardIterator();

        while (it.hasNext())
        {
            T curr = (T) it.next();
            if (isFriend.test(curr, value))
            {
                lyst.addBefore(value, curr);
                friendFound = true;
                break;
            }
        }

        if (!friendFound)
        {
            // call original encue method to add to end of cue
            super.encue(value);
        }


    }

}
