package jwdavis.parser;

import jwdavis.state.fire.Fire;

public class FireParser implements StateParser
{
    public static final String LABEL = "fire";

    public FireParser(){}

    @Override
    public String getLabel()
    {
        return LABEL;
    }

    @Override
    public Fire getState()
    {
        return new Fire();
    }

}
