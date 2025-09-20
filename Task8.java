public class Task8 {
    public static int cumulativeSum(int... numbers) {
        int totalSum = 0;
        for (int num : numbers) {
            int partialSum = 0;
            for (int i = 1; i <= num; i++) {
                partialSum += i;
            }
            System.out.println("Cumulative sum of " + num + " = " + partialSum);
            totalSum += num;
        }
        return totalSum;
    }
    public static void main(String[] args) {
        int result = cumulativeSum(4, 5, 10);
        System.out.println("Total sum of all parameters = " + result);
    }
}
