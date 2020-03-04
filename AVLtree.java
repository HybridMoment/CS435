import java.util.*; 

public class AVLtree{
    Node root;

    AVLtree(int data){
        this.root = new Node(data);
    }

    //Recursive methods for AVL operations
    public void insertRec(int data){
        this.root = insertRecHelper(this.root, data);
    }
    private Node insertRecHelper(Node root, int data){
        //base case
        if(root == null){
            root = new Node(data);
            return root;
        }
        //case 1: recurse left sub tree
        if(data < root.data){
            //go left
            root.left = insertRecHelper(root.left, data);
            root.left.parent = root;
            if(root.right == null){
                //inserted into new level, update heights and balance factors
                updateHeightAndBFInsertion(root);
            }
        }
        //case 2: recurse right sub tree 
        if(data > root.data){
            //go right
            root.right = insertRecHelper(root.right, data);
            root.right.parent = root;
            // just inserted, now we must correct heigh and balance factor, and perform rotations if needed
            if(root.left == null){
                //node inserted to new level, update height and balance factor of all parents
                updateHeightAndBFInsertion(root);
            }

        }
    
        return root;
    }
    
    private void updateHeightAndBFInsertion(Node root){

        while(root != null){
            root.height = calculateHeight(root);
            root.balanceFactor = calculateBF(root);
            if(root.balanceFactor >= 2 || root.balanceFactor <= -2){
                rotateNode(root);
            }
            root = root.parent;
        }

    }

    private int calculateHeight(Node root){

        if(root.left != null && root.right == null){
            return ( 1 + root.left.height);
        }else if(root.left == null && root.right != null){
            return ( 1 + root.right.height);
        }else{
            return( 1 + Math.max(root.left.height, root.right.height));
        }
        
    }

    private int calculateBF(Node cur){

        if(cur.left != null && cur.right == null){
            return (cur.left.height - 0);
        }else if(cur.right != null && cur.left == null){
            return (0 - cur.right.height);
        }else{
            return (cur.left.height - cur.right.height);
        }

    }

    private void rotateNode(Node cur){
        // perform rotations based on BF value
        if(cur.balanceFactor >= 2 && cur.left.balanceFactor == -1){
            //needs Left-Right rotation
            System.out.println("CALLING LEFT RIGHT ROTATION");
            leftRightRotation(cur);
        }else if( cur.balanceFactor >= 2 && cur.left.balanceFactor == 1){
            //needs Right rotation
            System.out.println("CALLING RIGHT ROTATION");
            //rightRotation(cur);
        }else if(cur.balanceFactor <= -2 && cur.right.balanceFactor == 1){
            //needs Right-Left rotation
            System.out.println("CALLING RIGHT LEFT ROTAION");
            //rightLeftRoatation(cur);
        }else if(cur.balanceFactor <= -2 && cur.right.balanceFactor == -1){
            // needs Left rotation
            System.out.println("CALLING LEFT ROTATION");
            //leftRotation(cur);
        }
       
    }

    private void rightRotation(Node cur){
        Node next = cur.left;
        Node a, b, c, treeZero, treeOne, treeTwo, treeThree;
        c = cur;
        b = next;
        a = next.left;
        treeZero = a.left;
        treeOne = a.right;
        treeTwo = b.right;
        treeThree = c.right;
        //Got nodes in right positions now rotate
        if(cur.parent.left.data == cur.data){
            cur.parent.left = b;   
        }else{
            cur.parent.right= b;
        }
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        c.parent = b;
        c.right = treeZero;
        c.left = treeOne;
        a.parent = b;
        a.right = treeTwo;
        a.left = treeThree;
        //Fix height 
    }

    private void leftRotation(Node cur){
        Node next = cur.right;
        Node a, b, c, treeZero, treeOne, treeTwo, treeThree;
        //Needs left rotation
        a = cur;
        b = next;
        c = next.right;
        treeZero = a.left;
        treeOne = b.left;
        treeTwo = c.left;
        treeThree = c.right;
        //got nodes in right positions now rotate 
        if(cur.parent.right.data == cur.data){
            cur.parent.right = b;   
        }else{
            cur.parent.left = b;
        }
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        c.parent = b;
        c.right = treeThree;
        c.left = treeTwo;
        a.parent = b;
        a.left = treeZero;
        a.right = treeOne;

    }

    private void rightLeftRoatation(Node cur){
        Node next = cur.right;
        Node a, b, c, treeZero, treeOne, treeTwo, treeThree;
        //Needs right-left rotation
        a = cur;
        c = next;
        b = next.left;
        treeZero = a.left;
        treeOne = b.left;
        treeTwo = b.right;
        treeThree = c.right;
        //nodes in position now rotate
        if(cur.parent.right.data == cur.data){
            cur.parent.right = b;   
        }else{
            cur.parent.left = b;
        }
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        c.parent = b;
        c.right = treeThree;
        c.left = treeTwo;
        a.parent = b;
        a.left = treeZero;
        a.right = treeOne;

    }

