package Seminar3;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        for (int i = 0; i < 6; i++) {
            list.add(new Random().nextInt(20));
        }
        list.print();
        list.reverseList();
        list.print();

    }
}
