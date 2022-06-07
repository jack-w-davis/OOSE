package jwdavis.state;

import jwdavis.*;

public abstract class State
{
    private Emergency context;

    abstract public String getType(); 
    abstract public void contextChange();
    abstract public void tick();
    public State(){}

    public void setContext(Emergency inContext)
    {
        this.context = inContext;
    }

    public Emergency getContext()
    {
        return context;
    }
}