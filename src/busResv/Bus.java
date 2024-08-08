package busResv;

public class Bus {
   private int busNo;
   private Boolean AC;
   private int capacity; //get and set 
   
   //constructor 
   Bus(int no,boolean ac,int cap){
	   this.busNo = no;
	   this.AC = ac;
	   this.capacity = cap;
   }
   
   public int getCapacity() { //accessor method
	   return capacity;
   }
   
   public void setCapacity(int cap) { //mutuator
	   capacity = cap;
   }
   
   public boolean getAC() {
	   return AC;
   }
   
   public void setAC(boolean val) {
	   AC = val;
   }
   
   public void displayBusInfo() {
	   System.out.println("Bus No:"+busNo+" AC:"+AC+" Total Capacity:"+capacity);
   }
   
   public int getBusNo() {
	   return busNo;
   }
}
