package jwdavis.state.flood;

import jwdavis.Emergency;
import jwdavis.state.State;
import jwdavis.state.flood.ActiveFlood;

public class Flood extends State
{
    public static final String EMERGENCY_TYPE = "flood";

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
}

