package jwdavis.state.fire;

import jwdavis.state.State;
import jwdavis.state.End;

import jwdavis.state.fire.FireHigh;

public class FireLow extends Fire
{
    public static final int    LOW_TO_HIGH_TIME  = 3;
    public static final int    LOW_CLEANUP_TIME  = 5;
    public static final double LOW_CASUALTY_PROB = 0.2;
    public static final double LOW_DAMAGE_PROB   = 0.2;

    public FireLow(){ }

    @Override
    public void contextChange()
    {
        int timePassed = getContext().getTimePassed();
        if(getContext().isAttended())
        {            
            if(LOW_CLEANUP_TIME <= timePassed)
            {
                getContext().notifyObserver("fire end "+ getContext().getLocation());
                getContext().getObserver().removeObserver(getContext());
            }
        }
        else
        {
            if(LOW_TO_HIGH_TIME <= timePassed)
            {
                getContext().notifyObserver("fire high "+ getContext().getLocation());
                getContext().setState(new FireHigh());
            }
        }
    }
}
