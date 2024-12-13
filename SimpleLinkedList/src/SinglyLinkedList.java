
public class SinglyLinkedList {

//    -------- private field(s) --------

    private Node head;

//    -------- public methods --------

    public SinglyLinkedList(){
      this.head = null;
    }

    public void pushFront(Object data){
        head  = new Node(data, head);
    }

    public Object popFront(){

        Node new_head = head.next;
        Node node_to_remove = head;
        head = new_head;

        return node_to_remove.data;
    }

    public int size(){
        ListIterator curr = new ListIteratorIMP(head);
        int count = 0;
        while(curr.hasNext()){
            ++count;
            curr.next();
        }
        return count;
    }

    public boolean isEmpty(){
        return 0 == size();
    }

    public ListIterator begin(){

        return new ListIteratorIMP(head);
    }

    public ListIterator find(Object dataToMatch){

        ListIterator runner = new ListIteratorIMP(head);
        ListIterator prev = new ListIteratorIMP(head);
        while(runner.hasNext()){

            if(runner.next().equals(dataToMatch)){
                return prev;
            }
            prev.next();
        }
        return null;
    }

    //    -------- inner class Node --------
    private static class Node{
        private Object data;
        private Node next;

        public Node(Object data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    //    -------- inner class LisIteratorIMP --------
    private static class ListIteratorIMP implements ListIterator{

        private Node node;

        public ListIteratorIMP(Node node){
            this.node = node;
        }


        @Override
        public boolean hasNext(){
            return node != null;
        }

        @Override
        public Object next(){

            Object curr_data = node.data;
            node = node.next;
            return curr_data;
        }
    }
}




