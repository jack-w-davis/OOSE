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
        addresses = new HashMap<String,Entry>();
    }

    public void addNewEntry(String inName, String... inEmails)
    {
        //TODO: Add exceptions here
        //if name isn't present
        if(! addresses.containsKey(inName))
        {
            Entry e = new Entry(inName);

            e.addEmails(inEmails);

            addresses.put(inName,e);
        }
        else
        {
            //TODO: throw exception here
        }
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

        throw new NoSuchElementException();
    }
}


