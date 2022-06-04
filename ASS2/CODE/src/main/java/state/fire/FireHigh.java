package jwdavis.state.fire;

import jwdavis.state.State;

public class FireHigh extends Fire
{
    public static final String EMERGENCY_TYPE = "fire high";
    public static final int    HIGH_TO_LOW_TIME   = 5;
    public static final double HIGH_DAMAGE_PROB   = 0.6;
    public static final double HIGH_CASUALTY_PROB = 0.6;

    public FireHigh(){}

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void contextChange()
    {
        if(getContext().isAttended() && 
        (HIGH_TO_LOW_TIME < getContext().getTimePassed()))
        {
            FireLow fl = new FireLow();
            fl.setContext(getContext());
            getContext().setState(fl);
        }
    }
}
