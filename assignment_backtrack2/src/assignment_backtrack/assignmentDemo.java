package assignment_backtrack;

import java.io.*;
import java.util.*;


public class assignmentDemo {
	public int n;           // ��¼������Ա������ҵ��
	public int isworked[];  // ��¼��j����ҵ�Ƿ�����
	public int expense[][]; // ��¼��i�Ź�����Ա����j�Ź��������ʱ��
	public int res[][];     // ��¼���乤����¼
	public ArrayList<Integer> counts;  // ��¼ÿ�η�����ܻ���
	public int temp = 1;    // ��¼�������
	
	public static void main(String[] args) throws Exception {
		assignmentDemo demo = new assignmentDemo();
		while(true){
			demo.readdata();
			int count = 0;
			System.out.println("************������************");
			demo.work(0, count);
			demo.returnlowres();
		}
	}
	
	/**
	 * ��ȡ��������
	 * @throws Exception 
	 */
	public void readdata() throws Exception{
		/*
		 * ѡ������ļ����
		 */
		System.out.println("demo1"+"������������ݣ�1~6��,���������ݱ�ţ�");
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
		
		// ��ʼ����ҵ������¼��Ĭ��Ϊ0����ʾû�б���
		this.isworked = new int[this.n];
		for(int m = 0; m < this.isworked.length; m++){
			this.isworked[m] = 0;
		}
		
		// ��ʼ��ÿ��������ÿ����ҵ�Ļ�����Ϣ
		this.expense = new int[this.n][this.n];
		System.out.println("*****************ÿ�����˶�Ӧ���ÿ����ҵ���軨��*******************");
		for(int m = 0; m < this.n; m++){
			String str = br.readLine();
			for(int n = 0; n < str.split(" ").length; n++){
				this.expense[m][n] = Integer.parseInt(str.split(" ")[n]);
				System.out.print(this.expense[m][n] + " ");
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
		
		// �������Ѽ�¼
		this.counts = new ArrayList<Integer>();
	}
	
	/**
	 * ��ʼ�������з��仨��
	 * @param i
	 * @param count
	 */
	public void work(int i, int count){
		// ����
		if(i>=this.n){
			// �����η������Ļ�����ӵ����Ѽ���
			this.counts.add(count);
			System.out.println("��" + this.temp + "�η��䣬�˴λ���Ϊ��" +count);
			System.out.println("���η������飺");
			for(int m = 0; m < this.res.length; m++){
				for(int n = 0; n < this.res[m].length; n++){
					System.out.print(this.res[m][n] + " ");
				}
				System.out.println();
			}
			return ;
		}
		
		// �����ǰ����û�����ͷ������ǰԱ��
	    for(int j = 0; j < this.n; j++){
	    	if(this.isworked[j] == 0){  
	    		this.isworked[j] = 1;  
	    		this.res[i][j] = 1;
	  	        work(i+1, count + this.expense[i][j]);  
	  	        this.isworked[j] = 0;
	  	        this.res[i][j] = 0;
	  	    }
	    } 
	}
	
	/**
	 * ������ٻ���
	 */
	public void returnlowres(){
		Collections.sort(this.counts);
		System.out.println("******************���ٻ���********************");
		System.out.println(this.counts.get(0));
	}
}

