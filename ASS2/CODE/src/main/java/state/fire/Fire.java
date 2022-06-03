package jwdavis.state.fire;

import jwdavis.Emergency;
import jwdavis.state.State;
import jwdavis.state.fire.FireLow;

public class Fire extends State
{
    //---------------CONSTANTS------------------------- 
    public static final String EMERGENCY_TYPE = "fire";
    public static final int    FIRE_LOW_CLEANP_TIME    = 5;
    public static final int    FIRE_LOW_TO_HIGH_TIME   = 15;
    public static final int    FIRE_HIGH_TO_LOW_TIME   = 10;
    public static final double FIRE_LOW_CASUALTY_PROB  = 0.2;
    public static final double FIRE_HIGH_CASUALTY_PROB = 0.6;
    public static final double FIRE_LOW_DAMAGE_PROB    = 0.2;
    public static final double FIRE_HIGH_DAMAGE_PROB   = 0.6;

    public Fire(){}

    public Fire(Fire oldState)
    {
        
    }

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void emergencyBegin()
    {
        FireLow fl = new FireLow();
        fl.setContext(getContext());
        getContext().setState(fl);
    }
}

