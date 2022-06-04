package jwdavis.state.chemical;

import jwdavis.Emergency;
import jwdavis.state.State;

public class Spill extends State
{
    public static final String EMERGENCY_TYPE = "spill";

    public Spill(){}

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
            ActiveSpill newState = new ActiveSpill();
            newState.setContext(getContext());
            getContext().setState(newState);
            System.out.println("REMOVE ME " + getContext().getType());
        }
    }
}

