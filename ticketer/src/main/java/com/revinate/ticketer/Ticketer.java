package com.revinate.ticketer;

import java.util.HashSet;
import java.util.Set;

public class Ticketer {

	private Integer remainingTicketCount;

    /**
     * Create a new ticketer by setting the initial amount of available tickets.
     * A ticket should consist of a unique string.
     *
     * @param initialAmountOfTickets Number of tickets this ticketer will contain initially
     */
    public Ticketer(int initialAmountOfTickets) {
    	
    	remainingTicketCount=initialAmountOfTickets;
    }

    /**
     * Buys a certain number of tickets. If the number of available tickets is smaller than the
     * number of tickets to buy, then all the remaining tickets are sold.
     * <p>
     * This method should return empty if there are no tickets available.
     * <p>
     *
     * @param numberOfTickets The number of tickets to buy
     * @return The purchased tickets
     */
    public Set<String> buy(int numberOfTickets) {
    	
    	Set<String> tickets = new HashSet<String>();
    	int ticketToDisburse;
    	
    	// Applying synchronized block over shared resource i.e. ticket count, 
    	// so that only single thread can access it at a time to avoid data read/write inconsistency
    	synchronized (remainingTicketCount) {
    		
    		// if remaining tickets are more than required tickets, satisfy the request. 
    		// Else sell whatever is available.
        	if(remainingTicketCount>=numberOfTickets)
        		ticketToDisburse=numberOfTickets;
        	else
        		ticketToDisburse=remainingTicketCount;

    		for(int index=0;index<ticketToDisburse;index++)
    			tickets.add("Ticket"+(remainingTicketCount-index));
    		remainingTicketCount-=numberOfTickets;	
		}
		return tickets;
    }
    
}
