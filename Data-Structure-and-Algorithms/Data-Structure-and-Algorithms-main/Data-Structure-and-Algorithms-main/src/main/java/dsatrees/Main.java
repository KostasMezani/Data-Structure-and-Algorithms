package main.java.dsatrees;

public class Main{
    public static void main(String[] args) {
        int [] amKeys = {2353, 3201, 6535}; // Lefteriotis, Mezani, Pasiotis (alphabetical)
        int minInserts = 6;

        // ---- BST ----
        dsatrees.BinarySearchTree bst = new dsatrees.BinarySearchTree();
        for (int i = 0; i < minInserts; i++) {
            int key = amKeys[i % amKeys.length];
            bst.insert(key);
        }

        System.out.println("=== BST (AM keys, cyclic until 6 inserts) ===");
        System.out.println("Inorder: " + bst.inorder());
        System.out.println("Preorder: " + bst.preorder());
        System.out.println("Postorder: " + bst.postorder());
        System.out.println();


        // ---- AVL ----
        dsatrees.AVLTree avl = new dsatrees.AVLTree();
        for(int i = 0; i < minInserts; i++){
            int key = amKeys[i % amKeys.length];
            avl.insert(key);
        }

        System.out.println("=== AVL (AM keys, cyclic until 6 inserts) ===");
        System.out.println("Inorder: " + avl.inorder());
        System.out.println("Preorder: " + avl.preorder());
        System.out.println("Postorder: " + avl.postorder());

    }
}