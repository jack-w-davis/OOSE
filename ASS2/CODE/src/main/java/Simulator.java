package jwdavis;

import jwdavis.utils.*;
import jwdavis.*;
import jwdavis.parser.*;
import jwdavis.state.fire.*;
import jwdavis.state.*;

import jwdavis.responders.*;

import java.lang.Thread;  
import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Simulator
{
    public static void main(String[] args)
    {
        // List<String> list = IO.readFile(args[0]);

        // FileParser fp = new FileParser();

        // fp.addEmergencyParser(new FireParser(),
        //                       new FloodParser());

        // fp.parseCollection(list);


        // Emergency<Fire> e = new Emergency<>(new Fire(), 5, "Perth");
        // System.out.println(e.getType());
        
        // e.setState(new FireLow());
        // System.out.println(e.getType());


        // responderShennanigans();
        fireTest();

    }

    public static void fireTest()
    {

        Emergency e = new Emergency(new Fire(), 3, "Perth");


        int seconds = 0;
        int inc     = 1;
        
        while(seconds < 60)
        {
            try 
            {
                System.out.printf("%ds:\n",seconds);
                e.currentTime(seconds);
                seconds += inc;
                Thread.sleep(1000 * inc);

                
            } catch (InterruptedException except) {}
        }

    }

    public static void fileSim()
    {
        ResponderComm res = new ResponderCommImpl();

        int seconds = 0;
        int inc     = 1;

        while(seconds < 60)
        {
            try 
            {
                System.out.printf("%ds:\n",seconds);
                for(String mess: res.poll())
                {
                    System.out.printf("      %s\n",mess);    
                }
                seconds += inc;
                Thread.sleep(1000 * inc);

            } 
            catch (InterruptedException e) 
            {
                //TODO: handle exception
            }
        }
    }
}

