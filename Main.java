import java.util.*;

public class Main{

    public int[] getRandomArray(int n){
        
        int[] arr = new int[n];
        Random randNumberGen = new Random();
        Set<Integer> randomNumberHash = new HashSet<Integer>();
        int randomNum;

        for(int i = 0; i < n; i++){
            randomNum = randNumberGen.nextInt(n) + 1;
            while(randomNumberHash.contains(randomNum)){
                //generate new number
                randomNum = randNumberGen.nextInt(n) + 1;
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
        //System.out.println(Arrays.toString(arr));
        return arr;
        
    }

    public static void main(String[] args){

            Main myMain = new Main();
            BinarySearchTree myTree = new BinarySearchTree(5);

            boolean DEBUG_ITER_BST = false;
            boolean DEBUG_REC_BST = false;
            boolean DEBUG_ITER_AVL = false;
            boolean DEBUG_RAN_ARRAY = true;

            

            if(DEBUG_RAN_ARRAY){
                boolean questionFivePartA = true;
                boolean questionFivePartB = true;
                boolean questionFivePartC = true;
                boolean questionSixPartB = true;
                boolean questionSixPartC = true;

                /* 
                    This will portion handles Question 5 and 6 problems
                */
                if(questionFivePartA){
                    
                    //(Part A)
                    int giantArray[] = myMain.getRandomArray(10000);
                    AVLtree myAVL = new AVLtree(giantArray[0]);
                    BinarySearchTree myBST = new BinarySearchTree(giantArray[0]);

                    System.out.println("Printing results, question 5 Part A... (array 10000)");
                    //timing AVL insertions for question 5 part A
                    final long startTimeAVL = System.currentTimeMillis();
                    
                    for(int i = 1; i < giantArray.length; i++){

                        myAVL.insertIter(giantArray[i]); //iterative insert
                        
                    }
                    
                    final long endTimeAVL = System.currentTimeMillis();
                    System.out.println(" Total time AVL = " + (endTimeAVL - startTimeAVL));

                    //timing BST insertions for question 5 part A
                    final long startTimeBST = System.currentTimeMillis();
                    for(int i = 1; i < giantArray.length; i++){
        
                        myBST.insert(giantArray[i]); //recursive insert

                    }
                    final long endTimeBST = System.currentTimeMillis();
                    System.out.println(" To time recursive BST inserts = " + (endTimeBST - startTimeBST));                    
                    System.out.println(" ");

                }
                
                if(questionFivePartB){
                    //(Part B)
                    int smallArray[] = myMain.getRandomArray(10);
                    AVLtree smallAVL = new AVLtree(smallArray[0]);
                    BinarySearchTree smallBST = new BinarySearchTree(smallArray[0]);

                    System.out.println("Printing results, question 5 Part B...(array 10)");
                    final long smallStartTimeAVL = System.currentTimeMillis();
                    for(int i = 1; i < smallArray.length; i++){
                        
                        smallAVL.insertIter(smallArray[i]);

                    }
                    final long smallEndTimeAVL = System.currentTimeMillis();
                    System.out.println(" Total time small array AVL = " + (smallEndTimeAVL - smallStartTimeAVL));

                    final long smallStartTimeBST = System.currentTimeMillis();
                    for(int i = 1; i < smallArray.length; i++){
                        
                        smallBST.insert(smallArray[i]);

                    }
                    final long smallEndTimeBST = System.currentTimeMillis();
                    System.out.println(" Total time small array BST = " + (smallEndTimeBST - smallStartTimeBST));
                    System.out.println(" ");
                }
                
                
                if(questionFivePartC){
                    //(Part c)
                    //BST outperforms AVL
                    int[] giantArrayIter = myMain.getRandomArray(10000);
                    AVLtree giantAVLiter = new AVLtree(giantArrayIter[0]);
                    BinarySearchTree giantBSTiter = new BinarySearchTree(giantArrayIter[0]);

                    System.out.println("Pringting results, question 5 Part C...(array 10000)");
                    final long iterStartTimeAVL = System.currentTimeMillis();
                    
                    for(int i = 1; i < giantArrayIter.length; i++){
                        
                        giantAVLiter.insertIter(giantArrayIter[i]);

                    }
                    
                    final long iterEndTimeAVL = System.currentTimeMillis();
                    System.out.println(" Total time 10000 iterative AVL = " + (iterEndTimeAVL - iterStartTimeAVL));

                    final long iterStartTimeBST = System.currentTimeMillis();
                    for(int i = 1; i < giantArrayIter.length; i++){

                        giantBSTiter.insertIter(giantArrayIter[i]);

                    }
                    final long iterEndTimeBST = System.currentTimeMillis();
                    System.out.println(" Total time 10000 iterative BST = " + (iterEndTimeBST - iterStartTimeBST));
                    System.out.println(" ");

                }
                

                /* 
                    This will perform tasks for question 6 all parts
                */
                if(questionSixPartB){
                    //(Part b)
                    int[] giantArrayLevelCount = myMain.getRandomArray(10000);
                   
                    AVLtree giantAvlLevelCount = new AVLtree(giantArrayLevelCount[0]);
                    BinarySearchTree giantBstLevelCount = new BinarySearchTree(giantArrayLevelCount[0]);

                    System.out.println("Printing results, question 6 Part B...(array 10000) level count..");
                   
                    for(int i = 1; i < giantArrayLevelCount.length; i++){
                        giantAvlLevelCount.insertIter(giantArrayLevelCount[i]);
                    }
                    System.out.println(" Total level count avl tree = "+ giantAvlLevelCount.levelCounter);

                    for(int i = 1; i < giantArrayLevelCount.length; i++){
                        giantBstLevelCount.insertIter(giantArrayLevelCount[i]);
                    }
                    System.out.println(" Total level binary tree = " + giantBstLevelCount.levelCounter);
                    System.out.println(" ");

                }
                
                if(questionSixPartC){
                    //(Part C)
                    int[] sortedArray = myMain.getSortedArray(10000);
                    AVLtree avl = new AVLtree(sortedArray[0]);
                    BinarySearchTree bst = new BinarySearchTree(sortedArray[0]);

                    System.out.println("Printing results, question 6 Part C...");
                    for(int i = 1; i < sortedArray.length; i++){
                        avl.insertIter(sortedArray[i]);
                    }
                    System.out.println(" Total levels avl : " + avl.levelCounter);
                    for(int i = 1; i < sortedArray.length; i++){
                        bst.insertIter(sortedArray[i]);
                    }
                    
                    System.out.println(" Total Levels bst : " + bst.levelCounter);
                    System.out.println(" ");
    
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
                myAvLtree.inorder(myAvLtree.root);
                System.out.println(" ");
                
            }
           
            
    }
}