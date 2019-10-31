package huffmanCode;
 
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
 
public class demo {
 
	public static class Node<E> {
		E data;
		double weight;
		Node leftChild;
		Node rightChild;
 
		public Node(E data, double weight) {
			super();
			this.data = data;
			this.weight = weight;
		}
 
		public String toString() {
			return "Node[data=" + data + ", weight=" + weight + "]";
		}
	}
 
	public static void main(String[] args) {
		List<Node> nodes = new ArrayList<Node>();
 
		nodes.add(new Node("A", 40.0));
		nodes.add(new Node("B", 8.0));
		nodes.add(new Node("C", 10.0));
		nodes.add(new Node("D", 30.0));
		nodes.add(new Node("E", 10.0));
		nodes.add(new Node("F", 2.0));
		
		Node root = demo.createTree(nodes);
		
		System.out.println(breadthFirst(root));
 
	}
 
	/**
	 * �����������
	 * 
	 * @param nodes
	 *            �ڵ㼯��
	 * @return ��������Ĺ��������ĸ��ڵ�
	 */
	private static Node createTree(List<Node> nodes) {
		// ֻҪnodes�����л���2�����ϵĽڵ�
		while (nodes.size() > 1) {
			quickSort(nodes);
			//��ȡȨֵ��С�������ڵ�
			Node left = nodes.get(nodes.size()-1);
			Node right = nodes.get(nodes.size()-2);
			
			//�����½ڵ㣬�½ڵ��ȨֵΪ�����ӽڵ��Ȩֵ֮��
			Node parent = new Node(null, left.weight + right.weight);
			
			//���½ڵ���Ϊ����Ȩֵ��С�ڵ�ĸ��ڵ�
			parent.leftChild = left;
			parent.rightChild = right;
			
			//ɾ��Ȩֵ��С�������ڵ�
			nodes.remove(nodes.size()-1);
			nodes.remove(nodes.size()-1);
			
			//���½ڵ���뵽������
			nodes.add(parent);
		}
		
		return nodes.get(0);
	}
 
	/**
	 * ��ָ�������е�i��j��������Ԫ�ؽ���
	 * 
	 * @param nodes
	 * @param i
	 * @param j
	 */
	private static void swap(List<Node> nodes, int i, int j) {
		Node tmp;
		tmp = nodes.get(i);
		nodes.set(i, nodes.get(j));
		nodes.set(j, tmp);
	}
 
	/**
	 * ʵ�ֿ��������㷨�����ڶԽڵ��������
	 * 
	 * @param nodes
	 * @param start
	 * @param end
	 */
	private static void subSort(List<Node> nodes, int start, int end) {
		if (start < end) {
			// �Ե�һ��Ԫ����Ϊ�ֽ�ֵ
			Node base = nodes.get(start);
			// i������������������ڷֽ�ֵ��Ԫ�ص�����
			int i = start;
			// j���ұ߿�ʼ����������С�ڷֽ�ֵ��Ԫ�ص�����
			int j = end + 1;
			while (true) {
				// �ҵ����ڷֽ�ֵ��Ԫ�ص�����������i�Ѿ�����end��
				while (i < end && nodes.get(++i).weight >= base.weight)
					;
				// �ҵ�С�ڷֽ�ֵ��Ԫ�ص�����������j�Ѿ�����start��
				while (j > start && nodes.get(--j).weight <= base.weight)
					;
 
				if (i < j) {
					swap(nodes, i, j);
				} else {
					break;
				}
			}
 
			swap(nodes, start, j);
 
			//�ݹ����������
			subSort(nodes, start, j - 1);
			//�ݹ��ұ�������
			subSort(nodes, j + 1, end);
		}
	}
	
	public static void quickSort(List<Node> nodes){
		subSort(nodes, 0, nodes.size()-1);
	}
	
	//������ȱ���
	public static List<Node> breadthFirst(Node root){
		Queue<Node> queue = new ArrayDeque<Node>();
		List<Node> list = new ArrayList<Node>();
		
		if(root!=null){
			//����Ԫ�ؼ��롰���С�
			queue.offer(root);
		}
		
		while(!queue.isEmpty()){
			//���ö��еġ���β��Ԫ�ؼ��뵽list��
			list.add(queue.peek());
			Node p = queue.poll();
			
			//������ӽڵ㲻Ϊnull���������뵽����
			if(p.leftChild != null){
				queue.offer(p.leftChild);
			}
			
			//������ӽڵ㲻Ϊnull���������뵽����
			if(p.rightChild != null){
				queue.offer(p.rightChild);
			}
		}
		
		return list;
	}
}