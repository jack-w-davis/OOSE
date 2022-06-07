package jwdavis.parser;

import jwdavis.state.flood.Flood;

public class FloodParser implements StateParser
{
    public static final String LABEL = "flood";

    public FloodParser(){}

    @Override
    public String getLabel()
    {
        return LABEL;
    }

    @Override
    public Flood getState()
    {
        return new Flood();
    }
    
}
