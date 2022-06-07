package jwdavis.state.chemical;

import jwdavis.state.State;

public class Spill extends State
{
    public static final String EMERGENCY_TYPE = "chemical";

    public Spill(){}

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void contextChange()
    {
        if(getContext().getCurTime() == getContext().getStartTime())
        {
            getContext().setState(new ActiveSpill());
            getContext().notifyObserver("chemical start "+ getContext().getLocation());
        }
    }

    @Override
    public void tick()
    {
    }
}

