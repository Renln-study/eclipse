package demoWhile;

public class while_return {
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++){
			if(i == 5)
//				return;//或者这里使用break;这两个关键字的效果是一样的
//				break;
				continue;
			System.out.println(i);
		}

	}
}