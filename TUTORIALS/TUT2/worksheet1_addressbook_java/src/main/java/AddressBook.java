// package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author ...
 */
public class AddressBook
{
    private Map<String, Entry> addresses;

    public AddressBook()
    {
        addresses = new HashMap<>();
    }

    public void addNewEntry(String inName, List<String> inEmails)
    {
        //If entry already exists
        if(addresses.containsKey(inName))
        {
            System.out.println(inName + " already present!");
        }
        //if name isn't present
        else
        {
            Entry e = new Entry(inName);
            e.addEmails(inEmails);
            addresses.put(inName,e);
        }
    }

    public Entry getEntryFromKey(String inKey)
    {
        if(addresses.containsKey(inKey))
        {
            return addresses.get(inKey);
        }
        
        throw new NoSuchElementException("");
    }

    public Entry getEntryFromValue(String inValue)
    {
        for(Entry e: addresses.values())
        {
            if(e.getEmails().contains(inValue))
            {
                return e;
            }   
        }

        throw new NoSuchElementException("");
    }

    public Entry getEntryFromName(String inName) throws NoSuchElementException
    {
        if(addresses.containsKey(inName))
        {
            return addresses.get(inName);
        }
        
        throw new NoSuchElementException("");
    }

    public Entry getEntryFromEmail(String email) throws NoSuchElementException
    {
        for(Entry e: addresses.values())
        {
            if(e.getEmails().contains(email))
            {
                return e;
            }   
        }

        throw new NoSuchElementException("");
    }
}


