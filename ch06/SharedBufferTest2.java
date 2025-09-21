// Fig. 6.5: SharedBufferTest2.java
// SharedBufferTest2creates producer and consumer threads.

public class SharedBufferTest2
{
   public static void main( String [] args )
   {
      // create shared object used by threads 
      SynchronizedBuffer sharedLocation = new SynchronizedBuffer();
        
      // Display column heads for output
      StringBuffer columnHeads = 
         new StringBuffer( "Operation" );
      columnHeads.setLength( 40 );
      columnHeads.append( "Buffer\t\tOccupied Count" );
      System.err.println( columnHeads );
      System.err.println();
      sharedLocation.displayState( "Initial State" );
        
      // create producer and consumer objects
      Producer producer = new Producer( sharedLocation );
      Consumer consumer = new Consumer( sharedLocation );
        
      producer.start(); // start producer thread
      consumer.start(); // start consumer thread
        
   } // end main
    
} // end class SharedBufferTest2

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/