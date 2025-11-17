package dsatrees;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    //Ερωτημα 1.2
    private Node insertRecursive(Node root, int key){
        if(root == null){
            return new Node(key);
        }
        if(key < root.getKey()){
            root.setLeft(insertRecursive(root.getLeft(), key));
        }else if(key > root.getKey()){
            root.setRight(insertRecursive(root.getRight(), key));
        }else{
            root.setCount(root.getCount() + 1);
        }
        return root;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    //Ερώτημα 1.3

    private Node findMin(Node root){
        Node current = root;
        while(current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    private Node deleteRecursive(Node current, int key){
        if(root == null){
            return null;
        }
        if(key < root.getKey()){
            root.setLeft(deleteRecursive(root.getLeft(), key));
            return current;
        }else if(key > root.getKey()){
            root.setRight(deleteRecursive(root.getRight(), key));
            return current;
        }

        if(current.getCount() >1){
            current.setCount(current.getCount() - 1);
            return current;
        }

        //Περιπτωση 0 παιδιων
        if(current.getLeft()==null && current.getRight()==null){
            return null;
        }

        //Περιπτωση 1 παιδι: μονο δεξι παιδι
        if(current.getLeft()==null){
            return current.getRight();
        }

        //Περιπτωση 1 παιδι: μονο αριστερο παιδι
        if(current.getRight()==null){
            return current.getLeft();
        }

        // 3ε. Περίπτωση 2 παιδιά
        // Βρίσκουμε τον inorder successor = το μικρότερο στοιχείο στο δεξί υποδέντρο
        Node successor = findMin(current.getRight());

        // Μεταφέρουμε το key του successor στον τωρινό κόμβο
        current.setKey(successor.getKey());

        current.setCount(1);

        //Αφαιρουμε μια εμφανιση του successor key απο το δεξι υποδεντρο
        current.setRight(deleteRecursive(current.getRight(), successor.getKey()));

        return current;
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    public void inorder(){

    }

    public void preorder(){

    }

    public void postorder(){

    }
}
