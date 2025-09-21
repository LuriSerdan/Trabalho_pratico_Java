// Fig. 5.2: Producer.java
// Producer's run method controls a producer thread that
// stores values from 1 to 4 in Buffer sharedLocation.

public class Producer extends Thread 
{
   private Buffer sharedLocation; // reference to shared object

   // Producer constructor
   public Producer( Buffer shared )
   {
       super( "Producer" ); // create thread named "Producer" 
       sharedLocation = shared; // initialize sharedLocation 
   } // end Producer constructor

   // Producer method run stores values from 
   // 1 to 4 in Buffer sharedLocation
   public void run()
   {
      for ( int count = 1; count <= 4; count++ )   
      {
         // sleep 0 to 3 seconds, then place value in Buffer
         try 
         {
            Thread.sleep( ( int ) ( Math.random() * 3001 ) );
            sharedLocation.set( count ); // write to the buffer 
         } // end try

         // if sleeping thread interrupted, print stack trace
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // end catch

      } // end for

      System.err.println( getName() + " done producing." + 
         "\nTerminating " + getName() + "." );

   } // end method run

} // end class Producer

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/