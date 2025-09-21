// Fig. 6.4: SynchronizedBuffer.java
// SynchronizedBuffer synchronizes access to a shared integer.

public class SynchronizedBuffer implements Buffer
{
   private int buffer = -1; // shared by producer and consumer
   private int occupiedBuffers = 0; // counts occupied buffers
   
   // place value into buffer
   public synchronized void set( int value )
   {
      // for display, get name of thread that called this method
      String name = Thread.currentThread().getName();

      // while no empty buffers, place thread in waiting state
      while ( occupiedBuffers == 1 )
      {
         // output thread and buffer information, then wait
         try
         {
            System.err.println( name + " tries to write." );
            displayState( "Buffer full. " + name + " waits." );
            wait(); // wait until buffer is empty
         } // end try

         // if waiting thread interrupted, print stack trace
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         } // end catch

      } // end while
        
      buffer = value; // set new buffer value
        
      // indicate producer cannot store another value
      // until consumer retrieves current buffer value
      ++occupiedBuffers;
        
      displayState( name + " writes " + buffer );
      
      notify(); // tell waiting thread to enter ready state
   } // end method set; releases lock on SynchronizedBuffer 
    
   // return value from buffer
   public synchronized int get()
   {
      // for display, get name of thread that called this method
      String name = Thread.currentThread().getName();

      // while no data to read, place thread in waiting state
      while ( occupiedBuffers == 0 )
      {
         // output thread and buffer information, then wait
         try
         {
            System.err.println( name + " tries to read." );
            displayState( "Buffer empty. " + name + " waits." );
            wait();// wait until buffer contains new values
         } // end try

         // if waiting thread interrupted, print stack trace
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         } // end catch

      } // end while

      // indicate that producer can store another value 
      // because consumer just retrieved buffer value
      --occupiedBuffers;

      displayState( name + " reads " + buffer );
      
      notify(); // tell waiting thread to become ready

      return buffer;
   } // end method get; releases lock on SynchronizedBuffer 
    
   // display current operation and buffer state
   public void displayState( String operation )
   {
      StringBuffer outputLine = new StringBuffer( operation );
      outputLine.setLength( 40 );
      outputLine.append( buffer + "\t\t" + occupiedBuffers );
      System.err.println( outputLine );
      System.err.println();
   } // end method displayState
    
} // end class SynchronizedBuffer

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/