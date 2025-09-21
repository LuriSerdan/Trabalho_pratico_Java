// Fig. 5.4: UnsynchronizedBuffer.java
// UnsynchronizedBuffer represents a single shared integer.

public class UnsynchronizedBuffer implements Buffer 
{
   private int buffer = -1; // shared by Producer and Consumer

   // place value into buffer
   public void set( int value )
   {
      System.err.println( Thread.currentThread().getName() +
         " writes " + value );

      buffer = value;
   } // end method set

   // return value from buffer
   public int get()
   {
      System.err.println( Thread.currentThread().getName() +
         " reads " + buffer );

      return buffer; 
   } // end method get

} // end class UnsynchronizedBuffer

/***************************************************
* Copyright 1992-2004 by Deitel & Associates, Inc. *
* and Pearson Education Inc. All Rights Reserved.  *
***************************************************/