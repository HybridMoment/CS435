2c i.) We must use recursive functions in order to achieve the following, insertion, deletion, finding next value, finding prev value, find the min value, and find the max value in a BST
I assumed all input would be integer values. 
When constructing the BST object you must give it a root value, this will prevent null trees. 

ii.) Edge cases to consider, when deleting we must consider if the node is a leaf, has on child, or has two children.
avoid creating a null tree.

iii.) 10, 5, 15,14,13,18,25 =>      10
                                5       15
                                    14      18
                                13              25

    
    10,5,15,3,20    =>                  10
                                    5       15
                                3               20

iv.)
    insertRec:  if curr. data < data:
                        recur left
                else
                        recur right
    deleteRec: if leaf return null
            if has one child return child
            if two children recurse on right childs leftmost child
    
    findNextRec:    do inorder traversal return node that comes after the value

    findPrevRec: do inorder traversal and store prev nodes, return prev when you find the value given

    findminRec: traverse all the way left and return

    findMaxREc: traverse to rightmost node and return.

v.)
    performance issues happen during case 3 of deletion when there exists two children


vi.)
    done

vii.)
    Current issues are with delete not functioning properly. 