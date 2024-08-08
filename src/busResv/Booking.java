package busResv; 
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Booking {
    String passengerName;
    int busNo;
    Date date;
    
    Booking(){
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter name of Passenger: ");
    	passengerName = sc.next();
    	
    	System.out.println("Enter bus no: ");
    	busNo = sc.nextInt();
    	
    	System.out.println("Enter Date dd-mm-yyyy: ");
    	String dateInput = sc.next();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	try {
			date = dateFormat.parse(dateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    //here reference is passed
    public boolean isAvailable() throws SQLException{
//    	int capacity = 0;
//    	for(Bus bus:buses) {
//    		if(bus.getBusNo() == busNo) {
//    			capacity = bus.getCapacity();
//    		}
//    	}
    	BusDAO busdao = new BusDAO();
    	BookingDAO bookingdao = new BookingDAO();
    	int capacity = busdao.getCapacity(busNo); //current booking bus numbers capacity is found
    		//here we are checking the new booking, how many people have booked in the same bus and date.
    		// we are storing the old booking details in b and comparing whether the old booking
    		//and new booking is same.
//    		int booked=0;
//    		for(Booking b : bookings) {
//    			if(b.busNo == busNo && b.date.equals(date)) {
//    				booked++;
//    			}
//    		}
    	int booked = bookingdao.getBookedCount(busNo,date);
    		return booked<capacity;
    }
}
