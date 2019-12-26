/* longest increasing subseq by suvinay
*/
import java.util.*;
import java.io.*;
public class LongestIncreasinSubsequence {
  static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    String input=null;
    // for parsing all test cases
    while((input=br.readLine())!=null) {
      // Process input
      int n=Integer.valueOf(input);
      List<Integer> seq=new ArrayList<Integer>(n);
      String split[] = br.readLine().split(" ");
      for(String num: split)
        seq.add(Integer.valueOf(num));
      // Find the longest increasing subsequence
      List<Integer> LIS=longestIncreasingSubsequence(seq);
      int length=LIS.size();

      // Output answer
      System.out.println(length);
      for(int i=0;i<length;i++) {
        int index=LIS.get(i);
        if(i!=length-1)
          System.out.print(index+" ");
        else
          System.out.println(index);
      }

    }
  }

  // Return indices of LIS
  static <T extends Comparable<? super T>> List<Integer> longestIncreasingSubsequence(List<T> seq) {

    if(seq.size()==0)
      return new ArrayList<Integer>();

    List<Integer> increasingSeq=new ArrayList<Integer>();
    List<Integer> parent=new ArrayList<Integer>();

    for(int i=0;i<seq.size();i++)
      parent.add(null);

    for(int i=0;i<seq.size();i++) {
      if(increasingSeq.size()==0 || seq.get(i).compareTo(seq.get(increasingSeq.get(increasingSeq.size() - 1)))>0) {
        if(increasingSeq.size()>0)
          parent.set(i, increasingSeq.get(increasingSeq.size()-1));
        increasingSeq.add(i);
      } else{
        int index=findNext(seq, increasingSeq, i);
        increasingSeq.set(index, i);
        if(index!=0)
          parent.set(i, increasingSeq.get(index-1));
      }
    }

    List<Integer> result=new ArrayList<Integer>();
    Integer curParent=increasingSeq.get(increasingSeq.size() - 1);
    while(curParent!=null) {
      result.add(curParent);
      curParent=parent.get(curParent);
    }

    Collections.reverse(result);
    return result;

  }

  // Returns index of the smallest element in subsequence as big as seq.get(elem)
  static <T extends Comparable<? super T>> int findNext(List<T> seq, List<Integer> subSeq, int elem) {
    int low=0;
    int high=subSeq.size()-1;
    while(high> low) {
      int mid=(high+low)/2;
      if(seq.get(subSeq.get(mid)).compareTo(seq.get(elem))<0)
        low=mid+1;
      else
        high=mid;
    }
    return high;
  }

}
