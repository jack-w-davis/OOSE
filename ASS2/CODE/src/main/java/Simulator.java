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

    /**
     *  starts the simulation. The simulation proceeds until the message 'end'
     * is recieved from the responders.
     */
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
                notifyTime((int) elapsedSecs);
            } 
            catch (InterruptedException e) 
            {     /*TODO: handle exception*/}
        }
    }

    /**
     * A wrapper for the {@link jwdavis.responders.Responder#poll()} method.
     * this polls any messages the responders have sent and then deals with it
     * appropiately.
     */
    public void poll()
    {
        for(String mess: res.poll())
        {
            if(mess.matches("end"))
            {
                endRecieved = true;
            }
            else
            {
                notifyMessage(mess);
            }

            logger.info(String.format("POLL(%d) : %s",(int)elapsedSecs,mess));
        }
    }

    /**
     * Notifies observers (emergencies) of:
     *  - Responders arriving.
     *  - The time updating (each second).
     */
    public void notifyMessage(String message)
    {
        for(ContextObserver o: observers)
        {
            o.update(message);
        }
    }

    public void notifyTime(int time)
    {
        for(ContextObserver o: observers)
        {
            o.updateCurTime(time);
        }
    }

    /**
     * When an Emergency status changes it notifies the simulator (an observer)
     * and sends then this calls send to send to the responders. The emergencies
     * report their status to the simulator and not the responders themselves
     * so that if need be, in the future this can be updated to change how/what
     * message is sent. I.E. what if the responders change halfway throughout
     * the simulation.
     */
    public void update(String message)
    {
        res.send(message);
    }
}
