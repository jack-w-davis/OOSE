package jwdavis.state.fire;

import jwdavis.Emergency;
import jwdavis.state.State;
import jwdavis.state.fire.FireLow;

public class Fire extends State
{
    public static final String EMERGENCY_TYPE = "fire";

    public Fire()
    {}

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
            FireLow fl = new FireLow();
            fl.setContext(getContext());
            getContext().setState(fl);
            System.out.println("REMOVE ME " + getContext().getType());
        }
        
    }
}

