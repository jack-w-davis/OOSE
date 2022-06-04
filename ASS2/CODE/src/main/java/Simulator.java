package jwdavis;

import jwdavis.utils.*;
import jwdavis.*;
import jwdavis.parser.*;
import jwdavis.state.fire.*;
import jwdavis.state.*;
import jwdavis.observers.*;

import jwdavis.responders.*;

import java.lang.Thread;  
import java.util.stream.Collectors;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
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
        // fireTest();
        fireTestOld();
    }

    public static void fireTest()
    {
        Emergency e = new Emergency(new Fire(), 3, "Perth");

        e.setCurTime(3);
    }

    public static void fireTestOld()
    {
        // subscribers
        List<Observer> obs = new ArrayList<>();

        Emergency e = new Emergency(new Fire(), 3, "Perth");
        obs.add(e);

        int seconds = 0;
        int inc     = 1;
        
        while(seconds < 60)
        {
            try 
            {
                System.out.printf("%ds:\n",seconds);
                e.setCurTime(seconds);
                seconds += inc;
                Thread.sleep(1000 * inc);                
            } catch (InterruptedException except) {}
        }

    }


    // public static void notifyObservers(List<Observer> obs, int time)
    // {
    //     for(Observer o: obs)
    //     {
    //         o.setCurTime(time);
    //     }
    // }

    // public static void fileSim()
    // {
    //     ResponderComm res = new ResponderCommImpl();

    //     int seconds = 0;
    //     int inc     = 1;

    //     while(seconds < 60)
    //     {
    //         try 
    //         {
    //             System.out.printf("%ds:\n",seconds);
    //             for(String mess: res.poll())
    //             {
    //                 System.out.printf("      %s\n",mess);    
    //             }
    //             seconds += inc;
    //             Thread.sleep(1000 * inc);

    //         } 
    //         catch (InterruptedException e) 
    //         {
    //             //TODO: handle exception
    //         }
    //     }
    // }
}

