package assignment_branchLimit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import javax.xml.transform.Templates;

public class demo {
	public static int n;
	public static int work_cost[][];
	public static int assign[];
	public static int maxValue;
	public static int minTime =0;

	/*
	 * ��ʼ�����ݽṹ
	 * ������ҵ�ڵ�
	 */
	public static class Assing_node{
		int deep ;    //���
		int cost ;  // ��ǰ����������ѷ������ҵ����ʱ��
		Assing_node pre; //��¼·��
		int worker; //��¼����Ա
		public Assing_node() {
			
		}
	}
	public static Assing_node minNode =  new Assing_node();
	
	public static void main(String[] args) {
		/*
		 * �������
		 */
		Queue<Assing_node> queue =new LinkedList<Assing_node>(); 
		/*
		 * ��ʼ������
		 */
		init();
		/*
		 * �����Ͻ�
		 */
		maxValue = mostBound(work_cost);
		/*
		 * �����½�
		 */
		int minValue = minBound(work_cost);
		/*
		 * ��ʼ������ҵ
		 * ȷ����Сʱ��
		 */
		job_assiged(work_cost,assign,queue);
		System.out.println("*****************���軨����Сʱ��Ϊ*******************");
		System.out.println(minTime);
		System.out.println("\"*****************��ҵ�������Ϊ*******************\"");
		while(minNode.pre != null) {
			System.out.println(" "+"���� "+(minNode.worker+1)+" �����ҵ��"+(minNode.deep+1));
			minNode= minNode.pre;
		}
		System.out.println(""+"���� "+(minNode.worker+1)+" �����ҵ��"+(minNode.deep+1));
		
	}



	public  static void job_assiged(int[][] work_cost,int[] assign, Queue<Assing_node> queue) {
//		�жϵ�ǰ�������
		for(int i =0;i<n;i++) {
			 int work_time = work_cost[0][i];
			 if(work_time <= maxValue) {
				 //�ڵ��ʼ�����������ȼ�����
				 Assing_node node = new Assing_node();
				 node.deep =0 ;
				 node.cost = work_time;
				 node.worker = i;
				 node.pre =null;
				 queue.add(node);
			 }
			}
//		�жϵ�ǰ�������
		 //�ڵ��ʼ�����������ȼ�����
		//����һ���ڵ�����ѭ��ʹ�ã���ȡ����
		Assing_node temp = new Assing_node();
		while(true) {//ѭ���������м�֦����
			int length = queue.size();
			if(length == 0) {
				break;
			}
			//ȡ�����ж��׽ڵ�
			temp = queue.remove();
			int totaltime = temp.cost;
			assign = new int[n];
			for(int i=0;i<n;i++) {
				assign[i] = 0;
			}			
			//��ʼ��������Ա�嵥
			//���ڵ����Ϣ¼���嵥
			assign[temp.worker] = 1; 
			Assing_node preNode = temp;
			while(preNode.pre!=null) { 
				preNode =preNode.pre;
				assign[preNode.worker]=1;
			}
			//ifȻ���ж��Ƿ�Ҷ�ӽڵ�
			if(temp.deep == (n-1)){
				if(minTime == 0 ) {
					minTime = temp.cost;
					minNode  = temp;
				}else if( minTime>temp.cost ) {
					minTime = temp.cost;
					minNode = temp;
				}
			}else {
				//else ���й������
				int present_deep = temp.deep+1;
				int[] children = new int[n];
				for(int i=0; i<n;i++) {
					children[i] = work_cost[present_deep][i];
				}
				for(int k = 0;k<n;k++) {
					int sum = children[k]+totaltime;
					if( sum <= maxValue && assign[k] ==0) {
						Assing_node new_node = new Assing_node();
						new_node.deep =  present_deep;
						new_node.cost = children[k] +totaltime;
						new_node.worker = k;
						new_node.pre = temp;
						//���
						queue.add(new_node);
					}
				}
			}
		  }
		}




	public static int minBound(int[][] work_cost) {
		int bound=0;
		for(int i=0;i<n;i++){
			int min=work_cost[i][0];
			for(int j=1;j<work_cost[i].length;j++){
				if(min>work_cost[i][j]){
					min=work_cost[i][j];
					}
				}
			bound+=min;
			}
		return bound;                 //���������С�߽�ֵ
	}

	public static int mostBound(int[][] work_cost){
		int bound=0;
		assign = new int [n];
		for(int i =0;i<n;i++) {
			assign[i] =0;
		}
		int index =0;
		for(int i=0;i<n;i++) {
			int minValue =  500;
			for(int j =0; j<n;j++) {         //j�����j������ɵ�j���
				if(work_cost[i][j]<minValue&&assign[j]==0) {
					minValue = work_cost[i][j];
					index = j;
				} 
			}
			assign[index]=1;
			bound += minValue;
		}
		return bound;//����������߽�ֵ
		}

	public static void init() {
		/*
		 * ��ȡ�����ļ�
		 */
		System.out.println("������������ݣ�1~6��,���������ݱ�ţ�");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String filePath = "./src/assignment_branchLimit/input_assgin05_0"+num+".dat";
		try {
			FileReader  fi = new FileReader(filePath);
//			StringBuffer  strBuffer = new StringBuffer();
			BufferedReader br =  new BufferedReader(fi);
			n = Integer.parseInt(br.readLine());
			work_cost = new int[n][n];
			System.out.println("*****************��ҵ�����빤������*******************");
			System.out.println(n);
			// ��ʼ��ÿ��������ÿ����ҵ�Ļ�����Ϣ
			System.out.println("*****************ÿ�����˶�Ӧ���ÿ����ҵ���軨��*******************");
			for(int m = 0; m <n; m++){
				String str = br.readLine();
				for(int n = 0; n < str.split(" ").length; n++){
					work_cost[m][n] = Integer.parseInt(str.split(" ")[n]);
					System.out.print(work_cost[m][n] + " ");
			
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

