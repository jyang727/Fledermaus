package com.juntest.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;

public class FutureTaskTest
{
	ExecutorService executor = Executors.newFixedThreadPool(1);

	SlowStringReverser reverser = new SlowStringReverser();

	void doReverse(final String target) throws InterruptedException
	{

	    FutureTask<String> future = new FutureTask<String>(
	         new Callable<String>()
	              {
	                    public String call()
	                    {
	                        return reverser.reverseString(target);
	                    }
	               });
	    executor.execute(future);

	    // try every 10 seconds
	    while (!future.isDone())
	    {
	        System.out.println("Task not yet completed.");
	        try
	        {
	            Thread.sleep(500);
	        } catch (InterruptedException ie)
	        {
	            System.out.println("Will check after 1/2 sec.");
	        }
	    }
	  
	    try
	    {
	        System.out.println("Here is the result..." + future.get());
	    } catch (ExecutionException ex)
	    {
        }
	    
	    executor.shutdown();
	    return;
	}

    public static void main(String args[])
	{
	  	FutureTaskTest msr = new FutureTaskTest();
	    try
	    {
	        msr.doReverse("foobar");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	private static class SlowStringReverser
	{
	    StringBuffer orgString;

	    StringBuffer reversedString;

	    SlowStringReverser(String orgStr)
	    {
	        orgString = new StringBuffer(orgStr);
	    }

	    SlowStringReverser()
	    {

	    }

	    public String reverseString(String str)
	    {
	        orgString = new StringBuffer(str);
	        reversedString = new StringBuffer();
	        for (int i = (orgString.length() - 1); i >= 0; i--)
	        {

	            reversedString.append(orgString.charAt(i));
	            System.out.println("Reversing one character per second."
	                    + reversedString);
	            try
	            {
	                Thread.sleep(1000);
	            } catch (InterruptedException ie)
	            {
	            }
	        }
	        return reversedString.toString();
	    }
	}
}
	
