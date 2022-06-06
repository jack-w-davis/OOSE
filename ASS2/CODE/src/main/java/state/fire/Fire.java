package jwdavis.state.fire;

import jwdavis.Emergency;
import jwdavis.state.State;
import jwdavis.state.fire.FireLow;

public class Fire extends State
{
    public static final String EMERGENCY_TYPE = "fire";
    protected int numCasualties = 0;
    protected int numDamage = 0;

    public Fire()
    {}

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
            getContext().setState(new FireLow());
            getContext().notifyObserver("fire low "+ getContext().getLocation());
        }
    }

    @Override
    public void tick(){}

}

