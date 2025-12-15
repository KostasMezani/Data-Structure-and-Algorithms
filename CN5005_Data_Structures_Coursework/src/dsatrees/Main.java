import dsatrees.AVLTree;

public static void main(String[] args) {
    AVLTree avl = new AVLTree();

    avl.insert(10);
    avl.insert(20);
    avl.insert(30);
    avl.insert(40);
    avl.insert(50);
    avl.insert(25);

    System.out.println("Preorder before delete: " + avl.preorder());
    System.out.println("Inorder before delete: " + avl.inorder());

    System.out.println();

    avl.delete(40);
    System.out.println("Preorder after delete(40): " + avl.preorder());
    System.out.println("Inorder after delete(40): " + avl.inorder());

    System.out.println();

    avl.delete(30);
    System.out.println("Preorder after delete(30): " + avl.preorder());
    System.out.println("Inorder after delete(30): " + avl.inorder());
}
