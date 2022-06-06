package jwdavis.state.flood;

import jwdavis.state.End;
import jwdavis.Emergency;
import jwdavis.state.State;

public class ActiveFlood extends Flood
{
    public static final String EMERGENCY_TYPE      = "flood";
    public static final int    FLOOD_END_TIME      = 7;
    public static final double FLOOD_DAMAGE_PROB   = 0.7;
    public static final double FLOOD_CASUALTY_PROB = 0.35;

    public ActiveFlood(){}

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void contextChange()
    {
        if(FLOOD_END_TIME <= getContext().getTimePassed())
        {
            getContext().notifyObserver("flood end "+ getContext().getLocation());
            getContext().getObserver().removeObserver(getContext());
        }
    }
}