    private void leftRightRotation(Node cur){
        System.out.println("IN LEFT RIGHT ROTATION");
        Node next = cur.left;
        Node a, b, c, treeZero, treeOne, treeTwo, treeThree;
        //Needs left-right rotation
        c = cur;
        a = next;
        b = next.right;
        /*
        a.right = b.left;
        c.left = b.right;
        b.parent = c.parent;
        b.left = a;
        b.right = c;
        a.parent = b;
        c.parent = b;
        */
        System.out.println("c = "+b.data);
    
        if(cur.parent == null){
            //rotation on root, update.
            this.root = b;
            System.out.println("updated root to : " + b.data);
        }
        
        //System.out.println("Performing fake rotation");
        //b.right = c;
        //b.left = a;
        //System.out.println("b = " + b.data);
        //System.out.println("b.left = " + b.left.data);
        //System.out.println("b.right = " + b.right.data);
        
        /*
        treeZero = next.right;
        treeOne = b.right;
        treeTwo = b.left;
        treeThree = a.left;
        
        //Got positions of all nodes and subtrees now rotate
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        a.parent = b;
        a.left = treeThree;
        a.right = treeTwo;
        c.parent = b;
        c.left = treeOne;
        c.right = treeZero;
        
        //update heights and balance factors
        c.height = calculateHeight(c);
        c.balanceFactor = calculateBF(c);
        a.height = calculateHeight(a);
        a.balanceFactor = calculateBF(a);
        b.height = calculateHeight(b);
        b.balanceFactor = calculateBF(b);
        if(parent != null){
            //update parent node to point to new rotated sub-tree
            if(parent.data < b.data){
                parent.right = b;
            }else{
                parent.left = b;
            }
        }else{
            //rotation happened on root, need to update
            System.out.println("Updating root...");
            this.root = b;
        }
        */
    }

    //Iterative methods for AVL operations

    public void insertIter(int data){
        Node cur = this.root;
        boolean notInserted = true;
        if(this.root == null){
            Node root = new Node(data);
            root.parent = null;
            this.root = root;
            notInserted = false;
        }
        while(notInserted){
            //traverse tree
            if(cur.data < data){
                //go right
                if(cur.right == null){
                    //insert
                    Node rightInsert = new Node(data);
                    rightInsert.parent = cur;
                    cur.right = rightInsert;
                    notInserted = false;

                    if(cur.left == null){
                        //inserted into a new level, need to update heights and balance factor of all parents
                        updateHeightAndBFInsertion(cur.parent);
                        break;
                    }

                }else{
                    //update current
                    cur = cur.right;
                }
            }
            if(cur.data > data){
                //go left
                if(cur.left == null){
                    //insert
                    Node leftInsert = new Node(data);
                    leftInsert.parent = cur;
                    cur.left = leftInsert;
                    notInserted = false;

                    if(cur.right == null){
                        //inserted into a new level, need to update heights and balance factor of all parents
                        updateHeightAndBFInsertion(cur.parent);
                        break;
                    }

                }else{
                    //update current
                    cur = cur.left;
                }
            }
        }
        System.out.println("INSERT ITER : " + data);
    }
    
    public void deleteIter(int data){
        Node cur = this.root;
        //find Node to delete
        while(true){
            //traverse tree 
            if(cur.data == data){
                //found 
                break;

            }else{

                if(cur.data > data){
                    cur = cur.left;
                }else if(cur.data < data) {
                    cur = cur.right;
                }
            }
        }
        if(cur.left == null){
            transplantNode(cur, cur.right);
        }else if(cur.right == null){
            transplantNode(cur, cur.left);
        }else{
            Node min = findMinIter(cur.right);
            if(min.parent.data != cur.data){
                transplantNode(min, min.right);
                min.right = cur.right;
                min.right.parent = min;
            }
            transplantNode(cur, min);
            min.left = cur.left;
            min.left.parent = min;
        }
      
    }

    public Node findNextIter(int data){
        boolean notFound = true;
        Node cur = this.root;
        ArrayList<Node> memo = new ArrayList<Node>();

        while(notFound){
            //traverse tree
            if(cur.data == data){
                //found
                notFound = false;
            }
            memo.add(cur);

            if(cur.data > data){
                cur = cur.left;
            }else if(cur.data < data){
                cur = cur.right;
            }
        }
        
        if(cur.right == null){
            //next in memo or DNE
            if(memo.get(memo.size()-2).data > data){
                return memo.get(memo.size()-2);
            }
        }else{
            return findMinIter(cur.right);
        }
        //return DNE
        Node dne = new Node(0);
        return dne;
    }

    public Node findPrevIter(int data){
        boolean notFound = true;
        Node cur = this.root;
        ArrayList<Node> memo = new ArrayList<Node>();
        
        while(notFound){
            //traverse tree
            if(cur.data == data){
                //found 
                notFound = false;
            }
            memo.add(cur);
        
            if(cur.data > data){
                cur = cur.left;
            }else if(cur.data < data){
                cur = cur.right;
            }
        }

        if(cur.left == null){
            //prev in memo or DNE
            Node tmp;
            for(int i = memo.size()-1; i >= 0; i-- ){
                tmp = memo.get(i);
                if(tmp.data < data){
                    return tmp;
                }
            }
        }else{
            return findMaxIter(cur.left);
        }   
        //return DNE
        Node dne = new Node(0);
        return dne;
        
    }

    public Node findMinIter(Node root){
        Node cur = root;
        
        if(cur == null){
            System.out.println("Tree empty");
        }
        while(cur.left != null){
            cur = cur.left;
        }
        return cur;
    }

    public Node findMaxIter(Node root){
        Node cur = root;

        if(cur == null){
            System.out.println("Tree empty");
        }
        while(cur.right != null){
            cur = cur.right;
        }
        return cur;
    }

    private void transplantNode(Node u, Node v){
        if(u.parent == null){
            //replace root with v
            this.root = v;
        }else if(u.data == u.parent.left.data){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        if(v != null){
            v.parent = u.parent;
        }
    }

    public void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    
    
}