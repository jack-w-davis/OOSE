package jwdavis;

import jwdavis.utils.IO;
import jwdavis.utils.Map2D;
import jwdavis.*;
import jwdavis.parser.*;

import jwdavis.state.*;
import jwdavis.state.fire.*;
import jwdavis.state.flood.*;
import jwdavis.state.chemical.*;

import jwdavis.observers.*;

import jwdavis.responders.*;

import java.io.*;
import java.lang.Thread;  
import java.util.stream.Collectors;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.logging.*;

public class Simulator
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Simulator.class.getName());

    private Map2D<String,String,Emergency> observers;
    private ResponderComm res;
    private boolean endRecieved;

    public Simulator(Map2D<String,String,Emergency> inObservers, ResponderComm inRes)
    {
        this.observers = inObservers;
        this.res = inRes;
        //this is set to false by default because logically you can't have
        //something that has ended before it has already begone
        this.endRecieved = false;
    }

    public void start()
    {
        long startTime = System.currentTimeMillis();        
        long elapsedSecs = 0;

        while(endRecieved == false)
        {
            System.out.printf("%ds:\n",elapsedSecs);
            try 
            {
                poll();
                Thread.sleep(1000);                
                elapsedSecs = (System.currentTimeMillis() - startTime) / 1000L;
                // notifyObservers((int) elapsedSecs);
            } 
            catch (InterruptedException e) 
            {     /*TODO: handle exception*/}

        }
    }

    private void poll()
    {
        for(String mess: res.poll())
        {
            logger.log(Level.INFO,"POLL: " + mess);
            if(mess.matches("end"))
            {
                //TODO: end stuff here
            }
            else
            {
                
                notifyObservers(mess);
            }
        }
    }
    
    public void notifyObservers(String message)
    {
        for(ContextObserver o: observers.values())
        {
            o.update(message);
        }
    }

    public void removeObserver(ContextObserver inObserver)
    {

    }

}


    // public static void main(String[] args)
    // {
    //     //If the program doesn't have any args specified it can't run
    //     if(0 < args.length)
    //     {
    //         List<String> fileContent = IO.readFileToList(args[0]);
            
    //         FileParser fp = new FileParser();

    //         fp.addStateParser(new FireParser(),
    //                           new FloodParser(),
    //                           new ChemParser());

    //         Map2D<String,String,Emergency> em = fp.parseFile(fileContent);

    //         long startTime = System.currentTimeMillis();        
    //         long elapsedSecs = 0;

    //         ResponderComm res = new ResponderCommImpl();
    //         while(elapsedSecs < 60)
    //         {
    //             try 
    //             {
    //                 System.out.printf("%ds:\n",elapsedSecs);
    //                 for(String mess: res.poll())
    //                 {
    //                     // System.out.printf("      %s\n",mess);    
    //                 }
    //                 elapsedSecs = (System.currentTimeMillis() - startTime) / 1000L;
    //                 Thread.sleep(1000);

    //             } 
    //             catch (InterruptedException e) 
    //             {
    //                 //TODO: handle exception
    //             }
    //         }
    //     }
    // }

    // public static void notifyObservers(List<Emergency> emergencies, int time)
    // {
    //     for(Emergency em: emergencies)
    //     {
    //         em.setCurTime(time);
    //     }
    // }
// }
