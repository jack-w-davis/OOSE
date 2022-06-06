package jwdavis.state.chemical;

import jwdavis.Emergency;
import jwdavis.state.End;
import jwdavis.state.State;

public class ActiveSpill extends Spill
{
    public static final String EMERGENCY_TYPE     = "chemical";
    public static final int    CHEM_CLEANUP_TIME  = 6;
    public static final double CHEM_CASUALTY_PROB = 0.3;

    public ActiveSpill(){}

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void contextChange()
    {
        if(getContext().isAttended() && CHEM_CLEANUP_TIME <= getContext().getTimePassed())
        {
            getContext().notifyObserver("chemical end "+ getContext().getLocation());
            getContext().getObserver().removeObserver(getContext());
        }
    }
}

