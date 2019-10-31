package algoritm_01;

import java.awt.Label;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TwoSum_linklist {

	public static void main(String[] args) {
		ListNode result =  new ListNode(0);
	    ListNode a  = new ListNode(1);
	    ListNode a1  = new ListNode(4);
	    ListNode a2  = new ListNode(3);
	    ListNode b  = new ListNode(9);
	    ListNode b1  = new ListNode(9);
	    ListNode b2  = new ListNode(4);
	    ListNode c  = new ListNode(1);
	    ListNode result1 = c;
//	    System.out.println(result.next);
//	    a.next= a1;
//	    a1.next= a2;
	    b.next = b1;
//	    b1.next = b2;
	    Solution01 s = new Solution01();
	    result.next = s.addTwoNumbers(a, b);
	    result = result.next;
	    while(result!=null) {
	    	System.out.println(result.val);
	    	result=result.next;
	    }
		}
	}

	
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

class Solution01 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root  = new ListNode(0);
        ListNode c = root;
        //设置 返回节点，设置进位标志  Nflag,设置和变量
        int Nflag = 0;
        int sumTwo = 0;
//      int Current = 0;
        while (l1 != null ||l2 != null) {
        	if(l1!=null&&l2!=null) {
        		sumTwo =  l1.val + l2.val+Nflag;
        		ListNode sum_result = new ListNode(sumTwo%10);
        		c.next = sum_result;
        		Nflag = sumTwo>=10?1:0;
        	}else if(l1 == null &&l2 != null) {
        		sumTwo = l2.val+Nflag;
        		ListNode sum_result = new ListNode(sumTwo%10);
        		Nflag = sumTwo/10;
        		 c.next= sum_result;
        	}else if(l1 != null && l2 ==null) {
        		sumTwo = l1.val+Nflag;
        		ListNode sum_result = new ListNode(sumTwo%10);
        		Nflag = sumTwo/10;
        		 c.next = sum_result;
        	}
        	c=c.next;
        	if(l1!=null) l1 =l1.next;
        	if(l2!=null) l2 =l2.next;
        }
        if(Nflag == 1) {
    		ListNode sum_result = new ListNode(Nflag);
    		Nflag=0;
    		c.next = sum_result;
    		c= c.next;
    	}
        
        return root.next;
       
    }
}
