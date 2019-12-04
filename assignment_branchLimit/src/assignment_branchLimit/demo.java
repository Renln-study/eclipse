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
	 * 初始化数据结构
	 * 定义作业节点
	 */
	public static class Assing_node{
		int deep ;    //深度
		int cost ;  // 当前搜索深度下已分配的作业所需时间
		Assing_node pre; //记录路径
		int worker; //记录工作员
		public Assing_node() {
			
		}
	}
	public static Assing_node minNode =  new Assing_node();
	
	public static void main(String[] args) {
		/*
		 * 定义队列
		 */
		Queue<Assing_node> queue =new LinkedList<Assing_node>(); 
		/*
		 * 初始化数据
		 */
		init();
		/*
		 * 计算上界
		 */
		maxValue = mostBound(work_cost);
		/*
		 * 计算下界
		 */
		int minValue = minBound(work_cost);
		/*
		 * 开始遍历作业
		 * 确定最小时间
		 */
		job_assiged(work_cost,assign,queue);
		System.out.println("*****************所需花费最小时间为*******************");
		System.out.println(minTime);
		System.out.println("\"*****************作业分配情况为*******************\"");
		while(minNode.pre != null) {
			System.out.println(" "+"工人 "+(minNode.worker+1)+" 完成作业："+(minNode.deep+1));
			minNode= minNode.pre;
		}
		System.out.println(""+"工人 "+(minNode.worker+1)+" 完成作业："+(minNode.deep+1));
		
	}



	public  static void job_assiged(int[][] work_cost,int[] assign, Queue<Assing_node> queue) {
//		判断当前树的深度
		for(int i =0;i<n;i++) {
			 int work_time = work_cost[0][i];
			 if(work_time <= maxValue) {
				 //节点初始化，放入优先级队列
				 Assing_node node = new Assing_node();
				 node.deep =0 ;
				 node.cost = work_time;
				 node.worker = i;
				 node.pre =null;
				 queue.add(node);
			 }
			}
//		判断当前树的深度
		 //节点初始化，放入优先级队列
		//声明一个节点用于循环使用，读取队列
		Assing_node temp = new Assing_node();
		while(true) {//循环遍历进行剪枝处理
			int length = queue.size();
			if(length == 0) {
				break;
			}
			//取出队列队首节点
			temp = queue.remove();
			int totaltime = temp.cost;
			assign = new int[n];
			for(int i=0;i<n;i++) {
				assign[i] = 0;
			}			
			//初始化工作人员清单
			//将节点的信息录入清单
			assign[temp.worker] = 1; 
			Assing_node preNode = temp;
			while(preNode.pre!=null) { 
				preNode =preNode.pre;
				assign[preNode.worker]=1;
			}
			//if然后判断是否叶子节点
			if(temp.deep == (n-1)){
				if(minTime == 0 ) {
					minTime = temp.cost;
					minNode  = temp;
				}else if( minTime>temp.cost ) {
					minTime = temp.cost;
					minNode = temp;
				}
			}else {
				//else 进行广度搜索
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
						//入队
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
		return bound;                 //返回这个最小边界值
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
			for(int j =0; j<n;j++) {         //j代表第j个人完成第j项工作
				if(work_cost[i][j]<minValue&&assign[j]==0) {
					minValue = work_cost[i][j];
					index = j;
				} 
			}
			assign[index]=1;
			bound += minValue;
		}
		return bound;//返回这个最大边界值
		}

	public static void init() {
		/*
		 * 读取本地文件
		 */
		System.out.println("共六组测试数据（1~6）,请输入数据编号：");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String filePath = "./src/assignment_branchLimit/input_assgin05_0"+num+".dat";
		try {
			FileReader  fi = new FileReader(filePath);
//			StringBuffer  strBuffer = new StringBuffer();
			BufferedReader br =  new BufferedReader(fi);
			n = Integer.parseInt(br.readLine());
			work_cost = new int[n][n];
			System.out.println("*****************作业总数与工人总数*******************");
			System.out.println(n);
			// 初始化每个工人做每项作业的花费信息
			System.out.println("*****************每个工人对应完成每个作业所需花费*******************");
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

