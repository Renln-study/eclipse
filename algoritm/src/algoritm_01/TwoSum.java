package algoritm_01;

public class TwoSum {
 public static void main(String[] args) {
	 Solution so = new Solution();
	 int[] num = {3,3};
	 int[] result = new int[2];
	 result = so.twoSum(num, 6);
	 for(int i = 0;i<result.length;i++) {
		 System.out.println(result[i]);
	 }
	 
}
}


class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] b = new int[2];
        for(int i =0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
            	System.out.println(nums[i]+"+"+nums[j]);
                if((nums[i]+nums[j])== target){
                    b[0] = i;
                    b[1] = j; 
                    System.out.println("Êä³ö "+"i"+i+"------"+"j"+j);
                }
            }
        }
        return b;
    } 
}