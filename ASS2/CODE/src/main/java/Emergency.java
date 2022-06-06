package jwdavis;

import jwdavis.state.State;
import jwdavis.observers.Observer;
import jwdavis.Simulator;
import jwdavis.observers.ContextObserver;
import jwdavis.observers.Observable;
import java.util.logging.*;
import java.util.regex.Matcher;



public class Emergency implements ContextObserver
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Emergency.class.getName());

    private State   state;
    private String  location;
    private int     startTime; //TODO: Maybe refactor and remove me
    private int     curTime;
    private int     stateChangeTime;
    private boolean attended;
    private Simulator observer;

    public Emergency(int inStartTime,State inState, String inLocation)
    {
        this.state = inState;
        this.state.setContext(this);

        this.startTime = inStartTime;
        this.curTime = inStartTime;
        this.stateChangeTime = inStartTime;
        this.location = inLocation;
        this.attended = false;
    }

    public void addObserver(Simulator inSimulator)
    {
        observer = inSimulator;
    }
    
    /**
     * This is used to recieve updates from the subject 
     * {@link jwdavis.Simulator#Simulator() Simulator},as per Slide 11 of Lecture 4 
     * "In the Observer Pattern... There’s no selection. Subjects call all 
     * registered observers". So cohering tothe Observer Pattern, all observers 
     * recieve the message, which is whythis method parses the message and uses 
     * the location/type to figure outif it's the intended recipient.
     * 
     * Since we don't have to use threads this makes it somewhat messy as really
     * these Emergencies could just have their own thread and keep track of the
     * current time and whatever else.
     */
    public void update(String message)
    {
        Matcher matchTime     = TIME_PATTERN.matcher(message);
        Matcher matchAttended = ATTENDED_PATTERN.matcher(message);

        if (matchTime.matches())
        {
            updateCurTime(Integer.parseInt(message));
        }
        else if(matchAttended.matches())
        {
            String messLoc = matchAttended.group("location");
            String messType = matchAttended.group("type");
            boolean messAtt = matchAttended.group("attended").equals("+");

            if(location.equals(messLoc) && state.getType().equals(messType))
            {
                updateAttended(messAtt);
            }
        }
        else
        {
            //TODO: throw exception
        }
    }

    public void notifyObserver(String message)
    {
        observer.update(message);
    }

    public void updateCurTime(int inCurTime)
    {
        this.curTime = inCurTime;
        state.contextChange();
    }

    public void updateAttended(boolean inAttend)
    {
        this.attended = inAttend;
        state.contextChange();
        logger.info(String.format("%s at %s %s attended",
                                   getType(),
                                   location,
                                   attended?"is":"is not"
                                   ));
    }


//---------------------standard SETTERS/GETTERS---------------------------------
    public void setState(State inState)
    {
        this.state = inState;
        this.state.setContext(this);
        this.stateChangeTime = this.curTime;
    }

    public String getLocation()
    {
        return location;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public int getCurTime()
    {
        return curTime;
    }

    public int getStateChangeTime()
    {
        return stateChangeTime;
    }

    public String getType()
    {
        return state.getType();
    }

    public boolean isAttended()
    {
        return attended;
    }

    public int getTimePassed()
    {
        return curTime - stateChangeTime;
    }

    public Simulator getObserver()
    {
        return observer;
    }
}