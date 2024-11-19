// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Node {
  Phone info;
  Node next;
  Node() {
   }
  Node(Phone x, Node p) {
    info=x;next=p;
   }
  Node(Phone x) {
    this(x,null);
   }
 }

