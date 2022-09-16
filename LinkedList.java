public class LinkedList {
    private Node head;
    private int length = 1;

    /**
     * Constructor for LinkedList. Creates a new LinkedList with the provided data. Will throw an error if
     * the data is null.
     * 
     * @param data
     */
    public LinkedList(String data) {
        this.head = new Node(data);
    }

    /**
     * Adds a node at the beginning of the linked list.
     * 
     * @param data
     */
    public void prepend(String data) {
        length++;
        Node node = new Node(data);
        node.setNext(head);
        head.setPrev(node);
        this.head = node;
    }

    /**
     * Appends the provided element to the end of the linked list. That is, after the last element.
     * 
     * @param data
     * @return - The node that was appended.
     */
    public Node append(String data) {
        length++;
        Node node = new Node(data);
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(node);
        node.setPrev(current);

        return node;
    }
    
    /**
     * Inserts a new node with the given data after the provided node.
     * 
     * @param data The data to insert.
     * @param node The node to insert after.
     */
    public Node insert(String data, Node node) {
        length++;
        Node new_node = new Node(data);
        Node next_node = node.getNext();
        new_node.setPrev(node);
        node.setNext(new_node);
        if (next_node != null) {
            // We're in the middle of the list.
            next_node.setPrev(new_node);
            new_node.setNext(next_node);
        }
        
        return new_node;
    }

    /**
     * Attempt to delete the provided node from the LinkedList.
     * 
     * @param node
     */
    public void delete(Node node) {
        Node prev_node = node.getPrev();
        Node next_node = node.getNext();

        if (prev_node == null) {
            // This is the head
            deleteHead();
            return;
        }

        if (next_node == null) {
            // This is the tail
            deleteTail();
            return;
        }

        prev_node.setNext(next_node);
        next_node.setPrev(prev_node);
        node.setNext(null);
        node.setPrev(null);
        length--;
    }

    public void deleteHead() {
        length--;
        Node next_node = head.getNext();
        head.setNext(null);
        if (next_node == null) {
            // This is the only node in the list
            head = null;
            return;
        }
        next_node.setPrev(null);
        head = next_node;
    }

    public void deleteTail() {
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        Node prev_node = current.getPrev();
        prev_node.setNext(null);
        current.setPrev(null);
        length--;
    }

    /**
     * Retrieves the node at the beginning of the linked list.
     * 
     * @return Node - The node at the beginning of the linked list.
     */
    public Node popNode() {
        Node node = head;
        head = head.getNext();
        head.setPrev(null);
        length--;
        return node;
    }

    /**
     * Retrieves the data at the beginning of the linked list.
     * 
     * @return String - The data in the node at the beginning of the linked list.
     */
    public String pop() {
        return popNode().getData();
    }

    /**
     * Search the LinkedList for the the provided target data and return a `Node` object, which
     * must be passed as a parameter for the `insert` and `delete` methods.
     * 
     * @param target
     * @return - A reference to the Node.
     */
    public Node search(String target) {     
        Node current = head;
        
        while (current != null) {
            if (current.getData() != null && current.getData().equals(target)) {
                return current;
            } 
            current = current.getNext();
        }

        return null;
    }

    /**
     * Check whether the linked list contains the provided node. Mainly useful for testing.
     * 
     * @param target - The node to search for.
     * @return - true if the node is in the list, false if it's not.
     */
    public boolean contains(Node target) {
        Node current = head;

        while (current != null) {
            if (current == target) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Returns the amount of nodes present in the LinkedList.
     * 
     * @return - The amount of nodes present.
     */
    public int length() {
        return length;
    }

    public class Node {
        private String data;
        private Node next;
        private Node prev;
        
        public Node(String data) {
            this.data = data;
        }
    
        public String getData() {
            return data;
        }
    
        public Node getNext(){
            return next;
        }
        
        public Node getPrev() {
            return prev;
        }
    
        public Node setNext(Node next) {
            this.next = next;
            return this;
        }
    
        public Node setPrev(Node prev) {
            this.prev = prev;
            return this;
        }

        public String toString() {
            return "<--> " + data + " <-->";
        }
    }
}