// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.ArrayList;
import java.util.LinkedList;
//linked link- 1->2->3->4-5->6-7
//output-> 1->4->3->2->5-6- 7
class LinkedList1{
    int val;
    LinkedList1 next;
     LinkedList1(int val, LinkedList1 node)
     {
         this.val=val;
         this.next= node;
     }
     public void printList(LinkedList1 node)
     {
         while(node!=null)
         {
             System.out.println(node.val+"-");
             node=node.next;
         }
     }
}
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        LinkedList1 head= new LinkedList1(0, null);
        LinkedList1 v1= new LinkedList1(1, null);
        head.next=v1;
        LinkedList1 v2= new LinkedList1(2, null);
        v1.next=v2;
        LinkedList1 v3= new LinkedList1(3, null);
        v2.next=v3;
        LinkedList1 v4= new LinkedList1(4, null);
        v3.next=v4;
        LinkedList1 v5= new LinkedList1(5, null);
        v4.next=v5;

        head.printList(head);
         // assuming I have v2 nad v4
        LinkedList1 p1b;
        LinkedList1 p2b;
        LinkedList1 p= head;

        while(p.next!=v2)
            p=p.next;
        p1b=p;
        p=head;
        while(p.next!=v4)
            p=p.next;
        LinkedList1 temp= v2;
        p2b=p;
        System.out.println(v2.val+", "+ v4.val+", "+ p1b.val+", "+ p2b.val);
//        p1b.next=null;
//        p2b.next=null;
//        v2.next=null;
//        v4.next=null;

        p1b.next=v4;
//        v2.next=

        head.printList(head);


    }
}