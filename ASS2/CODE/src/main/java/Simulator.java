package jwdavis;

import jwdavis.utils.*;
import jwdavis.emergency.Emergency;
import jwdavis.parser.*;
import jwdavis.state.fire.*;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Simulator
{
    public static void main(String[] args)
    {
        // List<String> list = IO.readFile(args[0]);

        // FileParser fp = new FileParser();

        // fp.addEmergencyParser(new FireParser(),
        //                       new FloodParser());

        // fp.parseCollection(list);


        //Creates an emergency
        Emergency<FireState> e = new Emergency<>(new FireState());

        System.out.println(e.getType());
        

    }
}
