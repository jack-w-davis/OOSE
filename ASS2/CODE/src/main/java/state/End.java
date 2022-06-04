package jwdavis.state;

import jwdavis.*;

public class End extends State 
{
    public static final String EMERGENCY_TYPE = "spill";

    public End(){}

    @Override
    public String getType()
    {
        return EMERGENCY_TYPE;
    }

    @Override
    public void contextChange()
    {
        System.out.println("poop i'm end");
        //Does nothing for now
    }


}