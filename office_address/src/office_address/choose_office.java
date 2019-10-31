	/*
		 * 1.��ȡ��������
		 * 2.�����ݽ��д���{
		 * 	 ��1�����ô�Ȩ��λ�� 
		 * 	 ��2����ά�ʾ�ѡַ����: ---
		 * 			��������λ����˵ ������x�����y������ɣ��ֳ�����������д��� ��
		 * 			�ֱ���ȡ��Ȩ��λ��
		 * 3.�������	
		 * }
		 */
package office_address;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class choose_office {

	public static void main(String[] args) throws Exception{
		InputData();
//		System.out.println();
	}
	
//	 ��ȡ��������
	public static void InputData() throws NumberFormatException, IOException{
		//�ж��Ƿ������������ ----flag
		boolean flag = true;
		while(flag) {
			/*
			 * ѡ������ļ����
			 */
			System.out.println("������������ݣ�1~5��,���������ݱ��:");
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();
			/*
			 * ����IO�����ļ�������ȡ���鳤��
			 */
			String str = null;
			FileReader file_reader = new FileReader("./src/office_address/input_assign01_0"+num+".dat");
			BufferedReader br = new BufferedReader(file_reader);
			int length =Integer.parseInt(br.readLine());
			/*
			 * �����Ų���ʼ��x��y����, Ȩֵ������
			 */
			int[] xlist = new int[length];
			int[] ylist = new int[length];
			double[] xlistW = new double[length];
			double[] ylistW = new double[length];
			int index =0 ;
			while((str= br.readLine()) != null){
				String[] data = str.split(",");
				xlist[index] = Integer.parseInt(data[0]);
				ylist[index] = Integer.parseInt(data[1]);
				xlistW[index] = Integer.parseInt(data[2]);
				ylistW[index] = Integer.parseInt(data[2]);
				index++;
				}
			/*
			 * �������ݣ����ڶ���һ�����ݲ�������
			 */
			init_data(xlist,ylist,xlistW,ylistW);
			System.out.println("�Ƿ������������,yes����,no�˳�����:"+"\n");
			sc = new Scanner(System.in);
			String next = sc.next();
			if(next.equals("no"))
				flag = false;
		}
		
	}
	
//��ʼ������
public static void init_data(int[] xlist, int[] ylist, double[] xlistW, double[] ylistW) {
	int length =xlist.length;
	//������ʵ������
	quickSort(xlist,xlistW,0,length-1);
	quickSort(ylist,ylistW,0,length-1);
//	//�����������������
//	for(int i=0;i<xlist.length;i++) {
//		System.out.println("�������ǣ� "+xlist[i]+"++++++"+xlistW[i]+"    ");
//		System.out.println("�������ǣ�"+ylist[i]+"+++++++"+ylistW[i]+"\n");
//		
//	}
	//��ȡ��Ȩ��λ��
	int X = get_Mheight(xlist,xlistW);
	int Y = get_Mheight(ylist,ylistW);
	System.out.println("�ʾ�����:"+"("+X+","+Y+")");
	}

//ʵ�ֿ�������
public static void quickSort(int[] attr, double[] weight, int low, int height){
	int temp = 0;
	double temp1 =0;
	int i = low;
	int j = height;

	if(low < height){
		temp = attr[low];
		temp1 = weight[low];

		while(i != j){
			while(j > i && attr[j] >= temp){
				--j;
			}
			if(i < j){
				attr[i] = attr[j];
				weight[i] = weight[j];
				++i;
			}
			while(i < j && attr[i] < temp){
				++i;
			}
			if(i < j){
				attr[j] = attr[i];
				weight[j] = weight[i];
				--j;
			}
		}

		attr[i] = temp;
		weight[i] = temp1;
		quickSort(attr, weight, low, i - 1);
		quickSort(attr, weight, i + 1, height);
	}
}

//��ȡ��Ȩ��λ��
public static int get_Mheight(int[] list, double[] Weight) {
	//������Ȩֵ
	double sumWeight = 0.0;
	for(int i =0;i<Weight.length;i++) {
		 sumWeight += Weight[i];
	}
	/*
	 * �����Ȩ��λ��
	 * sumlΪ���Ȩֵ����ۼӣ�sumRΪ�ұ�Ȩֵ�ۼ�
	 */
	double sumL=0.0;
	double sumR=0.0;
	for(int i=0;i<list.length;i++) {
		//���ȨֵΪsum+Weight[i],�ұ�ΪsumWeight-sumL
		sumR =sumWeight-sumL;
		sumL += Weight[i];
		if(sumL>= (sumWeight/2)&&sumR>=(sumWeight/2)){
			return list[i];
		}
	}
	return 0;
}

}


