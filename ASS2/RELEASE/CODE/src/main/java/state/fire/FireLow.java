package jwdavis.state.fire;


public class FireLow extends Fire
{
    public static final int    LOW_TO_HIGH_TIME  = 3;
    public static final int    LOW_CLEANUP_TIME  = 5;
    public static final double LOW_CASUALTY_PROB = 0.2;
    public static final double LOW_DAMAGE_PROB   = 0.2;

    public FireLow(){ }

    /**
     * refer to:
     * {@link jwdavis.state.State#contextChange() tick}
     * for comment
     */
    @Override
    public void contextChange()
    {
        int timePassed = getContext().getTimePassed();
    
        if(getContext().isAttended())
        {   
            //goes from 'fire low' -> 'end' if there are fire fighters present
            //and the cleanup time has passed
            if(LOW_CLEANUP_TIME <= timePassed)
            {
                getContext().notifyObserver("fire end "+ getContext().getLocation());
                getContext().getObserver().removeObserver(getContext());
            }
        }
        else
        {
            //goes from 'fire low' -> 'fire high' if there are no firefighters 
            //and the 'LOW_TO_HIGH_TIME' has passed
            if(LOW_TO_HIGH_TIME <= timePassed)
            {
                getContext().notifyObserver("fire high "+ getContext().getLocation());
                getContext().setState(new FireHigh());
            }
        }
    }

    /**
     * refer to:
     * {@link jwdavis.state.State#tick() tick}
     * for comment
     */
    @Override
    public void tick()
    {
        if(newCasualty())
        {
            numCasualties++;
            getContext().notifyObserver(String.format("fire casualty %d %s",
                                                       numCasualties ,
                                                       getContext().getLocation()));
        }
        if(newDamage())
        {
            numDamage++;
            getContext().notifyObserver(String.format("fire damage %d %s",
                                                       numDamage ,
                                                       getContext().getLocation()));
        }
    }
    
    public boolean newCasualty()
    {
        return Math.random() < LOW_CASUALTY_PROB;
    }

    public boolean newDamage()
    {
        return Math.random() < LOW_DAMAGE_PROB;
    }
}
