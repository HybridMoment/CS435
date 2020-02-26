import java.util.*;

public class Main{

    public void getRandomArray(int n){
        
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

        System.out.println(Arrays.toString(arr));
    }

    public void getSortedArray(int n){
        int[] arr = new int[n];
        
        for(int i = 0; i < arr.length; i++){
            arr[i] = n;
            n = n -1;
        }

        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args){

            Main myMain = new Main();
            myMain.getSortedArray(10);
            myMain.getRandomArray(10);

            BinarySearchTree myTree = new BinarySearchTree(10);

            boolean DEBUG_ITER = true;
            boolean DEBUG_RECR = false;

            if(DEBUG_ITER){
                myTree.insertIter(5);
                myTree.insertIter(15);
                myTree.insertIter(20);
                myTree.insertIter(14);
                myTree.insertIter(13);
                myTree.insertIter(18);
                myTree.insertIter(25);
                myTree.findMinIter();
                myTree.findMaxIter();

                myTree.deleteIter(10);
                myTree.inorder(myTree.root);

            }

            if(DEBUG_RECR){
                System.out.println("Starting Recursive Tree");
                BinarySearchTree recurTree = new BinarySearchTree(10);
                recurTree.insert(5);
                recurTree.insert(15);
                recurTree.findMin();
                recurTree.findMax();
                recurTree.insert(20);
                recurTree.insert(3);
                recurTree.findNext(10);
                recurTree.findNext(15);
                recurTree.findPrev(10);
                recurTree.findPrev(15);
            }
            
    }
}