package jwdavis.state.fire;

import jwdavis.state.State;

public class FireLow extends Fire
{
    public static final String EMERGENCY_TYPE    = "fire low";
    public static final int    LOW_TO_HIGH_TIME  = 3;
    public static final int    LOW_CLEANUP_TIME  = 5;
    public static final double LOW_CASUALTY_PROB = 0.2;
    public static final double LOW_DAMAGE_PROB   = 0.2;

    public FireLow(){ }

    @Override
    public String getType() 
    { 
        return EMERGENCY_TYPE; 
    }

    @Override
    public void contextChange()
    {
        int timePassed = getContext().getTimePassed();
        
        if(getContext().isAttended())
        {            
            if(LOW_CLEANUP_TIME <= timePassed)
            {
                System.out.println("FIRE OUT: SEND MESSAGE");
            }
        }
        else
        {
            if(LOW_TO_HIGH_TIME <= timePassed)
            {
                FireHigh newState = new FireHigh();
                newState.setContext(getContext());
                getContext().setState(newState);
                System.out.println("REMOVE ME " + getContext().getType());
            }
        }
    }
}
