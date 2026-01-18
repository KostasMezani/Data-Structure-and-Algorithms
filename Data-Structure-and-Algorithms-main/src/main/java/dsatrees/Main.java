package dsatrees;

public class Main{
    public static void main(String[] args) {
        // AM keys + επιπλέον τιμές για πιο ενδιαφέρον demo με duplicates
        int [] amKeys = {10, 4, 18, 43, 32, 10, 25, 4, 50, 18, 32, 7};

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         CN5005 - Data Structures Demo Program             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // ---- BST ----
        System.out.println("=== BINARY SEARCH TREE (BST) ===\n");
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("1. Εισαγωγή κόμβων (12 inserts με duplicates):");
        for (int i = 0; i < amKeys.length; i++) {
            bst.insert(amKeys[i]);
            System.out.println("   Inserted: " + amKeys[i]);
        }
        System.out.println();

        System.out.println("2. Διασχίσεις BST:");
        System.out.println("   Inorder   : " + bst.inorder());
        System.out.println("   Preorder  : " + bst.preorder());
        System.out.println("   Postorder : " + bst.postorder());
        System.out.println();

        System.out.println("3. Διαγραφή κόμβου με key = 18 (έχει count=2):");
        bst.delete(18);
        System.out.println("   Inorder μετά τη διαγραφή: " + bst.inorder());
        System.out.println("   (Το 18 μειώθηκε σε count=1)");
        System.out.println();

        System.out.println("4. Διαγραφή ξανά του 18 (τώρα θα φύγει εντελώς):");
        bst.delete(18);
        System.out.println("   Inorder μετά τη διαγραφή: " + bst.inorder());
        System.out.println();

        System.out.println("5. Διαγραφή κόμβου με key = 43 (leaf node):");
        bst.delete(43);
        System.out.println("   Inorder μετά τη διαγραφή: " + bst.inorder());
        System.out.println();

        System.out.println("════════════════════════════════════════════════════════════\n");

        // ---- AVL ----
        System.out.println("=== AVL TREE (Self-Balancing) ===\n");
        AVLTree avl = new AVLTree();

        System.out.println("1. Εισαγωγή κόμβων (12 inserts με duplicates):");
        for(int i = 0; i < amKeys.length; i++){
            avl.insert(amKeys[i]);
            System.out.println("   Inserted: " + amKeys[i]);
        }
        System.out.println();

        System.out.println("2. Διασχίσεις AVL:");
        System.out.println("   Inorder   : " + avl.inorder());
        System.out.println("   Preorder  : " + avl.preorder());
        System.out.println("   Postorder : " + avl.postorder());
        System.out.println();

        System.out.println("3. Διαγραφή κόμβου με key = 32 (έχει count=2):");
        avl.delete(32);
        System.out.println("   Inorder μετά τη διαγραφή: " + avl.inorder());
        System.out.println("   (Το 32 μειώθηκε σε count=1)");
        System.out.println();

        System.out.println("4. changeKey: Αλλαγή 25 → 60:");
        boolean changed = avl.changeKey(25, 60);
        System.out.println("   Success: " + changed);
        System.out.println("   Inorder μετά το changeKey: " + avl.inorder());
        System.out.println();

        System.out.println("5. Διαγραφή κόμβου με key = 10 (έχει count=2):");
        avl.delete(10);
        System.out.println("   Inorder μετά τη διαγραφή: " + avl.inorder());
        System.out.println("   (Το 10 μειώθηκε σε count=1)");
        System.out.println();

        System.out.println("════════════════════════════════════════════════════════════\n");
        System.out.println("Demo completed successfully!");
        System.out.println();
    }
}