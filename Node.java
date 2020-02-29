public class Node{
    int data;
    Node parent = null;
    Node right;
    Node left;
	//public Object parent;

    public Node(int data){
        this.data = data;
        this.right = null;
        this.left = null;
    }

}