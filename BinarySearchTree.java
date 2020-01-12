
/* BST for Interviews : Adarsh_Kanojiya
   This code doesn't handel duplicate keys
   O(n) in every operation.
   Morris Method can be use to traverse without stack and recursion
*/
class BST {
    private Node root;
    private ArrayList<Integer> inOrder, postOrder, preOrder;

    BST() {
        root = null;
    }

    Node search(int key) {
        return search(root, key);
    }

    void insert(int key) {
        root=insert(root, key);// assign to root(root never changes in bst)
    }

    ArrayList<Integer> inOrder() {//sorted order traversal only in bst
        inOrder = new ArrayList<>();
        inorder(root);
        return inOrder;
    }

    ArrayList<Integer> postOrder() {
        postOrder = new ArrayList<>();
        postOrder(root);
        return postOrder;
    }

    ArrayList<Integer> preOrder() {
        preOrder = new ArrayList<>();
        preOrder(root);
        return preOrder;
    }

    private Node insert(Node root, int key) {// insertion takes place at leaf nodes
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }
    /*
    Deletion in BST:(Its easy to delete leaf node)
    1. Find the node to be deleted. Lets call it D.
    2. If it has at_most one child, copy or return or attach that child to D's parent
       and D goes to garbage Collector.
    3. If it has two children, then go to its right subtree and find minimum in it.
       Overwrite D's key with minimum key and delete minimum key from right subtree.
       We chose D's right subtree's minimum key because we need someone in D's place
       satisfying D's properties i.e., chosen key must be greater than D's left key
       and smaller than D's right key.
       We can also choose maximum key in D's left subtree.
    4. Optimization: We can find minimum node and its parent in right subtree and add
       and attach right subtree of minimum node(if present) to its(minimum node's) parent
       as its left child and also overwrite D's key with minimum key.
       We don't need to delete minimum Node because it automatically goes to garbage
       collector.
    */
    void delete(int key){
        root=delete(root,key);// what if root is deleted ?
    }
    private Node delete(Node root,int key){// very similar to insert
        if (root==null)return root;// if bst is empty
        if (key<root.key){
            root.left=delete(root.left,key);
        }else if (key>root.key){
            root.right=delete(root.right,key);
        }else {
            if (root.left==null){// if at_most one child is present
                return root.right;
            }else if (root.right==null){
                return root.left;
            }else {// if both children present then we delete from leaf
                root.key=minUtil(root.right);
                root.right=delete(root,root.key);
                /* OPTIMIZATION
                Node parentOfMinNode=root;
                Node minNode=root.right;
                while (minNode.left!=null){
                    parentOfMinNode=minNode;
                    minNode=minNode.left;
                }
                parentOfMinNode.left=minNode.right;// there might or might not be right child in minNode
                root.key=minNode.key;
                return root;
                */
            }
        }
        return root;
    }
    private int minUtil(Node node){
        while (node.left!=null){
            node=node.left;
        }
        return node.key;
    }


    private Node search(Node root, int key) {// check left then right or vice_versa if not
        if (root == null || root.key == key) return root;// present return null
        if (key < root.key) return search(root.left, key);
        return search(root.right, key);
    }

    private void inorder(Node root) {// (left,root,right)
        if (root == null) return;
        inorder(root.left);
        inOrder.add(root.key);
        inorder(root.right);
    }

    private void postOrder(Node root) {// (left,right,root)
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        postOrder.add(root.key);
    }
    /*
    Algorithm:
    Visualisation: we are storing all the right nodes then after that in left nodes we do the
     same i.e., we store their right nodes first(if present otherwise we store left only), IN s2
    and by reversing(or by popping) s2 we get left nodes first and then right nodes.
    1. Initially add root to s1 stack
    2. Pop elements from s1 by adding them to s2 at the same time
       til it emptied and simultaneously add left and THEN
       the right child of the popped Node
    3. Pop elements from s2 and print them, we get postOrder.
    */
    ArrayList<Integer> postOrder2Stack(){
        postOrder2Stack(root);
        return postOrder;
    }
    private void postOrder2Stack(Node root){//postOrder using 2 stacks
        if (root==null)return;//empty bst
        Stack<Node> s1=new Stack<>();
        Stack<Node> s2=new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()){
            root=s1.pop();
            s2.push(root);//result stack
            if (root.left!=null)s1.push(root.left);
            if (root.right!=null)s1.push(root.right);
        }
        postOrder=new ArrayList<>();
        while (!s2.isEmpty()){
            postOrder.add(s2.pop().key);
        }
    }
    /*
    Algorithm:
    1. From root, we store all left Nodes it we hit left_null
    2. Check if node with left_null have any right child.
    3. If it don't, then we add print this node and also check if
       its the right child of the current peek node. If it is, then
       then the curr peek node is done i.e., left and right child
       of current peek node has been taken care. Now we can remove the
       curr peek node. Repeat it.
    4. If it has then set root = root.right and while loop will do
       the same for it by following above algorithm.
     */
    ArrayList<Integer> postOrderStack(){
        postOrderStack(root);
        return postOrder;
    }
    private void postOrderStack(Node root){// postOrder using only one stack
        postOrder=new ArrayList<>();
        Stack<Node> s=new Stack<>();
        while (root!=null || !s.isEmpty()){
            if (root!=null){
                s.push(root);
                root=root.left;
            }else {
                Node tmp=s.peek().right;
                if (tmp==null){
                    tmp=s.pop();
                    postOrder.add(tmp.key);
                    while (!s.isEmpty() && tmp==s.peek().right){
                        tmp=s.pop();
                        postOrder.add(tmp.key);
                    }
                }else root=tmp;
            }
        }
    }
    ArrayList<Integer> preOrderStack(){
        preOrderStack(root);
        return preOrder;
    }
    private void preOrderStack(Node root){// add rigth then left to s
        if (root==null)return;
        preOrder=new ArrayList<>();
        Stack<Node> s=new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            root = s.pop();
            preOrder.add(root.key);
            if (root.right != null) s.push(root.right);
            if (root.left != null) s.push(root.left);
        }
    }
    ArrayList<Integer> inOrderStack(){
        inOrderStack(root);
        return inOrder;
    }
    private void inOrderStack(Node root){//first add all lefts and pop them, then add their rights
        inOrder=new ArrayList<>();
        Stack<Node> s=new Stack<>();
        while (root!=null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            root=s.pop();
            inOrder.add(root.key);
            root=root.right;
        }
    }
    private void preOrder(Node root) {// (root,left,right)
        if (root == null) return;
        preOrder.add(root.key);
        preOrder(root.left);
        preOrder(root.right);
    }
}

class Node {
    int key;
    Node left, right;

    Node(int key) {
        left = right = null;
        this.key = key;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        FastReader sc = new FastReader();
        BST bst = new BST();
        for (int i=1;i<6;i++){
            bst.insert(i);
        }
        ArrayList<Integer> inorder=bst.inOrderStack();
        ArrayList<Integer> postorder=bst.postOrderStack();
        ArrayList<Integer> preorder=bst.preOrderStack();
        System.out.print("Inorder: ");
        for (int i:inorder) System.out.print(i+" ");
        System.out.println();
        System.out.print("Post Order: ");
        for (int i:postorder) System.out.print(i+" ");
        System.out.println();
        System.out.print("Preorder: ");
        for (int i:preorder) System.out.print(i+" ");
        System.out.println();
        bst.delete(1);
        System.out.println(bst.search(1)==null);


    }
}
