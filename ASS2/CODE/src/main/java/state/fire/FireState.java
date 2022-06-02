package jwdavis.state.fire;

import jwdavis.state.State;

public class FireState extends State
{
    // abstract public <S extends FireState> void setState(S State);
    @Override
    public String getType()
    {
        return "FIRE";
    }
}

