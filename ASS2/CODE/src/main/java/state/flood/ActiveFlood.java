package jwdavis.state.flood;

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

    @Override
    public void tick()
    {
        if((!getContext().isAttended()) && newCasualty())
        {
            numCasualties++;
            getContext().notifyObserver(String.format("flood casualty %d %s",
                                                       numCasualties ,
                                                       getContext().getLocation()));
        }
        if(newDamage())
        {
            numDamage++;
            getContext().notifyObserver(String.format("flood damage %d %s",
                                                       numDamage ,
                                                       getContext().getLocation()));
        }
    }
    
    public boolean newCasualty()
    {
        return Math.random() < FLOOD_CASUALTY_PROB;
    }

    public boolean newDamage()
    {
        return Math.random() < FLOOD_DAMAGE_PROB;
    }
    
}

