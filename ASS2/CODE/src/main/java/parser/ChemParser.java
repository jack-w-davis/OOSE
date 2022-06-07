package jwdavis.parser;

import jwdavis.state.chemical.Spill;

public class ChemParser implements StateParser
{
    public static final String LABEL = "chemical";

    public ChemParser(){}

    @Override
    public String getLabel()
    {
        return LABEL;
    }

    @Override
    public Spill getState()
    {
        return new Spill();
    }

}
