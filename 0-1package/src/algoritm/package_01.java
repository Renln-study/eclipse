
//动态规划求0-1背包问题的最优解

 
package algoritm;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Renln
 */
public class package_01 {
	//数组v存放价值，w[i]存放第i个物品的重量，c表示背包的容量，m[i][j]记录从第i个物品开始取到n，
	//背包容量为j的情况下的最优解
	//m[1][c]的值便是最优解
	public static void knapsack(int []v,int []w,int c,int [][]m)
	{
		/*
		 * 首先初始化数组 二维数组  因为只要求价值最大，所以初始化所有位置为零即可
		 * 确定边界
		 * 如果不取第i个物品，那么此时的m[i][j] = m[i-1][j]
		 * 如果取了第i个物品，那么此时的m[i][j] = m[i-1][j-w[i]]+v[i]
		 */
	//声明物品个数N
		int N = w.length;
		for(int i=1;i<N;i++) {
//			System.out.println("第"+i+"行");
			for(int j=0;j<=c;j++)
			{
				m[i][j] = m[i-1][j];
				if(j>=w[i]){ //判断第i个物品是否能够放进去
					m[i][j] = Math.max(m[i-1][j],m[i-1][j-w[i]]+v[i]);
				}
//				System.out.print(m[i][j]);
			}
//			System.out.println();
		}
	}
	
	//求装的物品的解的向量表，存在x数组中，0表示没有取，1表示装了该物品
	public static void traceback(int [][]m,int []w,int c,int[]x)
	{
		int n = w.length-1;
		    for(int i=n;i>1;i--)  
		    {  
		        if(m[i][c]==m[i-1][c])  
		            x[i]=0;  
		        else  
		        {  
		            x[i]=1;  
		            c-=w[i];  
		        }  
		    }  
		    x[1]=(m[1][c]>0)?1:0;  
	}	
	
	/**
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String args[]) throws NumberFormatException, IOException
	{
//		int w[]={0,2,2,6,4,5};
//		int v[]={0,6,3,5,6,4};
		Scanner sc = new Scanner(System.in);
		String file = ".//src/algoritm/input_assign02_05.dat";
		FileReader readFile = new FileReader(file);
		BufferedReader br  = new BufferedReader(readFile);
		String readData = null;
		readData = br.readLine();
//		System.out.println(readData);
		//定义数据个数N， 容量C
		int N = Integer.parseInt(readData.split(",")[0]);
		int C = Integer.parseInt(readData.split(",")[1]);
		int m[][]=new int[N+1][C+1];
		int[] x = new int[N+1];
		int index =0;
		int[] v = new int[N+1];
		int[] w = new int[N+1];
		w[index]=v[index]=0;
		while((readData= br.readLine()) != null){
			String[] data = readData.split(",");
			++index;
			w[index] = Integer.parseInt(data[0]);
			v[index] = Integer.parseInt(data[1]);
			}
		knapsack(v,w,C, m);
		traceback(m,w,C,x);	
		System.out.println("最大价值为"+m[N][C]);
		System.out.println("输出路径选择:");
		for(int k=1;k<x.length;k++) System.out.print(x[k]);
	}
 
}
	
	
         //测试           
