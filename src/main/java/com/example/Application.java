package com.example;

/**
 * <pre>
 * Loop Detection: Given a circular linked list, implement an algorithm that
 * returns the node at the beginning of the loop.
 *
 * DEFINITION:
 * Circular linked list: A (corrupt) linked list in which a node's next pointer
 * points to an earlier node, so as to make a loop in the linked list.
 *
 * EXAMPLE
 * Input: A -> B -> C -> D -> E -> C [the same C as earlier]
 * Output: C
 * </pre>
 */
public class Application {
    /**
     * Basic Node implementation
     */
    private static class Node {
        private int value;

        private Node next = null;

        Node(){}

        Node(int v) {
            this.value = v;
        }
        public int value(){
            return this.value;
        }

        public void value(int i){
            this.value = i;
        }

        public Node next(){
            return this.next;
        }

        public void next(Node n){
            this.next = n;
        }

    }

    /**
     * Basic Singly Linked List implementation.
     */
    private static class SinglyLinkedList {
        private Node head = null;
        private Node tail = null;
        private long size = 0;

        public long size(){
            return this.size;
        }

        public long add(Node n){
            if (head == null) {
                this.head = n;
                this.tail = this.head;
            } else {
                tail.next(n);
                tail = tail.next;
            }
            return ++this.size;
        }

        public Node get(int i){
            if (i > size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }

            Node current = this.head;
            for(int n = 0; n < i; n++){
                if (current == null) break;
                current = current.next;
            }

            return current;
        }
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[10];

        // generate nodes that will be stored in the linked list
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(i);
            System.out.println("Node #" + i + " address = " + nodes[i]);
        }
        System.out.println();

        // create the first linked list
        SinglyLinkedList linkNodes1 = new SinglyLinkedList(){
            {
                this.add(nodes[0]);
                this.add(nodes[2]);
                this.add(nodes[4]);
                this.add(nodes[1]);
                this.add(nodes[3]);
                this.add(nodes[5]);
                this.add(nodes[6]);

                // loop starts here
                this.add(nodes[2]);   // index = 7, zero based

            }
        };

        System.out.println("Address = " + getLoopingNode(linkNodes1));
    }

    private static Node getLoopingNode(SinglyLinkedList n){
        Node output = null;
        if (n != null){
            for(int i = 0; i < n.size() - 1; i++){
                Node current = n.get(i);

                for(int y = i+1; y < n.size(); y++){
                    if(n.get(y) == current){
                        output = current;
                        System.out.println("Found loop at index = " + y);
                        System.out.println("Node #" + (i+1) + " with value [" + current.value() +"] and address {" + current + "}");
                        break;
                    }
                }
            }
        }
        return output;
    }
}
