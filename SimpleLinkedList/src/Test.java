
public class Test {
    public static void main(String[] args){
        int first = 1;
        int second = 2;
        int third = 3;
        int forth =4;

        SinglyLinkedList list = new SinglyLinkedList();
        System.out.println("size of list ="+list.size());
        System.out.println("list is empty "+list.isEmpty());

        list.pushFront(first);
        list.pushFront(second);
        list.pushFront(third);
        list.pushFront(forth);
        if(list.size() != 4 ){
            System.out.println("size of list incorrect ="+list.size());
        }
        else{
            System.out.println("size test passed");
        }
        if(list.isEmpty()){
            System.out.println("list is empty failed "+list.isEmpty());

        }
        else{
            System.out.println("list is empty passed "+list.isEmpty());
        }
        Object curr_obj = list.popFront();
        System.out.println("removed data = " +curr_obj);
        curr_obj = list.popFront();
        System.out.println("removed data = " +curr_obj);
        curr_obj = list.popFront();
        System.out.println("removed data = " +curr_obj);
        curr_obj = list.popFront();
        System.out.println("removed data = " +curr_obj);
        curr_obj = list.popFront();
        System.out.println("removed data = " +curr_obj);

        //insert again 4 3 2 1
        list.pushFront(first);
        list.pushFront(second);
        list.pushFront(third);
        list.pushFront(forth);



         ListIterator iterator = list.begin();
        System.out.println("begin= " +iterator.next());
        System.out.println("begin= " +iterator.next());
        System.out.println("begin= " +iterator.next());
        System.out.println("begin= " +iterator.next());
        System.out.println("begin= " +iterator.next());

        iterator = list.find(second);

        System.out.println("find= " +iterator.next());

        iterator = list.begin();
        System.out.println("has next " +iterator.hasNext()+" data ="+iterator.next());
        System.out.println("has next " +iterator.hasNext()+" data ="+iterator.next());
        System.out.println("has next " +iterator.hasNext()+" data ="+iterator.next());
        System.out.println("has next " +iterator.hasNext()+" data ="+iterator.next());
        System.out.println("has next " +iterator.hasNext()+" data ="+iterator.next());





    }





}
