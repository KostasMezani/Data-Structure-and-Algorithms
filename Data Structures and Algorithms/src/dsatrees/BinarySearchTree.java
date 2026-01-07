package dsatrees;

import java.util.ArrayList;
import java.util.List;

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

    private Node deleteRecursive(Node current, int key) {
        // 1. Αν το υποδέντρο είναι άδειο, δεν υπάρχει τίποτα να διαγράψουμε
        if (current == null) {
            return null;
        }

        // 2. Πλοήγηση στο σωστό branch, όπως στην insert
        if (key < current.getKey()) {
            current.setLeft(deleteRecursive(current.getLeft(), key));
            return current;
        } else if (key > current.getKey()) {
            current.setRight(deleteRecursive(current.getRight(), key));
            return current;
        }

        // 3. Εδώ φτάνουμε ΜΟΝΟ αν key == current.getKey()
        //    Άρα αυτός είναι ο κόμβος που μας ενδιαφέρει.

        // 3α. Αν υπάρχουν πολλαπλές εμφανίσεις (count > 1),
        //     απλώς μειώνουμε το count και τελειώσαμε
        if (current.getCount() > 1) {
            current.setCount(current.getCount() - 1);
            return current;
        }

        // Από εδώ και κάτω είμαστε στην περίπτωση count == 1,
        // άρα πρέπει να αφαιρέσουμε πλήρως τον κόμβο από το δέντρο.

        // 3β. Περίπτωση 0 παιδιά (φύλλο)
        if (current.getLeft() == null && current.getRight() == null) {
            return null;
        }

        // 3γ. Περίπτωση 1 παιδί: μόνο δεξί παιδί
        if (current.getLeft() == null) {
            return current.getRight();
        }

        // 3δ. Περίπτωση 1 παιδί: μόνο αριστερό παιδί
        if (current.getRight() == null) {
            return current.getLeft();
        }

        // 3ε. Περίπτωση 2 παιδιά
        // Βρίσκουμε τον inorder successor = το μικρότερο στοιχείο στο δεξί υποδέντρο
        Node successor = findMin(current.getRight());

        // Αντιγράφουμε το key και το count του successor στον τωρινό κόμβο
        current.setKey(successor.getKey());
        current.setCount(successor.getCount());

        // Θέλουμε να αφαιρέσουμε ΟΛΕΣ τις εμφανίσεις αυτού του successor κόμβου από κάτω,
        // οπότε πρώτα τον "μηδενίζουμε" (count = 1) και μετά κάνουμε μια delete
        successor.setCount(1);

        // Διαγράφουμε μία εμφάνιση του successor key από το δεξί υποδέντρο
        current.setRight(deleteRecursive(current.getRight(), successor.getKey()));

        return current;
    }


    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private void inorderRecursive(Node node, List<String> result){
        if(node == null){
            return;
        }
        //Διασχίζουμε πρώτα το αριστερό υποδέντρο
        inorderRecursive(node.getLeft(), result);

        // Επισκοπτόμαστε τον τρέχοντα κόμβο (key(count))
        result.add(node.getKey() + "(" + node.getCount() + ")" );

        // Στο τέλος διαασχίζουμε το δεξί υποδέντρο
        inorderRecursive(node.getRight(), result);

    }

    public List<String> inorder(){
        List<String> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void preorderRecursive(Node node, List<String> result){
        if (node == null){
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

    private void postorderRecursive(Node node, List<String> result){
        if (node == null){
            return;
        }

        postorderRecursive(node.getLeft(), result);

        postorderRecursive(node.getRight(),result);

        result.add(node.getKey() + "(" + node.getCount() + ")" );
    }

    public List<String> postorder(){
        List<String> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void inorderPrintRecrusive(Node node){
        if(node == null){
            return;
        }
        inorderPrintRecrusive(node.getLeft());
        System.out.println(node.getKey() + "(" + node.getCount() + ") ");
        inorderPrintRecrusive(node.getRight());
    }

    public void inorderPrint(){
        inorderPrintRecrusive(root);
        System.out.println();
    }
}
