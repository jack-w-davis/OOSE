package jwdavis.state.chemical;

import jwdavis.Emergency;
import jwdavis.state.End;
import jwdavis.state.State;

public class ActiveSpill extends Spill
{
    public static final String EMERGENCY_TYPE     = "spill start";
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
            State newState = new End();
            newState.setContext(getContext());
            getContext().setState(newState);
            
            System.out.println("SPILL DONE");
        }
    }
}

