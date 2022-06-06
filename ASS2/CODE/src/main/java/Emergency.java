package jwdavis;

import jwdavis.state.State;
import jwdavis.observers.Observer;
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
    private boolean attended = false;

    public Emergency(int inStartTime,State inState, String inLocation)
    {
        this.state = inState;
        this.state.setContext(this);

        this.startTime = inStartTime;
        this.curTime = inStartTime;
        this.stateChangeTime = inStartTime;
        this.location = inLocation;
    }

    public void setState(State inState)
    {
        this.state = inState;
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

    /**
     * This is used to recieve updates from the subject (the simulation),
     * as per Slide 11 of Lecture 4 "In the Observer Pattern... There’s no 
     * selection. Subjects call all registered observers". So cohering to
     * the Observer Pattern, all observers recieve the message, which is why
     * this method parses the message and uses the location/type to figure out
     * if it's the intended recipient.
     */
    public void update(String message)
    {
        Matcher matchTime = TIME_PATTERN.matcher(message);
        Matcher matchAttended = ATTENDED_PATTERN.matcher(message);
        if (matchTime.matches())
        {
            
        }
        else if(matchAttended.matches())
        {
            String messLoc = matchAttended.group("location");
            String messType = matchAttended.group("type");
            boolean messAtt = (matchAttended.group("attended").equals("\\+")? true: false);

            if(location.equals(messLoc) && state.getType().equals(messType))
            {
                updateAttended(messAtt);
            }
        }
        else
        {

        }
    }

    public void updateCurTime(int inCurTime)
    {
        this.curTime = inCurTime;
        state.contextChange();
    }
}