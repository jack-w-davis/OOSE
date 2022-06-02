package jwdavis.emergency;

import jwdavis.state.State;

/**
 * Dependency Injection: 
 */

public class Emergency<S extends State>
{
    private S state;
    private String location;
    private int startTime;
    
    public Emergency(S inState)
    {
        this.state = inState;
        this.state.setContext(this);
    }
    
    public void setStartTime(int inStartTime)
    {
        this.startTime = inStartTime;
    }

    public void setState(S inState)
    {
        this.state = inState;
    }

    public String getType()
    {
        return state.getType();
    }
}
