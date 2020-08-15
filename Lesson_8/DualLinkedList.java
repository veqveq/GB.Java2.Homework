public class DualLinkedList {

    private Node head;
    private Node tail;
    private int size;
    private Iterator iterator;

    public void add(String val) {
        if (head == null) {
            head = new Node(val, null);
            tail = head;
            iterator = new Iterator(head);
        } else {
            Node newNode = new Node(tail, val);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void replace(int index, String val) {
        if (index > size - 1 || index < 0) {
            throw new IndexExceedsSizeLengthException(String.format("Индекс %d не существует при размерности списка %d", index, size));
        }
        if (index == 0) {
            if (head == null) {
                add(val);
            } else {
                head.setValue(val);
            }
        } else {
            Node currentNode = head;
            int count = 0;
            while (count != index) {
                currentNode = currentNode.getNext();
                count++;
            }
            currentNode.setValue(val);
        }
    }

    public void add(int index, String value) {
        if (index > size - 1|| index < 0) {
            throw new IndexExceedsSizeLengthException(String.format("Индекс %d не существует при размерности списка %d", index, size));
        }
        if (index == 0) {
            Node currentNode = head;
            head = new Node(value, currentNode);
            currentNode.setPrev(head);
        } else if (index == size) {
            add(value);
        } else {
            Node currentNode = head;
            int count = 0;
            while (count != index) {
                currentNode = currentNode.getNext();
                count++;
            }
            Node newNode = new Node(currentNode.getPrev(),value,currentNode);
            currentNode.getPrev().setNext(newNode);
            currentNode.setPrev(newNode);
        }
        size++;
    }

    public void delete(int index) {
        if (index == 0) {
            head = head.getNext();
        } else if (index == size - 1) {
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            Node currentNode = head;
            int count = 0;
            while (count != index) {
                currentNode = currentNode.getNext();
                count++;
            }
            currentNode.getPrev().setNext(currentNode.getNext());
            currentNode.getNext().setPrev(currentNode.getPrev());
        }
        size--;
    }

    public void delete(String val) {
        Node currentNode = head;
        int count = 0;
        while (count != size) {
            if (currentNode.getValue().equals(val)) {
                delete(count);
                return;
            }
            currentNode = currentNode.getNext();
            count++;
        }
    }

    public void deleteAll(String val) {
        Node currentNode = head;
        int count = 0;
        while (count != size) {
            if (currentNode.getValue().equals(val)) {
                delete(count);
            } else {
                count++;
            }
            currentNode = currentNode.getNext();
        }
    }

    public int getSize() {
        return size;
    }

    public Iterator iterator(){
        return iterator;
    }

    private static class Node {
        private Node prev;
        private String value;
        private Node next;

        public Node(Node prev, String value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public Node(String value, Node next) {
            this(null, value, next);
        }

        public Node(Node prev, String value) {
            this(prev, value, null);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    protected static class Iterator{
        private Node current;

        public Iterator(Node current) {
            this.current = current;
        }

        public Iterator(){}

        public boolean hasNext(){
            return current.getNext() != null;
        }

        public boolean hasPrev(){
            return current.getPrev() != null;
        }

        public String current(){
            return current.getValue();
        }

        public String next(){
            if (current.getNext() != null) {
                current=current.getNext();
            }
            return  current.getValue();
        }

        public String prev(){
            if (current.getPrev() != null) {
                current=current.getPrev();
            }
            return  current.getValue();
        }
    }

    @Override
    public String toString() {
        return "DualLinkedList{" +
                "head=" + head +
                '}';
    }
}