package readwrite;
import java.util.Scanner;

public class Tree{
    private class Node{
        int key;
        Node(int n){
            key=n;
        }
    }
    Node root = null;
    Tree left= null;
    Tree right= null;

    public void write(int input){
        if(root==null){
            root = new Node(input);
            return;
        }
        else if(root.key>input){
            if(left==null){
                left = new Tree();
                left.root = new Node(input);
                return;
            }
            else{
                left.write(input);
                return;
            }
        }
        else if(root.key<input){
            if(right==null){
                right = new Tree();
                right.root = new Node(input);
                return;
            }
            else{
                right.write(input);
                return;
            }
        }
        return;
    }

    public int read(int input){
        
        if(root.key>input){
            if(left==null)
                return root.key;
            else
                return left.read(input);
        }
        else if(root.key<input){
            if(right==null)
                return root.key;
            else
                return right.read(input);
        }
        return root.key;
    }

    public static void main( String[] args){
        
        Tree tree = new Tree();
        tree.write(55);
        tree.write(52);
        tree.write(47);
        tree.write(56);
        System.out.println(tree.read(53));
       
    }
}