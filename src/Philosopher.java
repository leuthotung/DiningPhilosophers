import common.BaseThread;
import java.util.*;
/**
 * Class Philosopher.
 * Outlines main subrutines of our virtual philosopher.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Philosopher extends BaseThread
{
	/**
	 * Max time an action can take (in milliseconds)
	 */
	public static final long TIME_TO_WASTE = 1000;

	/**
	 * The act of eating.
	 * - Print the fact that a given phil (their TID) has STARTED eating.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are DONE eating.
	 */
	public void eat()
	{
		try
		{
			System.out.println("Philosopher "+ this.iTID+" has STARTED eating");
			DiningPhilosophers.soMonitor.requestShake(getTID());
			shake();
			DiningPhilosophers.soMonitor.endShake(getTID());
			yield();
			//PhilSleep();
			sleep((long)(Math.random() * TIME_TO_WASTE));
			yield();
			System.out.println("Philosopher "+ this.iTID+" is DONE eating");
			// ...
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of thinking.
	 * - Print the fact that a given phil (their TID) has STARTED thinking.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are DONE thinking.
	 */
	public void think()
	{
		try
		{
			System.out.println("Philosopher "+ this.iTID+" has STARTED thinking");
			yield();
			
			//PhilSleep();
			
			sleep((long)(Math.random() * TIME_TO_WASTE));
			yield();
			System.out.println("Philosopher "+ this.iTID+" is DONE thinking");
			// ...
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.think():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of talking.
	 * - Print the fact that a given phil (their TID) has STARTED talking.
	 * - yield
	 * - Say something brilliant at random
	 * - yield
	 * - The print that they are DONE talking.
	 */
	public void talk()
	{
		System.out.println("Philosopher "+ this.iTID+" has STARTED talking");
		yield();

		saySomething();
		yield();
		System.out.println("Philosopher "+ this.iTID+" is DONE talking");

		// ...
	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run()
	{
		for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
		{
			DiningPhilosophers.soMonitor.pickUp(getTID());

			eat();
			

			DiningPhilosophers.soMonitor.putDown(getTID());

			think();
			//DiningPhilosophers.soMonitor.requestSleep(getTID());
			//PhilSleep();
			//DiningPhilosophers.soMonitor.endSleep(getTID());
				
			
			/*
			 * TODO:
			 * A decision is made at random whether this particular
			 * philosopher is about to say something terribly useful.
			 */
	
				// Some monitor ops down here...
			Random rand = new Random();
			int talkchance = rand.nextInt(2);
			if(talkchance ==1 ) {
				DiningPhilosophers.soMonitor.requestTalk(getTID());
				talk();
				DiningPhilosophers.soMonitor.endTalk(getTID());
				// ...
			}

			yield();
		}
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 * Feel free to add your own phrases.
	 */
	public void saySomething()
	{
		String[] astrPhrases =
		{
			"Eh, it's not easy to be a philosopher: eat, think, talk, eat...",
			"You know, true is false and false is true if you think of it",
			"2 + 2 = 5 for extremely large values of 2...",
			"If thee cannot speak, thee must be silent",
			"My number is " + getTID() + ""
		};

		System.out.println
		(
			"Philosopher " + getTID() + " says: " +
			astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
	public void PhilSleep()
    {
		try
		{	
			//DiningPhilosophers.soMonitor.requestSleep(getTID());
			System.out.println("Philosopher "+ this.iTID+" has STARTED sleeping");
			yield();
		
			sleep((long)(Math.random() * TIME_TO_WASTE));
			yield();
			System.out.println("Philosopher "+ this.iTID+" is DONE sleeping");
			//DiningPhilosophers.soMonitor.endShake(getTID());
			// ...
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.sleep():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
    }
	public void shake() 
	{
		try
		{
			System.out.println("Philosopher "+ this.iTID+" has STARTED shaking");
			yield();
		
			sleep((long)(Math.random() * TIME_TO_WASTE));
			yield();
			System.out.println("Philosopher "+ this.iTID+" is DONE shaking");
			// ...
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.sleep():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
		
	}
}

// EOF
