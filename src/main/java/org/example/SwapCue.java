package org.example;

import java.util.function.BiPredicate;

/**
 * Swap Cue - an extension of the Cue class
 * When a new element is enqueued, it searches for a friend
 * starting at the head of the queue moving down the line.
 * If it finds a friend, it swaps its (last) position
 * in the queue with the friend.
 *
 * @param <T> generic class with parameter T
 * @author Rivka Bral
 */
public class SwapCue<T> extends Cue<T>
{
    /**
     * encue - when a new element is enqueued, it searches for a friend
     * starting at the head of the queue moving down the line.
     * If it finds a friend, it swaps its (last) position in the queue
     * with the friend.
     *
     * @param value    the element to add
     * @param isFriend the BiPredicate that determines if two elements are friends
     */
    public void encue(T value, BiPredicate<T, T> isFriend)
    {
        Lyst.ForwardIterator it = this.forwardIterator();
        boolean friendFound = false;

        while (it.hasNext())
        {
            T curr = (T) it.next();

            if (isFriend.test(curr, value))
            {
                // insert new node before the curr
                lyst.addBefore(value, curr);

                // remove curr and encue as last
                it.remove();
                super.encue(curr);
                friendFound = true;
                break;
            }
        }

        // if friend found, insert at end of cue
        if (!friendFound)
        {
            super.encue(value);
        }
    }
}
