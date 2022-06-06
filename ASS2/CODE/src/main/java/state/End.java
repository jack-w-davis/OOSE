package jwdavis.state;

import jwdavis.*;

public class End extends State 
{
    public static final String EMERGENCY_TYPE = "end";

    public End()
    {
        getContext().getObserver().removeObserver(getContext());
    }

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void contextChange()
    {
        
    }
}