package dsatrees;

public class Main {
    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("=== Insert Tests ===");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Testing duplicates
        bst.insert(30);
        bst.insert(30);

        System.out.print("Inorder after inserts: ");
        bst.inorderPrint(); // Expected (sorted):
        // 20 30 30 30 40 50 60 70 80 (but with counts)

       /* System.out.println("\n=== Delete Tests ===");
        System.out.println("Delete key 20 (leaf)");
        bst.delete(20);
        bst.inorderPrint();

        System.out.println("Delete key 30 (3 duplicates)");
        bst.delete(30); // decreases count, not remove node
        bst.inorderPrint();

        System.out.println("Delete key 30 again");
        bst.delete(30);
        bst.inorderPrint();

        System.out.println("Delete key 30 again (now count should become 1)");
        bst.delete(30);
        bst.inorderPrint();

        System.out.println("Delete key 50 (root with 2 children)");
        bst.delete(50);
        bst.inorderPrint();

        */
        System.out.println("Inorder (list) = " + bst.inorder());
        System.out.println("Preorder: " + bst.preorder());
        System.out.println("Postorder: " + bst.postorder());
    }
}
