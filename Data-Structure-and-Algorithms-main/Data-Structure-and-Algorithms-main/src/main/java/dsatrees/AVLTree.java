package dsatrees;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private Node root;


    public AVLTree() {
        this.root = null;
    }

    private  int height(Node n){
        return n==null ? 0 :n.getHeight();
    }

    private void updateHeight(Node n){
        int leftHeight = height(n.getLeft());
        int rightHeight = height(n.getRight());
        n.setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    private int balanceFactor(Node n){
        return height(n.getLeft()) - height(n.getRight());
    }

    private Node rotateRight(Node y){
        Node x = y.getLeft();
        Node t2 = x.getRight();

        //Rotation
        x.setRight(y);
        y.setLeft(t2);

        //Update heights (πρώτα ο y, μετά ο x)
        updateHeight(y);
        updateHeight(x);

        return x; //νέα ρίζα του υποδέντρου
    }

    private Node rotateLeft(Node x){
        Node y = x.getRight();
        Node t2 = y.getLeft();

        //Rotation
        y.setLeft(x);
        x.setRight(t2);

        //Update heights (πρώτα ο x, μετά ο y)
        updateHeight(x);
        updateHeight(y);

        return y; // νέα ρίζα του υποδέντρου
    }

    private Node rebalance(Node node){
        updateHeight(node);
        int bf = balanceFactor(node);

        //Left heavy
        if(bf > 1){
            //LR case: το αριστερό παιδί είναι "βαρύ δεξιά"
            if(balanceFactor(node.getLeft()) < 0){
                node.setLeft(rotateLeft(node.getLeft()));
            }
            //LL case
            return rotateRight(node);
        }

        //Right Heavy
        if (bf < -1){
            // RL case: το δεξί παιδί είναι "βαρύ αριστερά"
            if (balanceFactor(node.getRight()) > 0){
                node.setRight(rotateRight(node.getRight()));
            }
            //RR case
            return rotateLeft(node);
        }

        //Ηδη ισορροπημένο
        return node;
    }

    private Node insertRecursive(Node current, int key){
        // 1) Κλασικό BST insert
        if(current == null){
            return new Node(key);
        }

        if(key < current.getKey()){
            current.setLeft(insertRecursive(current.getLeft(), key));
        } else if (key > current.getKey()){
            current.setRight(insertRecursive(current.getRight(), key));
        } else {
            // Duplicate: δεν αλλάζουμε δομή, μόνο count++
            current.setCount(current.getCount() + 1);

            return current;
        }

        // 2) Μετά την εισαγωγή, κάνουμε rebalance πριν επιστρέψουμε
        return rebalance(current);
    }

    public void insert(int key){
        root = insertRecursive(root, key);
    }



    public void delete(int key){
        root = deleteRecursive(root, key);
    }

    private Node findMin(Node node){
        Node current = node;
        while(current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    private Node deleteRecursive(Node current, int key){
        // 1) Αν το υποδεντρο είναι άδειο, δεν υπάρχει το key
        if (current == null){
            return null;
        }

        // 2) BST πλοήγηση: πάμε αριστερά ή δεξιά
        if(key < current.getKey()){
            current.setLeft(deleteRecursive(current.getLeft(), key));
        } else if (key > current.getKey()) {
            current.setRight(deleteRecursive(current.getRight(), key));
        } else {
            // 3) Βρέθηκε ο κόμβος με key == current.getKey()

            // 3a) Αν έχει duplicates (count > 1), μειώνουμε count και τελειώσαμε
            if(current.getCount() > 1){
                current.setCount(current.getCount() - 1);
                return current; // δομή δεν αλλάζει
            }

            // 3b) Τώρα είμαστε στην περίπτωση count == 1, άρα πρέπει να αφαιρεθεί ο κόμβος

            // Περίπτωση: 0 ή 1 παιδί
            if(current.getLeft() == null || current.getRight() == null){
                Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
                // Αν child == null -> ήταν leaf -> επιστρέφουμε null
                // Αν child != null -> επιστρέφουμε το παιδί (ο γονέας "παρακάμπτει" τον current)
                return child;
            }

            // 3c) Περίπτωση: 2 παιδιά
            // Βρίσκουμε inorder successor (min στο δεξί subtree)
            Node successor = findMin(current.getRight());

            // Μεταφέρουμε key και count του successor στον current
            current.setKey(successor.getKey());
            current.setCount(successor.getCount());

            // Θέλουμε να αφαιρέσουμε ΟΛΟ τον successor κόμβο από το δεξί subtree,
            // αλλιώς θα διπλασιαστούν τα counts.
            successor.setCount(1);

            //Διαγράφουμε τον successor από το δεξί subtree
            current.setRight(deleteRecursive(current.getRight(), successor.getKey()));
        }

        // 4) Αν μετά τη διαγραφή ο current έγινε null, γυρνάμε null
        if(current == null){
            return null;
        }

        // 5) AVL κομμάτι: κάνουμε rebalance πριν επιστρέφουμε
        return rebalance(current);
    }

    private Node findNode(Node current, int key){
        while (current != null) {
            if (key < current.getKey()){
                current = current.getLeft();
            } else if (key > current.getKey()){
                current = current.getRight();
            } else {
                return current; // Βρέθηκε
            }
        }
        return null; // Δε βρέθηκε
    }

    private Node deleteAllRecursive(Node current, int key) {
        if (current == null) return null;

        if (key < current.getKey()) {
            current.setLeft(deleteAllRecursive(current.getLeft(), key));
        } else if (key > current.getKey()) {
            current.setRight(deleteAllRecursive(current.getRight(), key));
        } else {
            // Βρέθηκε: εδώ ΑΓΝΟΟΥΜΕ το count και αφαιρούμε πλήρως τον κόμβο

            // 0 ή 1 παιδί
            if (current.getLeft() == null || current.getRight() == null) {
                Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
                return child;
            }

            // 2 παιδιά: successor
            Node successor = findMin(current.getRight());

            current.setKey(successor.getKey());
            current.setCount(successor.getCount());

            // θέλουμε να αφαιρέσουμε πλήρως τον successor από κάτω
            successor.setCount(1);
            current.setRight(deleteAllRecursive(current.getRight(), successor.getKey()));
        }

        // rebalance στο γύρισμα
        return rebalance(current);
    }

    private void deleteAll(int key) {
        root = deleteAllRecursive(root, key);
    }

    private Node insertWithCountRecursive(Node current, int key, int addCount) {
        if (current == null) {
            Node n = new Node(key);
            n.setCount(addCount);
            // height του node είναι 1 ήδη, ok
            return n;
        }

        if (key < current.getKey()) {
            current.setLeft(insertWithCountRecursive(current.getLeft(), key, addCount));
        } else if (key > current.getKey()) {
            current.setRight(insertWithCountRecursive(current.getRight(), key, addCount));
        } else {
            // υπάρχει ήδη -> πρόσθεσε στο count
            current.setCount(current.getCount() + addCount);
            return current;
        }

        return rebalance(current);
    }

    private void insertWithCount(int key, int count) {
        root = insertWithCountRecursive(root, key, count);
    }

    public boolean changeKey(int oldKey, int newKey) {
        if (oldKey == newKey) return true;

        Node node = findNode(root, oldKey);
        if (node == null) {
            return false; // δεν υπάρχει oldKey
        }

        int c = node.getCount();

        // αφαιρούμε πλήρως το oldKey
        deleteAll(oldKey);

        // εισάγουμε το newKey με το ίδιο count
        insertWithCount(newKey, c);

        return true;
    }

    private void preorderRecursive(Node node, List<String> result){
        if(node == null){
            return;
        }

        result.add(node.getKey() + "(" + node.getCount() + ")");
        preorderRecursive(node.getLeft(), result);
        preorderRecursive(node.getRight(), result);
    }

    public List<String> preorder(){
        List<String> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    public List<String> inorder() {
        List<String> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node node, List<String> result) {
        if (node == null) {
            return;
        }
        inorderRecursive(node.getLeft(), result);
        result.add(node.getKey() + "(" + node.getCount() + ")");
        inorderRecursive(node.getRight(), result);
    }

    public List<String> postorder() {
        List<String> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void postorderRecursive(Node node, List<String> result) {
        if (node == null) {
            return;
        }
        postorderRecursive(node.getLeft(), result);
        postorderRecursive(node.getRight(), result);
        result.add(node.getKey() + "(" + node.getCount() + ")");
    }

}