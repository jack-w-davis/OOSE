// package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author ...
 */
public class Entry 
{
    private Set<String> emails = new HashSet<String>();

    private String name;

    public Entry(String inName)
    {
        setName(inName);
    }

    private void setName(String inName)
    {
        //Name is valid
        if(!(inName == null || inName.equals("") ))
        {
            name = inName;
        }
        else
        {
            //TODO: throw exception here, catch it in calling class???
        }
    }

    public void addEmails(String[] inEmails)
    {
        for(String emailToAdd: inEmails)
        {
            //if the email isn't present already
            if(! emails.add(emailToAdd) )
            {
                //TODO: Throw exception here
            }
        }
    }

    public String getName()
    {
        return name;
    }
 
    //TODO: Change me or justify me later
    public List<String> getEmails()
    {
        return new LinkedList<>(emails);
    }

    public String toString()
    {
        String retval = null;

        retval =  getName() + " : ";

        for(String email: emails)
        {
            retval += (email + " ");
        }

        return retval;
    }
}
