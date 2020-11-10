package Chapter6Recursion.Task1;

public class Task1 {

    public static void main(String[] args) {
        System.out.println(Task1.multiplyUsingAddition(2, 3));
    }

    static int multiplyUsingAddition(int multiplicand, int multiplier) {
        if (multiplier == 1) {
            return multiplicand;
        } else {
            return multiplicand + multiplyUsingAddition(multiplicand, multiplier - 1);
        }
    }
}
