package jwdavis.state.fire;

public class FireHigh extends Fire
{
    public static final int    HIGH_TO_LOW_TIME   = 5;
    public static final double HIGH_DAMAGE_PROB   = 0.6;
    public static final double HIGH_CASUALTY_PROB = 0.6;

    public FireHigh(){}

    /**
     * refer to:
     * {@link jwdavis.state.State#contextChange() tick}
     * for comment
     */
    @Override
    public void contextChange()
    {
        //goes from 'fire high' -> 'fire low' if there are firefighters and 
        //HIGH_TO_LOW_TIME has passed
        if(getContext().isAttended() && 
        (HIGH_TO_LOW_TIME <= getContext().getTimePassed()))
        {
            getContext().setState(new FireLow());
            getContext().notifyObserver("fire low "+ getContext().getLocation());
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
        return Math.random() < HIGH_CASUALTY_PROB;
    }
    
    public boolean newDamage()
    {
        return Math.random() < HIGH_DAMAGE_PROB;
    }
}
