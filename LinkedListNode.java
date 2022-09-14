public class LinkedListNode {
    private String data;
    private LinkedListNode next;
    private LinkedListNode prev;

    public LinkedListNode(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public String getData() {
        return data;
    }

    public LinkedListNode getNext(){
        return next;
    }

    public LinkedListNode getPrev() {
        return prev;
    }

    public LinkedListNode setNext(LinkedListNode next) {
        this.next = next;
        return this;
    }

    public LinkedListNode setPrev(LinkedListNode prev) {
        this.prev = prev;
        return this;
    }
}