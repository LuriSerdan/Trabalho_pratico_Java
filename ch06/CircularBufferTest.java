// Fig. 6.10: CircularBufferTest.java
// CircularBufferTest shows two threads manipulating a 
// circular buffer.

// set up the producer and consumer threads and start them
public class CircularBufferTest
{
   public static void main ( String args[] )
   {
      // create shared object for threads; use a reference
      // to a CircularBuffer rather than a Buffer reference
      // to invoke CircularBuffer method createStateOutput
      CircularBuffer sharedLocation = new CircularBuffer();

      // display initial state of buffers in CircularBuffer
      System.err.println( sharedLocation.createStateOutput() );

      // set up threads
      Producer producer = new Producer( sharedLocation );
      Consumer consumer = new Consumer( sharedLocation );
        
      producer.start(); // start producer thread
      consumer.start(); // start consumer thread
   } // end main

} // end class CircularBufferTest

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/