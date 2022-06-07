package jwdavis.state.flood;

import jwdavis.state.State;

public class Flood extends State
{
    public static final String EMERGENCY_TYPE = "flood";
    protected int numCasualties = 0;
    protected int numDamage = 0;

    public Flood(){}

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
            getContext().setState(new ActiveFlood());
            getContext().notifyObserver("flood start "+ getContext().getLocation());
        }
    }

    @Override
    public void tick()
    {
    }
}

