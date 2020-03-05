import java.util.*;

public class Main{

    public int[] getRandomArray(int n){
        
        int[] arr = new int[n];
        Random randNumberGen = new Random();
        Set<Integer> randomNumberHash = new HashSet<Integer>();
        int randomNum;

        for(int i = 0; i < n; i++){
            randomNum = randNumberGen.nextInt(n);
            while(randomNumberHash.contains(randomNum)){
                //generate new number
                randomNum = randNumberGen.nextInt(n);
            }
            arr[i] = randomNum;
            //update hash 
            randomNumberHash.add(randomNum);
        }
        //System.out.println(Arrays.toString(arr));
        return arr;
        
    }

    public int[] getSortedArray(int n){
        int[] arr = new int[n];
        
        for(int i = 0; i < arr.length; i++){
            arr[i] = n;
            n = n -1;
        }
        return arr;
        //System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args){

            Main myMain = new Main();
            BinarySearchTree myTree = new BinarySearchTree(5);

            boolean DEBUG_ITER_BST = false;
            boolean DEBUG_REC_BST = false;
            boolean DEBUG_ITER_AVL = false;
            boolean DEBUG_RAN_ARRAY = true;

            if(DEBUG_RAN_ARRAY){
                boolean questionFivePartA = false;
                boolean questionFivePartB = false;
                boolean questionFivePartC = false;
                boolean questionSixPartB = false;
                boolean questionSixPartC = true;

                /* 
                    This will portion handles Question 5 and 6 problems
                */
                if(questionFivePartA){
                    //BinarySearchTree myBST = new BinarySearchTree(giantArray[0]);
                    //(Part A)
                    int giantArray[] = myMain.getRandomArray(10000);
                    AVLtree myAVL = new AVLtree(giantArray[0]);
                    //BinarySearchTree myBST = new BinarySearchTree(giantArray[0]);
                    final long startTime = System.currentTimeMillis();
                    for(int i = 1; i < giantArray.length -1; i++){
                        myAVL.insertIter(giantArray[i]);
                        //myBST.insert(giantArray[i]);
                    }
                    final long endTime = System.currentTimeMillis();
                    System.out.println("Total time = " + (endTime - startTime));
                
                    //myAVL.inorder(myAVL.root);
                    //System.out.println(" ");
                    //myBST.inorder(myBST.root);
                }
                
                if(questionFivePartB){
                    //(Part B)
                    int smallArray[] = myMain.getRandomArray(25);
                    //AVLtree smallAVL = new AVLtree(smallArray[0]);
                    BinarySearchTree smallBST = new BinarySearchTree(smallArray[0]);

                    final long smallStartTime = System.currentTimeMillis();
                    for(int i = 1; i < smallArray.length -1; i++){
                        //smallAVL.insertIter(smallArray[i]);
                        smallBST.insert(smallArray[i]);
                    }
                    final long smallEndTime = System.currentTimeMillis();
                    System.out.println("Total time small array = " + (smallEndTime - smallStartTime));
                }
                
                
                if(questionFivePartC){
                    //(Part c)
                    //BST outperforms AVL
                    int[] giantArrayIter = myMain.getRandomArray(10000);
                    //AVLtree giantAVLiter = new AVLtree(giantArrayIter[0]);
                    BinarySearchTree giantBSTiter = new BinarySearchTree(giantArrayIter[0]);

                    final long iterStartTime = System.currentTimeMillis();
                    for(int i = 1; i < giantArrayIter.length -1; i++){
                        giantBSTiter.insertIter(giantArrayIter[i]);
                        //giantAVLiter.insertIter(giantArrayIter[i]);
                    }
                    final long iterEndTime = System.currentTimeMillis();
                    System.out.println("Total time Large iterative = " + (iterEndTime - iterStartTime));
                
                }
                

                /* 
                    This will perform tasks for question 6 all parts
                */
                if(questionSixPartB){
                    //(Part b)
                    int[] giantArrayLevelCount = myMain.getRandomArray(10000);
                    AVLtree giantAvlLevelCount = new AVLtree(giantArrayLevelCount[0]);
                    BinarySearchTree giantBstLevelCount = new BinarySearchTree(giantArrayLevelCount[0]);

                    final long levelCountStartTime = System.currentTimeMillis();
                    for(int i = 1; i < giantArrayLevelCount.length -1; i++){
                        giantBstLevelCount.insertIter(giantArrayLevelCount[i]);
                        giantAvlLevelCount.insertIter(giantArrayLevelCount[i]);
                    }
                    final long levelCountEndTime = System.currentTimeMillis();
                    System.out.println("Total time Level Count = " + (levelCountEndTime - levelCountStartTime));
                    System.out.println("Total level binary tree = " + giantBstLevelCount.levelCounter);
                    System.out.println("Total level count avl tree = "+ giantAvlLevelCount.levelCounter);
                }
                
                if(questionSixPartC){
                    //(Part C)
                    int[] sortedArray = myMain.getSortedArray(10000);
                    AVLtree avl = new AVLtree(sortedArray[0]);
                    BinarySearchTree bst = new BinarySearchTree(sortedArray[0]);

                    final long startTime = System.currentTimeMillis();
                    for(int i = 1; i < sortedArray.length -1; i++){
                        bst.insertIter(sortedArray[i]);
                        //avl.insertIter(sortedArray[i]);
                    }
                    final long endTime = System.currentTimeMillis();
                    System.out.println(" Total time : " + (endTime - startTime));
                    System.out.println(" Total Levels bst : " + bst.levelCounter);
                    //System.out.println(" Total Levels avl : " + avl.levelCounter);
                }
                
            }

            if(DEBUG_ITER_BST){
                myTree.insertIter(3);
                myTree.insertIter(2);
                myTree.insertIter(7);
                myTree.insertIter(6);
                myTree.insertIter(8);
                Node check = myTree.findPrevIter(3);
                System.out.println( "PREV : " + check.data);
                check = myTree.findMinIter(myTree.root);
                System.out.println("MIN : " + check.data);
                check = myTree.findMaxIter(myTree.root);
                System.out.println( "MAX : " + check.data);
                check = myTree.findNextIter(2);
                System.out.println("NEXT : " + check.data);
                myTree.deleteIter(5);

                //myTree.deleteIter(10);
                myTree.inorder(myTree.root);
                System.out.println("ROOT : " + myTree.root.data);
            }

            if(DEBUG_REC_BST){
                System.out.println("Starting Recursive Tree");
                BinarySearchTree recurTree = new BinarySearchTree(5);
                recurTree.insert(3);
                recurTree.insert(2);
                recurTree.findMin();
                recurTree.findMax();
                recurTree.insert(7);
                recurTree.insert(6);
                recurTree.insert(8);
                recurTree.findMax();

                recurTree.findNext(2);
                recurTree.findNext(3);
                recurTree.findPrev(2);
                recurTree.findPrev(7);
                recurTree.inorder(recurTree.root);
            }

            if(DEBUG_ITER_AVL){
                AVLtree myAvLtree = new AVLtree(5);
                myAvLtree.insertIter(3);
                myAvLtree.insertIter(2);
                myAvLtree.insertIter(7);
                myAvLtree.insertIter(6);
                myAvLtree.insertIter(8);

                System.out.println("Root data : " + myAvLtree.root.data);
                System.out.println("Root height : " + myAvLtree.root.height);
                System.out.println("Root balance factor : " + myAvLtree.root.balanceFactor);
                //System.out.println("Root data = " + myAvLtree.root.data);
                //System.out.println("Root parent : " + myAvLtree.root.parent);
                myAvLtree.inorder(myAvLtree.root);
                System.out.println(" ");
                
            }
           
            
    }
}