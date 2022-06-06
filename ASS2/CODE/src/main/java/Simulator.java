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

public class Simulator implements EmergencyObserver
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Simulator.class.getName());

    private List<ContextObserver> observers;
    private ResponderComm res;
    private boolean endRecieved;
    private long elapsedSecs;

    public Simulator(List<Emergency> emergencies, ResponderComm inRes)
    {
        //this is set to false by default because logically you can't have
        //something that has ended before it has already begone
        this.endRecieved = false;
        this.res = inRes;
        this.elapsedSecs = 0;
        setup(emergencies);

    }

    /**
     * this set ups the relationship between the simulator and the emergencies
     * 
     * For the:
     *  - Simulator: it subscribes the simulator to all the emergencies and
     *               recieves updates based off the status of those emergencies
     *               i.e. 'start', 'end', 'low', 'high'
     */
    private void setup(List<Emergency> emergencies)
    {
        observers = new ArrayList<>();
        for(Emergency e: emergencies)
        {
            observers.add(e);
            e.addObserver(this);
        }
    }


    /**
     * The following method is used to remove an observer from the list of
     * current observers. The reason it copies the list and then removes the 
     * observer from the new list is to avoid ConcurrentModificationException
     * as the observer itself decides that it needs to be removed.
     * 
     * E.G. fire start -> low -> high -> responders arrive -> low -> end
     * at end the observer removes itself
     */
    public void removeObserver(ContextObserver inObserver)
    {
        List<ContextObserver> list = new ArrayList<>(observers);
        list.remove(inObserver);
        observers = list;
    }

    public void start()
    {
        long startTime = System.currentTimeMillis();        

        while(endRecieved == false)
        {
            try 
            {
                poll();
                Thread.sleep(1000);                
                elapsedSecs = (System.currentTimeMillis() - startTime) / 1000L;
                notifyObservers(""+elapsedSecs);
            } 
            catch (InterruptedException e) 
            {     /*TODO: handle exception*/}
        }
    }

    public void poll()
    {
        for(String mess: res.poll())
        {
            logger.log(Level.INFO,String.format("POLL(%d) : %s",(int)elapsedSecs,mess));

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
        System.out.println(message);
        for(ContextObserver o: observers)
        {
            o.update(message);
        }
    }

    public void send()
    {

    }
    
    /**
     * When an Emergency updates it's status this recieves it and
     */
    public void update(String message)
    {
        res.send(message);
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
