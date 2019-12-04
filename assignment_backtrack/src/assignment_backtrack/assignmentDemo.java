package assignment_backtrack;

import java.io.*;
import java.util.*;


public class assignmentDemo {
	 int n;           // 记录工作人员数和作业数
	 int work_cost[][]; // 记录第i号工作人员做第j号工作所需的时间
	 int res[][];     // 记录分配工作记录
	 int temp = 1;    // 记录分配次数
	 int isworked[];  // 记录第j号作业是否被做了
	 int bestCost =9999; //记录最佳作业分配时间
	 int currentCost;	//记录本次作业分配时间
     int assign[][];    //记录分配序列
	
	public static void main(String[] args) throws Exception {
		assignmentDemo order = new assignmentDemo();
		while(true){
			order.GetData();   //读取数据		
			order.backTack(0);	//回溯分配
			order.returnlowres();  //输出最佳分配时间，以及作业分布序列
		}
	}
	
	/**
	 * 读取测试数据
	 * @throws Exception 
	 */
	public void GetData() throws Exception{
		/*
		 * 选择测试文件序号
		 */
		System.out.println("共六组测试数据（1~6）,请输入数据编号：");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		/*
		 * 读取数据
		 */
		BufferedReader br = new BufferedReader(new FileReader("./src/assignment_backtrack/input_assgin04_0" + num + ".dat"));
		
		// 作业数以及工作人员数
		this.n = Integer.parseInt(br.readLine());
		System.out.println("*****************作业总数与工人总数*******************");
		System.out.println(this.n);
		
		
		// 初始化每个工人做每项作业的花费信息
		this.work_cost = new int[this.n][this.n];
		System.out.println("*****************每个工人对应完成每个作业所需花费*******************");
		for(int m = 0; m < this.n; m++){
			String str = br.readLine();
			for(int n = 0; n < str.split(" ").length; n++){
				this.work_cost[m][n] = Integer.parseInt(str.split(" ")[n]);
				System.out.print(this.work_cost[m][n] + " ");
			}
			System.out.println();
		}
		
		// 初始化分配工作表，默认都为0，表示没有分配
		this.res = new int[this.n][this.n];
		for(int m = 0; m < this.res.length; m++){
			for(int n = 0; n < this.res[m].length; n++){
				this.res[m][n] = 0;
			}
		}
		// 初始化作业被做记录，默认为0，表示没有被做
				this.isworked = new int[this.n];
				for(int m = 0; m < this.isworked.length; m++){
					this.isworked[m] = 0;
				}
		
	}
	
	/**
	 * 开始计算所有分配花费
	 * @param i
	 * @param count
	 */
	public void backTack(int i){
		if (i >= this.n) {     //说明已经遍历到叶子节点了,会产生一个当前最优解
			if(this.bestCost > this.currentCost) {
				bestCost = currentCost;    //把当前最小花费赋值给最优花费时间
				assign = new int[this.n][this.n];   //记录此次作业的分配情况
				for(int m = 0; m < this.res.length; m++){
					for(int n = 0; n < this.res[m].length; n++){
						if(this.res[m][n] ==1) {
							assign[m][n] =1;
						}
					}
				}
			}
		}
		//没有遍历到叶子节点
			for (int j = 0; j <this.n; j++) {//在第i层时的选择，i到n都可以选择放在这一层,也就是说第i个人完成i到n个作业都有可能
				    	if(this.isworked[j] == 0){   //判断本次作业中该项作业是否被选取
				    		this.isworked[j] = 1;  
				    		this.res[i][j] = 1;
				    		currentCost += this.work_cost[i][j];
				    		backTack(i+1);  
				    		currentCost -= this.work_cost[i][j];
				  	        this.isworked[j] = 0;
				  	        this.res[i][j] = 0;
				  	    }
				    } 
				
			}
	
	/**
	 * 输出最少花费
	 */
	public void returnlowres(){
		System.out.println("******************最佳分配时间********************");
		System.out.println(this.bestCost);
		System.out.println("本次作业分配详情：");
		for(int m = 0; m < this.n; m++){
			for(int n = 0; n < this.n; n++){
				System.out.print(assign[m][n] + " ");
			}
			System.out.println();
		}
	}
}

