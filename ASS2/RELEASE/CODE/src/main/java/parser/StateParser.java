package jwdavis.parser;

import jwdavis.state.State;

public interface StateParser
{
    String getLabel();
    State getState();
}
