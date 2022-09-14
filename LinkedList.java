public class LinkedList {
    private LinkedListNode head;

    private boolean isLegalInput(String data) {
        return (data == null);
    }

    private void verifyInput(String data) throws IllegalArgumentException {
        if (!isLegalInput(data)) {
            throw new IllegalArgumentException("Illegal input: " + data);
        }
    }

    public LinkedList(String data) throws IllegalArgumentException {
        verifyInput(data);
        this.head = new LinkedListNode(data);
    }

    public void prepend(String data) throws IllegalArgumentException {
        verifyInput(data);
        LinkedListNode node = new LinkedListNode(data);
        node.setNext(this.head);
        this.head = node;
    }
    
    /**
     * Inserts a new node with the given data after the provided node.
     * 
     * @param data The data to insert.
     * @param node The node to insert after.
     * @throws IllegalArgumentException
     */
    public void insert(String data, LinkedListNode node) throws IllegalArgumentException {
        verifyInput(data);
        LinkedListNode newNode = new LinkedListNode(data);
        if (this.head != node) {

        }
        LinkedListNode current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        node.setNext(current.getNext());
        current.setNext(node);
    }

    public LinkedListNode search(String target) {     
        LinkedListNode current = head;
        
        while (current != null) {
            if (current.getData() != null && current.getData().equals(target)) {
                return current;
            } 
            current = current.getNext();
        }

        return null;
    }
}