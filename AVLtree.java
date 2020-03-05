import java.util.*; 

public class AVLtree{
    Node root;
    int levelCounter = 0;

    AVLtree(int data){
        this.root = new Node(data);
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

                //added for Question 6
                this.levelCounter = this.levelCounter + 1;

                if(cur.right == null){
                    //insert
                    Node rightInsert = new Node(data);
                    rightInsert.parent = cur;
                    cur.right = rightInsert;
                    notInserted = false;

                    if(cur.left == null){
                        //inserted into a new level, need to update heights and balance factor of all parents
                        updateHeightAndBFInsertion(cur);
                        break;
                    }

                }else{
                    //update current
                    cur = cur.right;
                }
            }
            if(cur.data > data){
                //go left

                //added for question 6
                this.levelCounter = this.levelCounter + 1;
                
                if(cur.left == null){
                    //insert
                    Node leftInsert = new Node(data);
                    leftInsert.parent = cur;
                    cur.left = leftInsert;
                    notInserted = false;

                    if(cur.right == null){
                        //inserted into a new level, need to update heights and balance factor of all parents
                        updateHeightAndBFInsertion(cur);
                        break;
                    }

                }else{
                    //update current
                    cur = cur.left;
                }
            }
        }
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
        }else if(root.left == null && root.right == null){
            return 1;
        }else{
            return( 1 + Math.max(root.left.height, root.right.height));
        }
        
    }

    private int calculateBF(Node cur){

        if(cur.left != null && cur.right == null){
            return (cur.left.height - 0);
        }else if(cur.right != null && cur.left == null){
            return (0 - cur.right.height);
        }else if(cur.right == null && cur.left == null){
            return 0;
        }else{
            return (cur.left.height - cur.right.height);
        }

    }

    private void rotateNode(Node cur){
        // perform rotations based on BF value
        if(cur.balanceFactor >= 2 && cur.left.balanceFactor == -1){
            //needs Left-Right rotation
            leftRightRotation(cur);
        }else if( cur.balanceFactor >= 2 && cur.left.balanceFactor == 1){
            //needs Right rotation
            //System.out.println("CALLING RIGHT ROTATION");
            rightRotation(cur);
        }else if(cur.balanceFactor <= -2 && cur.right.balanceFactor == 1){
            //needs Right-Left rotation
            //System.out.println("CALLING RIGHT LEFT ROTAION");
            rightLeftRoatation(cur);
        }else if(cur.balanceFactor <= -2 && cur.right.balanceFactor == -1){
            // needs Left rotation
            //System.out.println("CALLING LEFT ROTATION");
            leftRotation(cur);
        }
       
    }

    private void rightRotation(Node cur){
        Node next = cur.left;
        Node a, b, c, treeZero, treeOne, treeTwo, treeThree;
        int curData = cur.data;

        //Set up nodes for manipulation, store subtrees that need to be moved
        c = cur;
        b = next;
        a = next.left;
        treeZero = a.left;
        treeOne = a.right;
        treeTwo = b.right;
        treeThree = c.right;

        //moving nodes for rotation
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        a.left = treeThree;
        a.right = treeTwo;
        c.left = treeOne;
        c.right = treeZero;
        a.parent = b;
        c.parent = b;

        //Check if rotation happened on root
        Node parent = b.parent;
        if(parent == null){
            //rotation on root, update.
            this.root = b;

        }else{
            //rotation did NOT occur on the root, must find if cur is a left or right child
            if(parent.left != null && parent.left.data == curData){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = b;
            }else{
                //b must be a right child 
                parent.right = b;
            }
        }

        //Need to update heights and balance factors after performing rotations
        c.height = calculateHeight(c);
        c.balanceFactor = calculateBF(c);
        a.height = calculateHeight(a);
        a.balanceFactor = calculateBF(a);
        b.height = calculateHeight(b);
        b.balanceFactor = calculateBF(b);

        //Need to update all parents since we performed a rotation
        while(parent != null){

            //recurse up parents and update their height and balance factor
            parent.height = calculateHeight(parent);
            parent.balanceFactor = calculateBF(parent);
            parent = parent.parent;
        }   

    }

    private void leftRotation(Node cur){
        Node next = cur.right;
        int curData = cur.data;
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
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        c.parent = b;
        c.right = treeThree;
        c.left = treeTwo;
        a.parent = b;
        a.left = treeZero;
        a.right = treeOne;

        //Check if rotation took place on the root
        Node parent = b.parent;
        if(parent == null){
            //rotation on root, update.
            this.root = b;

        }else{
            //rotation did NOT occur on the root, must find if cur is a left or right child
            if(parent.left != null && parent.left.data == curData){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = b;
            }else{
                //b must be a right child 
                parent.right = b;
            }
        }
        //Need to update heights and balance factors after performing rotations
        c.height = calculateHeight(c);
        c.balanceFactor = calculateBF(c);
        a.height = calculateHeight(a);
        a.balanceFactor = calculateBF(a);
        b.height = calculateHeight(b);
        b.balanceFactor = calculateBF(b);

        //Need to update all parents since we performed a rotation
        while(parent != null){
            //recurse up parents and update their height and balance factor
            parent.height = calculateHeight(parent);
            parent.balanceFactor = calculateBF(parent);
            parent = parent.parent;
        }        

    }

    private void rightLeftRoatation(Node cur){
        Node next = cur.right;
        Node a, b, c, subtreeOne, subtreeTwo;
        int curData = cur.data;

        //Set up nodes for manipulation, store the subtrees that need to be moved
        a = cur;
        c = next;
        b = next.left;
        subtreeOne = b.left;
        subtreeTwo = b.right;
        
        //moving nodes around to rotate
        b.parent = cur.parent;
        b.left = a;
        b.right = c;
        a.right = subtreeOne;
        c.left = subtreeTwo;
        a.parent = b;
        c.parent = b;

        //checking if rotation happened on root 
        Node parent = b.parent;
        
        if(parent == null){
            //rotation on node need to update
            this.root = b;
        }else{
            //rotation did not happen on the root node
            if(parent.left != null && parent.left.data == curData){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = b;
            }else{
                //b must be a right child 
                parent.right = b;
            }
        }

        //need to update heights and balance factors 
        c.height = calculateHeight(c);
        c.balanceFactor = calculateBF(c);
        a.height = calculateHeight(a);
        a.balanceFactor = calculateBF(a);
        b.height = calculateHeight(b);
        b.balanceFactor = calculateBF(b);

        //Need to update all parents since we performed a rotation
        while(parent != null){
            //recurse up parents and update their height and balance factor
            parent.height = calculateHeight(parent);
            parent.balanceFactor = calculateBF(parent);
            parent = parent.parent;
        }

    }

    private void leftRightRotation(Node cur){
        //System.out.println("IN LEFT RIGHT ROTATION");
        Node next = cur.left;
        Node a, b, c, subtreeTwo, subtreeOne;
        int curData = cur.data;

        //Set up nodes for manipulation, store subtrees that need to be moved
        c = cur;
        a = next;
        b = next.right;
        subtreeTwo = b.left;
        subtreeOne = b.right;

        //Moving nodes around to rotate
        b.parent = cur.parent;
        b.left = a;
        b.right = c;
        a.right = subtreeTwo;
        c.left = subtreeOne;
        c.parent = b;
        a.parent = b;

        //Check if rotation took place on the root
        Node parent = b.parent;
        if(parent == null){
            //rotation on root, update.
            this.root = b;

        }else{
            //rotation did NOT occur on the root, must find if cur is a left or right child
            if(parent.left != null && parent.left.data == curData){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = b;
            }else{
                //b must be a right child 
                parent.right = b;
            }
        }
        
        //Need to update heights and balance factors after performing rotations
        c.height = calculateHeight(c);
        c.balanceFactor = calculateBF(c);
        a.height = calculateHeight(a);
        a.balanceFactor = calculateBF(a);
        b.height = calculateHeight(b);
        b.balanceFactor = calculateBF(b);

        //Need to update all parents since we performed a rotation
        while(parent != null){
            //recurse up parents and update their height and balance factor
            parent.height = calculateHeight(parent);
            parent.balanceFactor = calculateBF(parent);
            parent = parent.parent;
        }

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
    
    public void postOrder(Node root){
        
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    
    
}