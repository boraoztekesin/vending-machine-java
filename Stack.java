import java.util.ArrayList;
//custom stack class. Every part has a stack to store items.
public class  Stack {
    Node top;
    private int size;
    ArrayList<Item> items =new ArrayList<>();
    private class Node{
        Item item;
        private Node next;
        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public void push(Item item) {
        items.add(item);
        Node tempNode = new Node(item);
        tempNode.next = top;
        top  = tempNode;
        size++;
    }
    public Item pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        Item result = top.item;
        top = top.next;
        size--;
        return result;
    }
    public void display()
    {
        // check for stack underflow
        if (top == null) {
            System.out.println("");
        }
        else {
            Node temp = top;
            while (temp != null) {
                System.out.println( temp.item.id);
                temp = temp.next;
            }
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
}


