package jwdavis.state;

import jwdavis.emergency.Emergency;

public abstract class State
{
    private Emergency<? extends State> context = null;
    
    abstract public String getType(); 

    public void setContext(Emergency<? extends State> inContext)
    {
        this.context = inContext;
    }

    
}