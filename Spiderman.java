/* Spiderman suvinay
*/
import java.util.*;
import java.io.*;
public class Spiderman{
  static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  // do not change final
  static final int NOT_COMPUTED=-1;
  static final int UP=1;
  static final int DOWN=2;
  static final int IMPOSSIBLE=10000;

  // DP table
  static int[][] table=new int[40][1001];
  static int[][] prev= new int[40][1001];
  public static void main(String[] args) throws IOException {
    int t=Integer.valueOf(br.readLine());
    //Parse each case and decerement T
    while(t-- >0) {
      //number of distances
      int n=Integer.valueOf(br.readLine());
      // parse distances
      String[] line=br.readLine().split(" ");
      int[] distances=new int[n];
      for (int i=0;i<n;i++)
        distances[i]=Integer.valueOf(line[i]);
// Compute the result
      resetTable();
      if(smallestHeight(distances, 0, 0)==IMPOSSIBLE) {
        System.out.println("IMPOSSIBLE");
        continue;
      }

      // output with string builder since it is O(n) to construct string in java
      StringBuilder sb=new StringBuilder();
      int height=0;
      for(int i=0;i<n;i++) {
        if(prev[i][height]==UP) {
          sb.append("U");
          height+=distances[i];
        } else{
          sb.append("D");
          height-=distances[i];
        }
      }
      System.out.println(sb);

    }

  }

  static int smallestHeight(int[] distances, int index, int height) {

    // Below ground
    if (height<0)
      return IMPOSSIBLE;
    // Base case
    if(index==distances.length)
      return height==0?height:IMPOSSIBLE; //else impossibe, ternary operator
    // Check to see if this value was already computed
    if(table[index][height]!=NOT_COMPUTED)
      return table[index][height];
    // Try going both directions
    int up = smallestHeight(distances, index + 1, height + distances[index]);
    int down = smallestHeight(distances, index + 1, height - distances[index]);
    // Pick the best one
    int minimum;
    if(up<down) {
      minimum=up;
      prev[index][height]=UP;
    } else{
      minimum=down;
      prev[index][height]=DOWN;
    }
    // Store and return the minimum attainable height
    return table[index][height]=Math.max(height, minimum);
  }
  // Reset the arrays used for memoieization
  static void resetTable() {
    for(int[] arr:table)
      Arrays.fill(arr, NOT_COMPUTED);
    for(int[] arr:prev)
      Arrays.fill(arr, NOT_COMPUTED);
  }

}
