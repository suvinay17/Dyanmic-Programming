/* 01 knapsack by suvinay
*/
import java.util.*;
import java.io.*;
public class Knapsack {
  static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    String input; //input line
    //for parsing all test cases
    while((input=br.readLine())!=null) {

      // Read input
      String[] split=input.split(" "); //split by delimeter space
      int capacity=Double.valueOf(split[0]).intValue();
      int n=Integer.valueOf(split[1]);
      int[] values=new int[n];
      int[] weights=new int[n];
      for(int i=0; i< n; i++) {
        split=br.readLine().split(" ");
        values[i]=Integer.valueOf(split[0]);
        weights[i]=Integer.valueOf(split[1]);
      }
      // Compute result
      List<Integer> result=knapsack(values, weights, n, capacity);

      // Output result
      System.out.println(result.size());
      for(int i=0; i<result.size(); i++) {
        if(i>0)
          System.out.print(" ");
        System.out.print(result.get(i));
      }
      System.out.println();

    }

  }

  static List<Integer> knapsack(int[] values, int[] weights, int n, int capacity) {

    int[][] optimumVal=new int[n+1][capacity + 1];

    // For each item (every row)
    for(int i=1; i<=n; i++) {

      int itemIndex=i-1;


      for(int j=0;j<=capacity;j++) {

        int sumWeight=j+weights[itemIndex];
        int totalValue=optimumVal[i-1][j]+values[itemIndex];
        optimumVal[i][j]=Math.max(optimumVal[i][j], optimumVal[i - 1][j]);
        if(sumWeight<=capacity)
          optimumVal[i][sumWeight]=Math.max(totalValue, optimumVal[i][sumWeight]);

      }

    }

    // Backtracking to generate list
    List<Integer> selectedItems=new ArrayList<Integer>();
    int column=capacity;
    for(int i=n;i>0;i--) {
      if(optimumVal[i][column]!=optimumVal[i-1][column]) {
        int itemIndex=i - 1;
        selectedItems.add(itemIndex);
        column-=weights[itemIndex];
      }
    }
    return selectedItems;

  }

}
