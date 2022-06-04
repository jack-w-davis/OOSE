package jwdavis.state.flood;

import jwdavis.Emergency;
import jwdavis.state.State;

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
            ActiveFlood newState = new ActiveFlood();
            newState.setContext(getContext());
            getContext().setState(newState);
            System.out.println("REMOVE ME " + getContext().getType());
        }
    }
}

