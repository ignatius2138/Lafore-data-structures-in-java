package Chapter6Recursion.Task3;

public class Task3 {

    public static void main(String[] args) {
        System.out.println(Task3.powUsingRecursion(2, 3));
    }

    static long powUsingRecursion(long base, long power) {
        if (power == 1) {
            return base;
        }
        if ((power % 2) != 0) { // power is odd
            return base * powUsingRecursion(base * base, power / 2);
        } else { // power is even
            return powUsingRecursion(base * base, power / 2);
        }
    }
}
