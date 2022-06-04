package jwdavis.state;

import jwdavis.*;

public abstract class State
{
    private Emergency context;

    abstract public String getType(); 
    //Was just called notify, turns out Java Objects already have a method
    //called notify, therefore it's now called contextChange  
    abstract public void contextChange();

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