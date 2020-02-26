import java.nio.file.NotDirectoryException;

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
            this.root = new Node(data);
        }
        while(notInserted){
            //traverse tree
            if(cur.data < data){
                //go right
                if(cur.right == null){
                    //insert
                    cur.right = new Node(data);
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
                    cur.left = new Node(data);
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
        //TODO: Case 3 not working
        boolean notFound = true;
        Node cur = this.root;
        Node prev = this.root;

        while(notFound){
            if(cur.data == data){
                //found
                if(cur.left == null && cur.right == null){
                    //case 1: Node to delete is leaf
                    if(prev.left == null){
                        //right leaf to delete
                        prev.right = null;
                    }else{
                        //left leaf to delete
                        prev.left = null;
                    }
                    cur = null;
                    notFound = false;
                }else if(cur.left == null || cur.right == null){
                    //case 2: node to delete has one child
                    if(cur.left != null){
                        //has left child
                        prev.left = cur.left;
                        cur.left = null;
                        notFound = false;
                    }else{
                        //has right child
                        prev.right = cur.right;
                        cur.right = null;
                        notFound = false;
                    }
                }else{
                    //case 3: node has two children and we need to find inorder successor
                    Node successor = cur.right;
                    if(successor.left != null){
                        //traverse left till at lowest node for successor
                        while(successor.left != null){
                            prev = successor;
                            successor = successor.left;
                        }
                        successor.left = cur.left;
                        successor.right = cur.right;
                        prev.left = null;
                        notFound = false;

                    }else{
                        //succesor is right node
                        successor = cur.right;
                        successor.left = cur.left;
                        successor.right = successor.right;
                        cur = successor;
                        notFound = false;
                    }
                }
            }else{
                //else traverse
                if(cur.data > data){
                    //go left
                    prev = cur;
                    cur = cur.left;
                }
                if(cur.data < data){
                    //go right
                    prev = cur;
                    cur = cur.right;
                }
            }

            
        }
    }
    public void findNextIter(int data){
        boolean notFound = true;
        Node cur = this.root;
        Node prev = this.root;

        while(notFound){
            if(cur.data == data){
                //found
                if(cur.right == null){
                    System.out.println("Next value iter : " + prev.data);
                    notFound = false;
                }else{
                    System.out.println("Next Value iter : " + cur.right.data);
                    notFound = false;
                }
            }
            if(cur.data > data){
                //go left
                prev = cur;
                cur = cur.left;
            }else{
                //go right
                prev = cur;
                cur = cur.right;
            }
        }
    }
    public void findPrevIter(int data){
        boolean notFound = true;
        Node cur = this.root;
        Node prev = this.root;

        while(notFound){
            if(cur.data == data){
                //found
                if(cur.left == null){
                    System.out.println("Prev value iter : " + prev.data);
                    notFound = false;
                }else{
                    System.out.println("Next value iter : " + cur.left.data);
                    notFound = false;
                }
            }
            if(cur.data > data){
                //go left
                prev = cur;
                cur = cur.left;
            }else{
                //go right
                prev = cur;
                cur = cur.right;
            }
        }
    }

    public void findMinIter(){
        Node cur = this.root;
        
        if(cur == null){
            System.out.println("Tree empty");
        }
        while(cur.left != null){
            cur = cur.left;
        }
        System.out.println("Min val iter: " + cur.data);

    }
    public void findMaxIter(){
        Node cur = this.root;

        if(cur == null){
            System.out.println("Tree empty");
        }
        while(cur.right != null){
            cur = cur.right;
        }
        System.out.println("Max val iter: "+ cur.data);
    }

    public void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }




}