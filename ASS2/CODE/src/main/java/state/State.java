package jwdavis.state;

import jwdavis.*;

public abstract class State
{
    public Emergency context;

    public State()
    {
        this.context = null;
    }

    public void updateTime(int curTime)
    {
        if(curTime == context.getStartTime()){
            emergencyBegin();
        }
    }

    public void setContext(Emergency inContext)
    {
        this.context = inContext;
    }

    public Emergency getContext()
    {
        return context;
    }
    
    /**
     * This method is used to actually 'start' an emergency.
     */
    
    abstract protected void emergencyBegin();
    abstract public String getType();    
}