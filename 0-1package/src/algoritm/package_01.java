
//��̬�滮��0-1������������Ž�

 
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
	//����v��ż�ֵ��w[i]��ŵ�i����Ʒ��������c��ʾ������������m[i][j]��¼�ӵ�i����Ʒ��ʼȡ��n��
	//��������Ϊj������µ����Ž�
	//m[1][c]��ֵ�������Ž�
	public static void knapsack(int []v,int []w,int c,int [][]m)
	{
		/*
		 * ���ȳ�ʼ������ ��ά����  ��ΪֻҪ���ֵ������Գ�ʼ������λ��Ϊ�㼴��
		 * ȷ���߽�
		 * �����ȡ��i����Ʒ����ô��ʱ��m[i][j] = m[i-1][j]
		 * ���ȡ�˵�i����Ʒ����ô��ʱ��m[i][j] = m[i-1][j-w[i]]+v[i]
		 */
	//������Ʒ����N
		int N = w.length;
		for(int i=1;i<N;i++) {
//			System.out.println("��"+i+"��");
			for(int j=0;j<=c;j++)
			{
				m[i][j] = m[i-1][j];
				if(j>=w[i]){ //�жϵ�i����Ʒ�Ƿ��ܹ��Ž�ȥ
					m[i][j] = Math.max(m[i-1][j],m[i-1][j-w[i]]+v[i]);
				}
//				System.out.print(m[i][j]);
			}
//			System.out.println();
		}
	}
	
	//��װ����Ʒ�Ľ������������x�����У�0��ʾû��ȡ��1��ʾװ�˸���Ʒ
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
		//�������ݸ���N�� ����C
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
		System.out.println("����ֵΪ"+m[N][C]);
		System.out.println("���·��ѡ��:");
		for(int k=1;k<x.length;k++) System.out.print(x[k]);
	}
 
}
	
	
         //����           
