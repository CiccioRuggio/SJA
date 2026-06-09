
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            if (Math.random() > 0.5){
                int ipt = 0;
                double a = 5 / ipt;
                System.out.println("Result: " + a);
            }
            else {
                int arr[] = new int[3];
                arr[3] = 10;
                System.out.println(Arrays.toString(arr));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + "\n" + e.getClass());
            // e.printStackTrace();
        }
        finally {
            System.out.println("Finally block executed.");
        }
    }
}
