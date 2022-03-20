// package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Jack Davis
 */
public class Entry 
{
    private Set<String> emails = new HashSet<>();

    private String name;

    public Entry(String inName)
    {
        setName(inName);
    }

    private void setName(String inName)
    {
        //Invalid name
        if((inName == null || inName.equals("") ))
        {
            System.out.println("Error: Name cannot be blank");
        }
        //Name is valid
        else
        {
            name = inName;
        }
    }

    public void addEmails(List<String> inEmails)
    {
        for(String emailToAdd: inEmails)
        {
            //if the email isn't present already
            if(! emails.add(emailToAdd) )
            {
                System.out.printf(
                    "%s: '%s' email already present, duplicate ignored\n",
                    name,emailToAdd);
            }
        }
    }

    public String getName()
    {
        return name;
    }
 
    public List<String> getEmails()
    {
        return new LinkedList<>(emails);
    }

    @Override
    public String toString()
    {
        String retval = getName() + " : ";

        for(String email: emails)
        {
            retval += (email + " ");
        }

        return retval;
    }
}
