/*Good Coalition Suvinay
 *
 */
import java.io.*;
import java.util.*;

public class GoodCoalition
{
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args)throws IOException
	{
		int tests=Integer.valueOf(br.readLine());
		while(tests-- > 0)
		{
			//taking input
			int n=Integer.valueOf(br.readLine());
			int[] S=new int[n];
			int[] P=new int[n];
			int sum=0;
			for(int i=0; i<n;i++)
			{
				String split[]=br.readLine().split(" ");
				S[i]=Integer.valueOf(split[0]);
				P[i]=Integer.valueOf(split[1]);
				sum+=S[i];
			}

			double[] table=new double[151];
			table[0]=1;
			for(int i=0; i<n; i++)
				for(int j=150-S[i];j>=0;j--)
					table[j+S[i]]=Math.max(table[j+S[i]],table[j]*P[i]/100d);
			double res=0;
			for(int i=76;i<=150;i++)
				res=Math.max(res,table[i]);
			System.out.println(res *100.0);
		}
	}
}
