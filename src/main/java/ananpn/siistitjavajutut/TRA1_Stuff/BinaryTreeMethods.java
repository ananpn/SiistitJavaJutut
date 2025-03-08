package ananpn.siistitjavajutut.TRA1_Stuff;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeMethods {

    public static <E extends Comparable<? super E>> void testWhatever(BTree<E> T) {
        ArrayList<E> resultList = new ArrayList<>();
        BTreeNode<E> root = T.getRoot(); //O(1)
        //preOrderTraverse(root, resultList); //O(n)
        //System.out.println("preOrder = " + resultList);
        resultList.clear();
        postOrderTraverse(root, resultList); //O(n)
        System.out.println("postOrder = " + resultList);
        //System.out.println("postOrder = " + resultList.reversed());

    }



    public <E> void inOrderTraverse(BTreeNode<E> node, ArrayList<E> resultList) {
        if (node != null){
            inOrderTraverse(node.getLeftChild(), resultList);
            resultList.add(node.getElement());
            inOrderTraverse(node.getRightChild(), resultList);
        }
    }

    static <E> BTreeNode<E> levelOrderIterateWithQueue(BTreeNode<E> root) {
        Queue<BTreeNode<E>> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            // removes first
            BTreeNode<E> currentNode = queue.poll();
            if (currentNode != null) {

                BTreeNode<E> leftChild = currentNode.getLeftChild();
                BTreeNode<E> rightChild = currentNode.getRightChild();
                if (leftChild != null) {
                    queue.add(leftChild);
                }
                if (rightChild != null) {
                    queue.add(rightChild);
                }
            }
        }
        return null;
    }

    public static <E> void preOrderTraverse(BTreeNode<E> node, ArrayList<E> resultList) {
        if (node != null){
            resultList.add(node.getElement());
            preOrderTraverse(node.getLeftChild(), resultList);
            preOrderTraverse(node.getRightChild(), resultList);
        }
    }

    public static <E> void postOrderTraverse(BTreeNode<E> node, ArrayList<E> resultList) {
        if (node != null) {
            postOrderTraverse(node.getLeftChild(), resultList);
            postOrderTraverse(node.getRightChild(), resultList);
            resultList.add(node.getElement());
        }


    }






}
