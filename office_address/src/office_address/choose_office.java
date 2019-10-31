	/*
		 * 1.获取输入数据
		 * 2.对数据进行处理{
		 * 	 （1）采用带权中位数 
		 * 	 （2）二维邮局选址问题: ---
		 * 			对于所有位置来说 皆是由x坐标和y坐标组成，分成两个方向进行处理 ，
		 * 			分别求取带权中位数
		 * 3.输出数据	
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
	
//	 获取输入数据
	public static void InputData() throws NumberFormatException, IOException{
		//判断是否继续测试数据 ----flag
		boolean flag = true;
		while(flag) {
			/*
			 * 选择测试文件序号
			 */
			System.out.println("共五组测试数据（1~5）,请输入数据编号:");
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();
			/*
			 * 定义IO对象，文件名，获取数组长度
			 */
			String str = null;
			FileReader file_reader = new FileReader("./src/office_address/input_assign01_0"+num+".dat");
			BufferedReader br = new BufferedReader(file_reader);
			int length =Integer.parseInt(br.readLine());
			/*
			 * 定义存放并初始化x，y坐标, 权值的数组
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
			 * 管理数据，便于对下一步数据操作定义
			 */
			init_data(xlist,ylist,xlistW,ylistW);
			System.out.println("是否继续测试数据,yes继续,no退出程序:"+"\n");
			sc = new Scanner(System.in);
			String next = sc.next();
			if(next.equals("no"))
				flag = false;
		}
		
	}
	
//初始化数据
public static void init_data(int[] xlist, int[] ylist, double[] xlistW, double[] ylistW) {
	int length =xlist.length;
	//对数据实现排序
	quickSort(xlist,xlistW,0,length-1);
	quickSort(ylist,ylistW,0,length-1);
//	//输出排序后的所有数据
//	for(int i=0;i<xlist.length;i++) {
//		System.out.println("横坐标是： "+xlist[i]+"++++++"+xlistW[i]+"    ");
//		System.out.println("纵坐标是："+ylist[i]+"+++++++"+ylistW[i]+"\n");
//		
//	}
	//获取带权中位数
	int X = get_Mheight(xlist,xlistW);
	int Y = get_Mheight(ylist,ylistW);
	System.out.println("邮局坐标:"+"("+X+","+Y+")");
	}

//实现快速排序
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

//获取带权中位数
public static int get_Mheight(int[] list, double[] Weight) {
	//计算总权值
	double sumWeight = 0.0;
	for(int i =0;i<Weight.length;i++) {
		 sumWeight += Weight[i];
	}
	/*
	 * 计算带权中位数
	 * suml为左边权值逐次累加，sumR为右边权值累加
	 */
	double sumL=0.0;
	double sumR=0.0;
	for(int i=0;i<list.length;i++) {
		//左边权值为sum+Weight[i],右边为sumWeight-sumL
		sumR =sumWeight-sumL;
		sumL += Weight[i];
		if(sumL>= (sumWeight/2)&&sumR>=(sumWeight/2)){
			return list[i];
		}
	}
	return 0;
}

}


