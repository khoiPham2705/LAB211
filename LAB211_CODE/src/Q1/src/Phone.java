// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Phone {
  String id;
  int weight,price;
  Phone() {
   }
  Phone(String xId, int xWeight, int xPrice){
    id=xId;weight=xWeight; price=xPrice;
   }
  public String toString(){
    return("(" + id +","+ weight + "," + price + ")");
   }
 }
