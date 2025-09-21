// Fig. 6.6: CircularBuffer.java
// CircularBuffer synchronizes access to an array of 
// shared buffers.

public class CircularBuffer implements Buffer
{
   // each array element is a buffer 
   private int buffers[] = { -1, -1, -1 };
    
   // occupiedBuffers maintains count of occupied buffers
   private int occupiedBuffers = 0;
    
   // variables that maintain read and write buffer locations
   private int readLocation = 0;
   private int writeLocation = 0;
   
   // place value into buffer
   public synchronized void set( int value )
   {
      // get name of thread that called this method
      String name = Thread.currentThread().getName();

      // while buffer full, place thread in waiting state
      while ( occupiedBuffers == buffers.length ) 
      {
         // output thread and buffer information, then wait
         try
         {
            System.err.println( "\nAll buffers full. " +
               name + " waits." ); 
            wait(); // wait until space is available
         } // end try

         // if waiting thread interrupted, print stack trace
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         } // end catch

      } // end while
        
      // place value in writeLocation of buffers
      buffers[ writeLocation ] = value;
        
      // output produced value
      System.err.println( "\n" + name + " writes " + 
         buffers[ writeLocation ] + " " );
        
      // indicate that one more buffer is occupied
      ++occupiedBuffers;  
        
      // update writeLocation for future write operation
      writeLocation = ( writeLocation + 1 ) % buffers.length;
        
      // display contents of shared buffers
      System.err.println( createStateOutput() );
        
      notify(); // return a waiting thread to ready state
   } // end method set
   
   // return value from buffer
   public synchronized int get()
   {  
      // get name of thread that called this method
      String name = Thread.currentThread().getName();

      // while buffer is empty, place thread in waiting state
      while ( occupiedBuffers == 0 )
      {
         // output thread and buffer information, then wait
         try
         {
            System.err.println( "\nAll buffers empty. " +
               name + " waits." );
            wait(); // wait until buffer contains new data
         } // end try
            
         // if waiting thread interrupted, print stack trace
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         } // end catch

      } // end while

      // obtain value at current readLocation
      int readValue = buffers[ readLocation ];
        
      // output consumed value
      System.err.println( "\n" + name + " reads " +
         readValue + " " );
        
      // decrement occupied buffers value
      --occupiedBuffers;
        
      // update readLocation for future read operation
      readLocation = ( readLocation + 1 ) % buffers.length;

      // display contents of shared buffers
      System.err.println( createStateOutput() );
             
      notify(); // return a waiting thread to ready state
        
      return readValue;
   } // end method get
    
   // create state output
   public String createStateOutput()
   {
      // first line of state information
      String output = "(buffers occupied: " + 
         occupiedBuffers + ")\nbuffers: ";

      for ( int i = 0; i < buffers.length; ++i )
      {
         output += " " + buffers[ i ] + "  ";
      } // end for

      // second line of state information
      output += "\n         ";

      for ( int i = 0; i < buffers.length; ++i )
      {
         output += "---- ";
      } // end for

      // third line of state information
      output += "\n         ";

      // append readLocation (R) and writeLocation (W)
      // indicators below appropriate buffer locations
      for ( int i = 0; i < buffers.length; ++i )
      {
         if ( i == writeLocation && 
              writeLocation == readLocation )
         {
            output += " WR  ";
         } // end if
         else if ( i == writeLocation )
         {
            output += " W   ";
         } // end if
         else if ( i == readLocation )
         {
            output += "  R  ";
         } // end if
         else 
         {
            output += "     ";
         } // end else

      } // end for

      output += "\n";

      return output;
   } // end method createStateOutput
    
} // end class CircularBuffer

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/