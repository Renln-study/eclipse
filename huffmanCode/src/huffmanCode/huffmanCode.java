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

	//����������ڵ�
	private static BinaryNode root;
	private int nodes;
	
	/*
	 * ����Huffman��
	 * ��ȡ����
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��ȡ����
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
		//���ݳ���Ƶ������
		map = sortByValueDescending(map);
//		 ��ȡMap�е�����key
      Set<String> keySet = map.keySet();
//      //�����������key��Set����
      Iterator<String> it =keySet.iterator();  
		//��mapÿ�����ݹ���ɸ��Խڵ�
		 List<BinaryNode> nodeList = makeSet(map,keySet,it);
//		 System.out.println("������"+nodeList.size());
		//����huffman��
		 buildHuffmanTree(nodeList);
		//����huffman����
		 Map<String,String> nodeMap = new HashMap<String, String>();
		 huffmanEncoding(nodeList, nodeMap);
//		 for (Map.Entry<String, String> entry : nodeMap.entrySet()) {
//	            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//	        }
		 //��������
		 StringBuilder resHuffman = new StringBuilder();
		 for(char ch : chArray) {
			 String str = ch+"";
			 if(nodeMap.containsKey(str)) {
				 resHuffman.append(nodeMap.get(str));
//				 resHuffman.append(",");
			 }
		 }
		 System.out.println("���ձ���������ʾ");
		 System.out.println(resHuffman.toString());
	}
	
	
	  public static void huffmanEncoding(List<BinaryNode> nodeList,Map<String,String> nodeMap ) {
	        StringBuilder huffmancode = new StringBuilder();
	        BinaryNode currentNode;
	        for (BinaryNode binaryNode : nodeList) {
	            currentNode = binaryNode;
//	            System.out.println("��ǰ�ڵ㣺"+currentNode.data);
	            while (currentNode != root) {
	                if (currentNode.isLeftChild())
	                	huffmancode.append("0");// ���ӱ���Ϊ0
	                else if (currentNode.isRightChild())
	                	huffmancode.append("1");// �Һ��ӱ���Ϊ1
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
			//Ӳ�Թ涨     return 1 --- ��ʾ���ص��� ��return -1 --��ʾ��������
			public int compare(BinaryNode o1, BinaryNode o2) {
				// TODO Auto-generated method stub
				return o1.weight-o2.weight;
			}
		});
		for(BinaryNode node :nodeList) pq.add(node);
        while (pq.size() != 1) {
            BinaryNode left = pq.remove();//Ƶ����С���ȳ�����
//            System.out.print("left:"+left.data+"");
            BinaryNode right = pq.remove();
//            System.out.print("right:"+right.data);
            BinaryNode parent = new BinaryNode(
                    left.weight + right.weight, left, right, null,null);//���츸���
            left.parents = parent;
            right.parents = parent;
            pq.add(parent);//�¹���õĸ������뵽���ȼ�������
//            System.out.println();
        }
        return (root =pq.remove());
	}
	private static List<BinaryNode> makeSet(Map<String, Integer> map, Set<String> keySet, Iterator<String> it) {
		List<BinaryNode> nodeList = new ArrayList<BinaryNode>();
		while(it.hasNext()){                         //������Iterator������**
		   String key = it.next();
		   int weight = map.get(key);
//		   System.out.print(key);
		   nodeList.add(new BinaryNode(weight, null, null, null,key));
   }
		return nodeList;
	}
	//���ı����ַ�������˳���������
	 //��������
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
