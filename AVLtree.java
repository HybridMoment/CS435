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
                break; //Need to break out after rotate no need to calculate heights and bf
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
            rightRotation(cur);
        }else if(cur.balanceFactor <= -2 && cur.right.balanceFactor == 1){
            //needs Right-Left rotation
            rightLeftRoatation(cur);
        }else if(cur.balanceFactor <= -2 && cur.right.balanceFactor == -1){
            // needs Left rotation
            leftRotation(cur);
        }
       
    }

    private void rightRotation(Node cur){
        Node next = cur.left;
        Node a, b, c, treeTwo;

        //Set up nodes for manipulation, store subtrees that need to be moved
        a = cur;
        b = next;
        c = next.left;
        treeTwo = b.right;

        //moving nodes for rotation
        b.parent = cur.parent;
        b.right = a;
        b.left = c; //redundant
        a.left = treeTwo;
        if(treeTwo != null){
            treeTwo.parent = a;
        }
        a.parent = b;
        c.parent = b; //redundant

        //Check if rotation happened on root
        Node parent = b.parent;
        if(parent == null){
            //rotation on root, update.
            this.root = b;

        }else{
            //rotation did NOT occur on the root, must find if cur is a left or right child
            if(parent.data > b.data){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = b;
            }else{
                //b must be a right child 
                parent.right = b;
            }
        }

        //Need to update heights and balance factors after performing rotations
        c.height = calculateHeight(c); //redundant
        c.balanceFactor = calculateBF(c); //redundant
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
        Node a, b, c, treeTwo;
        //Needs left rotation
        a = cur;
        b = next;
        c = next.right;
        treeTwo = b.left;
        

        //got nodes in right positions now rotate 
        b.parent = cur.parent;
        b.right = c;
        b.left = a;
        a.right = treeTwo;
        if(treeTwo != null){
            treeTwo.parent = a;
        }
        a.parent = b;
        c.parent = b;

        //Check if rotation took place on the root
        Node parent = b.parent;
        if(parent == null){
            //rotation on root, update.
            this.root = b;

        }else{
            //rotation did NOT occur on the root, must find if cur is a left or right child
            if(parent.data > b.data){
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
        Node a, b, c, subtreeThree, subtreeTwo;

        //Set up nodes for manipulation, store the subtrees that need to be moved
        a = cur;
        b = next;
        c = next.left;
        subtreeTwo = c.left;
        subtreeThree = c.right;
        
        //moving nodes around to rotate
        c.parent = cur.parent;
        c.right = b;
        c.left = a;
        a.right = subtreeTwo;
        if(subtreeTwo != null){
            subtreeTwo.parent = a;
        }
        a.parent = c;
        b.left = subtreeThree;
        if(subtreeThree != null){
            subtreeThree.parent = b;
        }
        b.parent = c;

        //checking if rotation happened on root 
        Node parent = c.parent;
        
        if(parent == null){
            //rotation on node need to update
            this.root = c;
        }else{
            //rotation did not happen on the root node
            if(parent.data > c.data){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = c;
            }else{
                //b must be a right child 
                parent.right = c;
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
        Node a, b, c, subtreeTwo, subtreeThree;

        //Set up nodes for manipulation, store subtrees that need to be moved
        a = cur;
        b = next;
        c = next.right;
        subtreeTwo = c.left;
        subtreeThree = c.right;

        //Moving nodes around to rotate
        c.parent = cur.parent;
        c.left = b;
        c.right = a;
        a.left = subtreeThree;
        if(subtreeThree != null){
            subtreeThree.parent = a;
        }
        b.right = subtreeTwo;
        if(subtreeTwo != null){
            subtreeTwo.parent = b;
        }
        a.parent = c;
        b.parent = c;

        //Check if rotation took place on the root
        Node parent = c.parent;
        if(parent == null){
            //rotation on root, update.
            this.root = c;

        }else{
            //rotation did NOT occur on the root, must find if cur is a left or right child
            if(parent.data > c.data){
                //cur was a left child, we need to update the parent to point at new rotated node
                parent.left = c;
            }else{
                //b must be a right child 
                parent.right = c;
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

    public void printLevelOrder(Node root){
         // Base Case 
         if(root == null) 
         return; 
       
        // Create an empty queue for level order tarversal 
        Queue<Node> q =new LinkedList<Node>(); 
        
        // Enqueue Root and initialize height 
        q.add(root); 
        
        
        while(true) 
        { 
            
            // nodeCount (queue size) indicates number of nodes 
            // at current level. 
            int nodeCount = q.size(); 
            if(nodeCount == 0) 
                break; 
            
            // Dequeue all nodes of current level and Enqueue all 
            // nodes of next level 
            while(nodeCount > 0) 
            { 
                Node node = q.peek(); 
                System.out.print(node.data + " "); 
                q.remove(); 
                if(node.left != null) 
                    q.add(node.left); 
                if(node.right != null) 
                    q.add(node.right); 
                nodeCount--; 
            } 
            System.out.println("");
        } 
    }

    
    
}