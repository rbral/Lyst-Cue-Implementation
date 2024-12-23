package org.example;

import java.util.function.BiPredicate;

/**
 * Polite Cue - an extension of the Cue class
 * When a new element is enqueued, it searches for a friend
 * starting at the head of the queue moving down the line.
 * Each friend is removed from its position and moved
 * to the end of the queue behind the new element.
 *
 * @param <T> generic class with parameter T
 * @author Rivka Bral
 */
public class PoliteCue<T> extends Cue<T>
{
    /**
     * encue - when a new element is enqueued, it searches for
     * a friend starting at the head of the queue moving down the line.
     * Each friend is removed from its position and moved to the end
     * of the queue behind the new element.
     *
     * @param value    the element to add
     * @param isFriend the BiPredicate that determines if two elements are friends
     */

    public void encue(T value, BiPredicate<T, T> isFriend)
    {
        int initialSize = lyst.size();
        super.encue(value);

        Lyst.ForwardIterator it = this.forwardIterator();
        int ix = 0;
        // only check those items that were already in the cue before
        while(it.hasNext() && ix < initialSize)
        {
            T curr = (T) it.next();
            ++ix;

            if (isFriend.test(curr, value))
            {
                // remove from current position and add to end of cue
                it.remove();
                super.encue(curr);
            }
        }

    }
}
