package jwdavis;

import jwdavis.state.State;


public class Emergency
{
    private State state;
    //TODO: Comment, these params don't change between an emergencies state 
    private String  location;
    private int     startTime; 
    
    public Emergency(State inState, int inStartTime, String inLocation)
    {
        this.state = inState;
        this.state.setContext(this);
        this.startTime = inStartTime;
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

    public String getType()
    {
        return state.getType();
    }

    public void currentTime(int curTime)
    {
        state.updateTime(curTime);
    }

    
}
