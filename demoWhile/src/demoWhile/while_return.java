package demoWhile;

public class while_return {
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++){
			if(i == 5)
//				return;//��������ʹ��break;�������ؼ��ֵ�Ч����һ����
//				break;
				continue;
			System.out.println(i);
		}

	}
}