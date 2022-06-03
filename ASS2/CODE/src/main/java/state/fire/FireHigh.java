package jwdavis.state.fire;

import jwdavis.state.State;

public class FireHigh extends Fire
{
    // public static final String EMERGENCY_TYPE = "high intensity fire";
    public FireHigh()
    {
        
    }

    @Override
    public String getType()
    {
        // return EMERGENCY_TYPE;
        return "fire high -- CHANGE ME";
        //TODO: REMOVE ME 
    }
}
