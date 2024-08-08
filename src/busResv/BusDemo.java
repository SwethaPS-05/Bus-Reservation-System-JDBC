package busResv;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.*;
public class BusDemo {
	public static void main(String[] args) {
	
		BusDAO busdao = new BusDAO(); 
		try {
		busdao.displayBusInfo();

		Scanner sc = new Scanner(System.in);
		int userOpt =1;
		
        while(userOpt == 1) {
            System.out.println("Enter 1 to Book and 2 to exit");
            userOpt = sc.nextInt();
            if(userOpt == 1) {
            	Booking booking = new Booking();
            	if(booking.isAvailable()) {
//            		bookings.add(booking);
            		BookingDAO 	bookingdao = new BookingDAO();
            		bookingdao.addBooking(booking);
            		System.out.println("Your booking is confirmed");
            	} else {
            		System.out.println("Sorry, Seat is not available. Try another bus or date");
            	}
            }
	}
	sc.close();
		} catch(Exception e) {
			System.out.println(e);
		}
}
}
