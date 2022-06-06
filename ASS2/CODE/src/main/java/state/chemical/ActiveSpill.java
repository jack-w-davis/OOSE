package jwdavis.state.chemical;

import jwdavis.Emergency;
import jwdavis.state.State;

public class ActiveSpill extends Spill
{
    public static final String EMERGENCY_TYPE     = "chemical";
    public static final int    CHEM_CLEANUP_TIME  = 6;
    public static final double CHEM_CASUALTY_PROB = 0.3;
    public static final double CHEM_CONTAM_PROB   = 0.7;
    protected int numCasualties = 0;
    protected int numContam = 0;

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

    @Override
    public void tick()
    {
        if(newCasualty())
        {
            numCasualties++;
            getContext().notifyObserver(String.format("chemical contam %d %s",
                                                       numCasualties ,
                                                       getContext().getLocation()));
        }

        if(newContam())
        {
            numContam++;
            getContext().notifyObserver(String.format("chemical contam %d %s",
                                                       numContam ,
                                                       getContext().getLocation()));
        }
    }
    
    public boolean newCasualty()
    {
        return Math.random() < CHEM_CASUALTY_PROB;
    }
    
    public boolean newContam()
    {
        return Math.random() < CHEM_CONTAM_PROB;
    }

}

