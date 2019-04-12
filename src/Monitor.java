/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
//import java.util.*;
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
	public enum states {Eating, Hungry, Thinking, Talking,Sleeping};
	//ArrayList<states> philStates;
	states[] philStates ;
	int numOfPhils;
	boolean talking = false;
	int numbShake= 2;
	int sleeping = 0;
	
	
	
	
	
	

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		this.numOfPhils = piNumberOfPhilosophers;
		//philStates = new ArrayList<states>(piNumberOfPhilosophers);
		philStates = new states[piNumberOfPhilosophers];
		for(int i = 0; i< piNumberOfPhilosophers; i++) {
			//philStates.add(states.Thinking);
			philStates[i] = states.Thinking;
		}
		talking = false;
		sleeping =0;
		
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		try {
		
			int index = piTID-1;
		
			while(philStates[(index-1+numOfPhils)% numOfPhils] == states.Eating || 
					philStates[(index+1)% numOfPhils] == states.Eating|| philStates[(index-1+numOfPhils)% numOfPhils]== states.Hungry) {
					philStates[index]= states.Hungry;
					wait();
			}		
			philStates[index]= states.Eating;
			//philStates[index]= states.Eating;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ...
	}
	

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		philStates[piTID-1] = states.Thinking;
		//philStates.set(piTID-1, states.Thinking);
		
		
		notifyAll();
		
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk(final int piTID)
	{
		try {
			while(talking)
				wait();
			//philStates.set(piTID-1, states.Talking);
			philStates[piTID-1] = states.Talking;
				talking = true;
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk(final int piTID)
	{
		//philStates.set(piTID-1, states.Thinking);
		philStates[piTID-1]= states.Thinking;
		talking = false;
		notifyAll();
		// ...
	}
	public synchronized void requestShake(final int piTID) 
	{
		
			try {
				while(numbShake<1)
				wait();
				numbShake--;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}
	public synchronized void endShake(final int piTID) 
	{
			numbShake++;
			notifyAll();
		
	}
	public synchronized void requestSleep(final int piTID)
	{

		try {
			while(talking = true)
			wait();
			sleeping++;
			philStates[piTID -1] = states.Sleeping;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public synchronized void endSleep(final int piTID)
	{
		sleeping--;
		philStates[piTID -1] = states.Thinking;
		if(sleeping<0) {
			notifyAll();
		}
	}
	
	
}

// EOF
