package jwdavis.state.fire;

import jwdavis.state.State;

public class FireHigh extends Fire
{
    public static final int    HIGH_TO_LOW_TIME   = 5;
    public static final double HIGH_DAMAGE_PROB   = 0.6;
    public static final double HIGH_CASUALTY_PROB = 0.6;

    public FireHigh()
    {
        
    }

    @Override
    public void contextChange()
    {
        if(getContext().isAttended() && 
        (HIGH_TO_LOW_TIME <= getContext().getTimePassed()))
        {
            getContext().setState(new FireLow());
            getContext().notifyObserver("fire low "+ getContext().getLocation());
        }
    }
}
