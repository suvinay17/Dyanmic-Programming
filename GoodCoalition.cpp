/* Suvinay Bothra
*/
#include <iostream>
#include <cstdio>
using namespace std;
int main(){
    int test,n;
    double optimum=0.0;
    cin>>test;
    while (test--){
        cin>>n;
        int p[n],s[n];
        for (int i=0;i<n;i++)
        {
        cin>>s[i]>>p[i];
        }
        double result[151];
        result[0]=1.0;
        for(int i=0;i<n;i++)
        {
            for(int j=150;j>=s[i];j--)
            {
                result[j]=max(result[j],p[i]/100*result[j-s[i]]);
            }
        }
        for(int j=76;j<151;j++)
        {
        optimum = max(optimum , result[j]);
        optimum = optimum * 100.0;
        printf("\n",optimum);
      }
    }
    return 0;
}
