package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
          Customer customer=customerRepository2.findById(customerId).get();

		  List<TripBooking> tripBookingList=customer.getTripBookingList();

		  for(TripBooking trip:tripBookingList){
			  Driver driver=trip.getDriver();
			  Cab cab=driver.getCab();
			  cab.setAvailabile(true);
			  driverRepository2.save(driver);
			  trip.setTripStatus(TripStatus.CANCELED);
		  }
		  customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
      List<Driver> driverList=driverRepository2.findAll();
	  Driver driver=null;

	  for(Driver currDriver:driverList){
		  if(currDriver.getCab().getAvailabile()){
			  if(currDriver==null||currDriver.getDriverId()<driver.getDriverId()){
				  driver=currDriver;
			  }
		  }
	  }
	  if(driver==null){
		  throw new Exception("No cab available!");
	  }

	  TripBooking tripBooking=new TripBooking();
	  tripBooking.setCustomer(customerRepository2.findById(customerId).get());
	  tripBooking.setFromLocation(fromLocation);
	  tripBooking.setToLocation(toLocation);
	  tripBooking.setDistanceInKm(distanceInKm);
	  tripBooking.setDriver(driver);
	  tripBooking.setTripStatus(TripStatus.CONFIRMED);
	  int rate=driver.getCab().getPerKmRate();
	  tripBooking.setBill(rate*distanceInKm);


	  driver.getCab().setAvailabile(false);
	  driverRepository2.save(driver);

	  Customer customer=customerRepository2.findById(customerId).get();
	  customer.getTripBookingList().add(tripBooking);
	  customerRepository2.save(customer);

	  tripBookingRepository2.save(tripBooking);
	  return tripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
     TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
	  tripBooking.setTripStatus(TripStatus.CANCELED);
	  tripBooking.setBill(0);
	  tripBooking.getDriver().getCab().setAvailabile(true);
	  tripBookingRepository2.save(tripBooking);

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
         TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
		 tripBooking.setTripStatus(TripStatus.COMPLETED);
		 tripBooking.getDriver().getCab().setAvailabile(true);
		 tripBookingRepository2.save(tripBooking);
	}
}
