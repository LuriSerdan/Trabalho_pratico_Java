// Fig. 5.5: SharedBufferTest.java
// SharedBufferTest creates producer and consumer threads.

public class SharedBufferTest 
{
    public static void main( String [] args )
    {
        // create shared object used by threads
        Buffer sharedLocation = new UnsynchronizedBuffer();

        // create producer and consumer objects
        Producer producer = new Producer( sharedLocation );
        Consumer consumer = new Consumer( sharedLocation );

        producer.start();  // start producer thread
        consumer.start();  // start consumer thread

    } // end main

} // end class SharedCell

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/