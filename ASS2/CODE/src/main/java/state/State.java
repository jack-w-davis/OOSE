package jwdavis.state;

import java.util.logging.Logger;

import jwdavis.*;

public abstract class State
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(State.class.getName());

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