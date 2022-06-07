package jwdavis.state;

import jwdavis.*;

public abstract class State
{
    private Emergency context;

    /**
     * returns a string, representing the type of emergency. E.G. 'fire', 'flood'
     * or 'chemical'
     */
    abstract public String getType(); 

    /**
     * allows the stat to know something has changed within it's context
     */
    abstract public void contextChange();
    /**
     * this is called every second. It's called tick because like a server for
     * a game has a 'tickrate' that updates every tick, this runs every second.
     */
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