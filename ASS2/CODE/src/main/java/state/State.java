package jwdavis.state;

import java.util.logging.Logger;

import jwdavis.*;

public abstract class State
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(State.class.getName());

    private Emergency context;

    abstract public String getType(); 
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