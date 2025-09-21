// Fig. 5.3: Consumer.java
// Consumer's run method controls a thread that loops four
// times and reads a value from sharedLocation each time.

public class Consumer extends Thread 
{ 
   private Buffer sharedLocation; // reference to shared object

   // Consumer constructor
   public Consumer( Buffer shared )
   {
      super( "Consumer" ); // create thread named "Consumer"
      sharedLocation = shared; // initialize sharedLocation 
   } // end Consumer constructor

   // read sharedLocation's value four times and sum the values
   public void run()
   {
      int sum = 0;

      // alternate between sleeping and getting Buffer value
      for ( int count = 1; count <= 4; ++count ) 
      {         
         // sleep 0-3 seconds, read Buffer value and add to sum
         try 
         {
            Thread.sleep( ( int ) ( Math.random() * 3001 ) );    
            sum += sharedLocation.get();
         }

         // if sleeping thread interrupted, print stack trace
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         }
      } // end for

      System.err.println( getName() + " read values totaling: " 
         + sum + ".\nTerminating " + getName() + "." );

   } // end method run

} // end class Consumer

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/