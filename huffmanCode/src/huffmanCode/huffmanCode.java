package huffmanCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Renln
 */
public class huffmanCode {

	//引入二叉树节点
	private static BinaryNode root;
	private int nodes;
	
	/*
	 * 建立Huffman树
	 * 获取数据
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//获取数据
		String filePath = "./src/huffmanCode/input_assign03_02.dat";
		char[] chArray =GetData(filePath);
//		for(char a : chArray) System.out.println(a);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(char a : chArray) {
			String str = a+"";
			if(map.get(str) == null) {
				map.put(str, 1);
			}else {
				map.put(str, map.get(str)+1);
			}
		}
		//根据出现频率排序
		map = sortByValueDescending(map);
//		 获取Map中的所有key
      Set<String> keySet = map.keySet();
//      //遍历存放所有key的Set集合
      Iterator<String> it =keySet.iterator();  
		//将map每个数据构造成各自节点
		 List<BinaryNode> nodeList = makeSet(map,keySet,it);
//		 System.out.println("长度是"+nodeList.size());
		//构建huffman树
		 buildHuffmanTree(nodeList);
		//构建huffman编码
		 Map<String,String> nodeMap = new HashMap<String, String>();
		 huffmanEncoding(nodeList, nodeMap);
//		 for (Map.Entry<String, String> entry : nodeMap.entrySet()) {
//	            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//	        }
		 //处理数据
		 StringBuilder resHuffman = new StringBuilder();
		 for(char ch : chArray) {
			 String str = ch+"";
			 if(nodeMap.containsKey(str)) {
				 resHuffman.append(nodeMap.get(str));
//				 resHuffman.append(",");
			 }
		 }
		 System.out.println("最终编码如下所示");
		 System.out.println(resHuffman.toString());
	}
	
	
	  public static void huffmanEncoding(List<BinaryNode> nodeList,Map<String,String> nodeMap ) {
	        StringBuilder huffmancode = new StringBuilder();
	        BinaryNode currentNode;
	        for (BinaryNode binaryNode : nodeList) {
	            currentNode = binaryNode;
//	            System.out.println("当前节点："+currentNode.data);
	            while (currentNode != root) {
	                if (currentNode.isLeftChild())
	                	huffmancode.append("0");// 左孩子编码为0
	                else if (currentNode.isRightChild())
	                	huffmancode.append("1");// 右孩子编码为1
	                currentNode = currentNode.parents;
	            }
	            nodeMap.put(binaryNode.data, huffmancode.reverse().toString());
	            huffmancode.delete(0, huffmancode.length());
	        }
	    }
	
	
	private static BinaryNode buildHuffmanTree(List<BinaryNode> nodeList) {
		if(nodeList.size()==0) return nodeList.remove(0);
		PriorityQueue<BinaryNode> pq = new PriorityQueue<BinaryNode>(new Comparator<BinaryNode>(){
			@Override
			//硬性规定     return 1 --- 表示返回倒序 ；return -1 --表示返回正序
			public int compare(BinaryNode o1, BinaryNode o2) {
				// TODO Auto-generated method stub
				return o1.weight-o2.weight;
			}
		});
		for(BinaryNode node :nodeList) pq.add(node);
        while (pq.size() != 1) {
            BinaryNode left = pq.remove();//频率最小的先出队列
//            System.out.print("left:"+left.data+"");
            BinaryNode right = pq.remove();
//            System.out.print("right:"+right.data);
            BinaryNode parent = new BinaryNode(
                    left.weight + right.weight, left, right, null,null);//构造父结点
            left.parents = parent;
            right.parents = parent;
            pq.add(parent);//新构造好的根结点插入到优先级队列中
//            System.out.println();
        }
        return (root =pq.remove());
	}
	private static List<BinaryNode> makeSet(Map<String, Integer> map, Set<String> keySet, Iterator<String> it) {
		List<BinaryNode> nodeList = new ArrayList<BinaryNode>();
		while(it.hasNext()){                         //利用了Iterator迭代器**
		   String key = it.next();
		   int weight = map.get(key);
//		   System.out.print(key);
		   nodeList.add(new BinaryNode(weight, null, null, null,key));
   }
		return nodeList;
	}
	//对文本中字符串出现顺序进行排序
	 //降序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


	private static char[] GetData(String filePath) {
		char[] chArray = null ;
		try {
			FileReader fread  = new FileReader(filePath);
			BufferedReader bread = new BufferedReader(fread);
			String str ;
			while((str = bread.readLine())!=null) {
				chArray = str.toCharArray();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chArray;
	}
	

}
