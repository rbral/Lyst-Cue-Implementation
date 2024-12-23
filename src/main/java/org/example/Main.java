package org.example;

import java.util.function.BiPredicate;

public class Main
{
    public static void main(String[] args)
    {
        /*IsraeliCue<Integer> queue = new IsraeliCue<>();

        // Define a "friendship" rule where two numbers are friends if one is divisible by the other
        BiPredicate<Integer, Integer> isFriend = (a, b) -> a % b == 0 || b % a == 0;

        // Enqueue elements with friendship criteria
        queue.encue(10, isFriend); // No friends, goes to the end
        queue.encue(5, isFriend);  // Friend with 10, so goes before 10
        queue.encue(3, isFriend);  // No friends, goes to the end
        queue.encue(15, isFriend); // Friend with 5, goes before 5


        // Display the queue
        System.out.println(queue.toString());

        queue.decue();

        System.out.println(queue);*/

        /*PoliteCue<Integer> pCue = new PoliteCue<>();
        BiPredicate<Integer, Integer> isFriend = (a, b) -> a % b == 0 || b % a == 0;

        // Enqueue elements with friendship criteria
        pCue.encue(10, isFriend);
        pCue.encue(5, isFriend);
        pCue.encue(3, isFriend);
        pCue.encue(15, isFriend);


        // Display the queue
        System.out.println(pCue.toString());
        // 10
        // 5 10
        // 5 10 3
        // 10 15 5 3*/


        SwapCue<Integer> sCue = new SwapCue<>();

        BiPredicate<Integer, Integer> isFriend = (a, b) -> a % b == 0 || b % a == 0;

        // Enqueue elements with friendship criteria
        sCue.encue(10, isFriend);
        sCue.encue(5, isFriend);
        sCue.encue(3, isFriend);
        sCue.encue(15, isFriend);
        sCue.encue(30, isFriend);


        // Display the queue
        System.out.println(sCue.toString());
        // 10
        // 5 10
        // 5 10 3
        // 15 10 3 5
        // 15 10 3 5
    }
}
