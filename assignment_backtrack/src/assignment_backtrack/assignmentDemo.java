package assignment_backtrack;

import java.io.*;
import java.util.*;


public class assignmentDemo {
	 int n;           // ��¼������Ա������ҵ��
	 int work_cost[][]; // ��¼��i�Ź�����Ա����j�Ź��������ʱ��
	 int res[][];     // ��¼���乤����¼
	 int temp = 1;    // ��¼�������
	 int isworked[];  // ��¼��j����ҵ�Ƿ�����
	 int bestCost =9999; //��¼�����ҵ����ʱ��
	 int currentCost;	//��¼������ҵ����ʱ��
     int assign[][];    //��¼��������
	
	public static void main(String[] args) throws Exception {
		assignmentDemo order = new assignmentDemo();
		while(true){
			order.GetData();   //��ȡ����		
			order.backTack(0);	//���ݷ���
			order.returnlowres();  //�����ѷ���ʱ�䣬�Լ���ҵ�ֲ�����
		}
	}
	
	/**
	 * ��ȡ��������
	 * @throws Exception 
	 */
	public void GetData() throws Exception{
		/*
		 * ѡ������ļ����
		 */
		System.out.println("������������ݣ�1~6��,���������ݱ�ţ�");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		/*
		 * ��ȡ����
		 */
		BufferedReader br = new BufferedReader(new FileReader("./src/assignment_backtrack/input_assgin04_0" + num + ".dat"));
		
		// ��ҵ���Լ�������Ա��
		this.n = Integer.parseInt(br.readLine());
		System.out.println("*****************��ҵ�����빤������*******************");
		System.out.println(this.n);
		
		
		// ��ʼ��ÿ��������ÿ����ҵ�Ļ�����Ϣ
		this.work_cost = new int[this.n][this.n];
		System.out.println("*****************ÿ�����˶�Ӧ���ÿ����ҵ���軨��*******************");
		for(int m = 0; m < this.n; m++){
			String str = br.readLine();
			for(int n = 0; n < str.split(" ").length; n++){
				this.work_cost[m][n] = Integer.parseInt(str.split(" ")[n]);
				System.out.print(this.work_cost[m][n] + " ");
			}
			System.out.println();
		}
		
		// ��ʼ�����乤����Ĭ�϶�Ϊ0����ʾû�з���
		this.res = new int[this.n][this.n];
		for(int m = 0; m < this.res.length; m++){
			for(int n = 0; n < this.res[m].length; n++){
				this.res[m][n] = 0;
			}
		}
		// ��ʼ����ҵ������¼��Ĭ��Ϊ0����ʾû�б���
				this.isworked = new int[this.n];
				for(int m = 0; m < this.isworked.length; m++){
					this.isworked[m] = 0;
				}
		
	}
	
	/**
	 * ��ʼ�������з��仨��
	 * @param i
	 * @param count
	 */
	public void backTack(int i){
		if (i >= this.n) {     //˵���Ѿ�������Ҷ�ӽڵ���,�����һ����ǰ���Ž�
			if(this.bestCost > this.currentCost) {
				bestCost = currentCost;    //�ѵ�ǰ��С���Ѹ�ֵ�����Ż���ʱ��
				assign = new int[this.n][this.n];   //��¼�˴���ҵ�ķ������
				for(int m = 0; m < this.res.length; m++){
					for(int n = 0; n < this.res[m].length; n++){
						if(this.res[m][n] ==1) {
							assign[m][n] =1;
						}
					}
				}
			}
		}
		//û�б�����Ҷ�ӽڵ�
			for (int j = 0; j <this.n; j++) {//�ڵ�i��ʱ��ѡ��i��n������ѡ�������һ��,Ҳ����˵��i�������i��n����ҵ���п���
				    	if(this.isworked[j] == 0){   //�жϱ�����ҵ�и�����ҵ�Ƿ�ѡȡ
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
	 * ������ٻ���
	 */
	public void returnlowres(){
		System.out.println("******************��ѷ���ʱ��********************");
		System.out.println(this.bestCost);
		System.out.println("������ҵ�������飺");
		for(int m = 0; m < this.n; m++){
			for(int n = 0; n < this.n; n++){
				System.out.print(assign[m][n] + " ");
			}
			System.out.println();
		}
	}
}

