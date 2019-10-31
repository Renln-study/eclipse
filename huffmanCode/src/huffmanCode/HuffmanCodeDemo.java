package huffmanCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HuffmanCodeDemo {

    private BinaryNode root;// root of huffman tree
    private int nodes;// number of total nodes in huffman tree

    public class BinaryNode implements Comparable<BinaryNode> {
        int frequency;// ���ֵ�Ƶ��
        BinaryNode left;
        BinaryNode right;
        BinaryNode parent;

        public BinaryNode(int frequency, BinaryNode left, BinaryNode right,
                BinaryNode parent) {
            this.frequency = frequency;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public int compareTo(BinaryNode o) {
            return frequency - o.frequency;
        }

        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }
    }

    /**
     * 
     * @param roots
     *            initial root of each tree
     * @return root of huffman tree
     */
    public BinaryNode buildHuffmanTree(List<BinaryNode> roots) {
        if (roots.size() == 1)// ֻ��һ�����
            return roots.remove(0);
        PriorityQueue<BinaryNode> pq = new PriorityQueue<BinaryNode>(roots);//���ȼ����б�������Ҷ�ӽ��
        while (pq.size() != 1) {
            BinaryNode left = pq.remove();//Ƶ����С���ȳ�����
            BinaryNode right = pq.remove();
            BinaryNode parent = new BinaryNode(
                    left.frequency + right.frequency, left, right, null);//���츸���
            left.parent = parent;
            right.parent = parent;
            pq.add(parent);//�¹���õĸ������뵽���ȼ�������
        }
        return (root = pq.remove());
    }

    /**
     * ���ݸ�������Ȩֵ���� N �õ����ڵ����
     * 
     * @param frequency
     * @return
     */
    public List<BinaryNode> make_set(Integer[] frequency) {
        List<BinaryNode> nodeList = new ArrayList<HuffmanCodeDemo.BinaryNode>(
                frequency.length);
        for (Integer i : frequency) {
            nodeList.add(new BinaryNode(i, null, null, null));
        }
//        System.out.println("lenght:"+frequency.length);
        nodes = frequency.length << 1 - 1;// huffman ���н���������Ҷ�ӽ���������2��ȥ1
//        System.out.println("nodes:"+nodes);
        return nodeList;
    }

    /**
     * 
     * @param root
     *            huffman���ĸ����
     * @param nodeList
     *            huffman���е�����Ҷ�ӽ���б�
     * @return
     */
    public int huffman_cost(List<BinaryNode> nodeList) {
//    	System.out.println("111:"+nodeList.size());
        int cost = 0;
        int level;
        BinaryNode currentNode;
        for (BinaryNode binaryNode : nodeList) {
            level = 0;
            currentNode = binaryNode;
            while (currentNode != root) {
                currentNode = currentNode.parent;
                level++;
            }
            cost += level * binaryNode.frequency;
        }
        return cost;
    }

    public String huffmanEncoding(List<BinaryNode> nodeList) {
        StringBuilder sb = new StringBuilder();
        BinaryNode currentNode;
        for (BinaryNode binaryNode : nodeList) {
            currentNode = binaryNode;
            while (currentNode != root) {
                if (currentNode.isLeftChild())
                    sb.append("0");// ���ӱ���Ϊ0
                else if (currentNode.isRightChild())
                    sb.append("1");// �Һ��ӱ���Ϊ1
                currentNode = currentNode.parent;
            }
        }
        return sb.toString();
    }

    public Map<BinaryNode, String> huffmanDecoding(String encodeString) {
        BinaryNode currentNode = root;
        //�洢ÿ��Ҷ�ӽ���Ӧ�Ķ����Ʊ���
        Map<BinaryNode, String> node_Code = new HashMap<HuffmanCodeDemo.BinaryNode, String>();
        StringBuilder sb = new StringBuilder();//��ʱ����ÿ�����Ķ����Ʊ���
        for (int i = 0; i < encodeString.length(); i++) {
            
            char codeChar = encodeString.charAt(i);
            sb.append(codeChar);
            if (codeChar == '0')
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
            if (currentNode.left == null && currentNode.right == null)// ˵����Ҷ�ӽ��
            {
                node_Code.put(currentNode, sb.toString());
                sb.delete(0, sb.length());//��յ�ǰ���,Ϊ�洢��һ�����Ķ����Ʊ�����׼��
                currentNode = root;//��һ��Ҷ�ӽ��Ľ���,�ִӸ���ʼ
            }
        }
        return node_Code;
    }

    // for test purpose
    public static void main(String[] args) {
        Integer[] frequency = { 10, 15, 12, 3, 4, 13, 1 };//�������ĳ�ʼƵ��
        HuffmanCodeDemo hc = new HuffmanCodeDemo();
        List<BinaryNode> nodeList = hc.make_set(frequency);//����������ڵ���
        hc.buildHuffmanTree(nodeList);//����huffman tree
        int totalCost = hc.huffman_cost(nodeList);//����huffman tree�Ĵ���
        System.out.println(totalCost);
        String encodeStr = hc.huffmanEncoding(nodeList);//������Ҷ�ӽ�����huffman ����
        System.out.println("�������ַ���" + encodeStr);
        
        //���ݱ����ַ�������
        Map<BinaryNode, String> decodeMap = hc.huffmanDecoding(encodeStr);
        Set<Map.Entry<BinaryNode, String>> entrys = decodeMap.entrySet();
//        System.out.println(entrys.size());
        for (Map.Entry<BinaryNode, String> entry : entrys) {
            BinaryNode node = entry.getKey();
            String code = entry.getValue();
            System.out.println("Node's frequency=" + node.frequency + " : " + code);
        }
    }
}