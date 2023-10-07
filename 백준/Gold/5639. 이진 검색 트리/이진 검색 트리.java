import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static class Node {
        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
        }

        private Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private void addNode(int n) {
            // 왼쪽 서브 트리에 있는 모든 노드의 키는 노드의 키보다 작다.
            if (n < this.data) {
                if (this.left == null) {
                    this.left = new Node(n);
                } else {
                    this.left.addNode(n);
                }
                // 오른쪽 서브 트리에 있는 모든 노드의 키는 노드의 키보다 크다.
            } else {
                if (this.right == null) {
                    this.right = new Node(n);
                } else {
                    this.right.addNode(n);
                }
            }
        }
    }

    private static void printPostOrder(Node node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.println(node.data);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node tree = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String lines = br.readLine();
            if (lines == null || lines.isEmpty()) {
                break;
            }

            int num = Integer.parseInt(lines);
            tree.addNode(num);
        }

        printPostOrder(tree);
    }
}
