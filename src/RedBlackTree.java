public class RedBlackTree {
    Node root;

    Node rotateLeft (Node node) {
        Node child = node.rightChild;
        Node childLeft = child.leftChild;
        child.leftChild = node;
        node.rightChild = childLeft;
        return child;
    }

    Node rotateRight (Node node) {
        Node child = node.leftChild;
        Node childRight = child.rightChild;
        child.rightChild = node;
        node.leftChild = childRight;
        return child;
    }

    boolean isRed (Node node) {
        if (node == null) {
            return false;
        }
        return (node.color == true);
    }

    void swapColors (Node node1, Node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    public boolean insert (int value){
        if (root==null){
            root = new Node();
            root.value = value;
            return true;
        } else {
            return  insert(root, value);
        }
    }

    private boolean insert (Node node, int value){
        if (node.value == value){
            return false;
        } else {
            if (node.value < value) {
                if (node.rightChild != null){
                    return insert(node.rightChild, value);
                } else {
                    node.rightChild = new Node();
                    node.rightChild.value = value;
                    return true;
                }

            } else {
                if (node.leftChild != null){
                    return insert(node.leftChild, value);
                } else {
                    node.leftChild = new Node();
                    node.leftChild.value = value;
                    return true;
                }
            }
        }

        if (isRed(node.rightChild) && !isRed(node.leftChild)){
            node = rotateLeft(node);
            swapColors(node, node.leftChild);
        }

        if (isRed(node.leftChild) && isRed(node.leftChild.leftChild)){
            node = rotateRight(node);
            swapColors(node, node.rightChild);
        }

        if (isRed(node.leftChild) && isRed(node.rightChild)){
            node.color = !node.color;
            node.leftChild.color = false;
            node.rightChild.color = false;
        }
        return true;

    }

    public boolean find (int value){
        return find (root, value);
    }

    public boolean find (Node node, int value){
        if (node == null) {
            return false;
        }
        if (node.value == value){
            return true;
        }
        if (node.value < value){
            return find(node.rightChild, value);
        } else {
            return find(node.leftChild, value);
        }

    }

}

class Node {
    int value;
    Node leftChild;
    Node rightChild;
    boolean color; // RED - true, BLACK - false

    Node (int value) {
        this.value = value;
        leftChild = null;
        rightChild = null;
        color = true;
    }

}
