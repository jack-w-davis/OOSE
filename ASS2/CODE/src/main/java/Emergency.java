package jwdavis;

import jwdavis.state.State;
import jwdavis.observers.*;

public class Emergency implements Observer, Observable
{
    private State   state;
    private String  location;
    private int     startTime; //TODO: Maybe refactor and remove me
    private int     curTime;
    private int     stateChangeTime;
    private boolean attended;

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
    
    public void setCurTime(int inCurTime)
    {
        this.curTime = inCurTime;
        state.contextChange();
    }

    public void setAttended(boolean inAttend)
    {
        this.attended = inAttend;
        state.contextChange();
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
}