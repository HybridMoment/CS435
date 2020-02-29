import java.util.*; 

public class BinarySearchTree{
    Node root;

    //constructor for BinarySearchTree Class
    BinarySearchTree(int data){
        this.root = new Node(data);
    }

    public void insert(int data){
       this.root =  insertRec(this.root, data);
    }

    private Node insertRec(Node root, int data){
        //base case
        if(root == null){
            root = new Node(data);
            return root;
        }
        //case 1: recurse left sub tree
        if(data < root.data){
            //go left
            root.left = insertRec(root.left, data);
        }
        //case 2: recurse right sub tree 
        if(data > root.data){
            //go right
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public void delete(int data){
       root = deleteRec(this.root, data);
    }

    private Node deleteRec( Node root, int data){
        //TODO: Make this work
        //base case
        if(root == null){
            return root;
        }
        //case 1
        if(data < root.data){

        }
        return root;
    }

    public void findMin(){
        Node minNode = findMinRec(this.root);
        System.out.println("MIN VALUE IS: " + minNode.data);
    }

    private Node findMinRec(Node root){
        //Base case    
        if(root.left == null){
            return root;
        }
        //Case 1:
        if(root.left != null){
            //Go left 
            return findMinRec(root.left);
        }
        return root;
    }

    public void findMax(){
        Node maxNode = findMaxRec(this.root);
        System.out.println("MAX VALUE IS: " + maxNode.data);
    }

    private Node findMaxRec(Node root){
        //Base case
        if(root.right == null){
            return root;
        }
        if(root.right != null){
            //go right
            return findMaxRec(root.right);
        }
        return root;
    }

    public void findNext(int data){
        Node findNextNode = findNextRec(this.root, data);
        System.out.println("FOUND NEXT: " + findNextNode.data);
    }

    private Node findNextRec(Node root, int data){
        //Base case
        if(root == null){
            return null;
        }
        //case 1: data is larger than root
        if(root.data < data){
            //go right then left
            return findMinRec(root.right);
        }
        //Case 2: data is less than root
        if(root.data > data){
            //go left, then right
            return findMaxRec(root.left);
        }
        return root;
    }

    public void findPrev(int data){
        Node findPrevNode = findPrevRec(this.root, data);
        System.out.println("FOUND PREV: " + findPrevNode.data);
    }

    private Node findPrevRec(Node root, int data){
        //Base case
        if(root == null){
            return null;
        }
        //case 1: data is larger
        if(root.data < data){
            return findMaxRec(root.right);
        }
        //case 2: data is smaller
        if(root.data > data){
            return findMaxRec(root.left);
        }
        return root;
    }

    //iterative methods for binary search tree operations

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

    public void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }




}