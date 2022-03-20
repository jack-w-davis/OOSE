import java.util.*;

import edu.curtin.addressbook.AddressBook;

/**
    @author Jack Davis

 */


public class Option
{
    public static 

    public String doOption(AddressBook addBook, String query);

    public String getOptionDesc();
}

class SearchByName implements IOption
{
    @Override
    public String doOption(AddressBook addBook, String query)
    {
        
    }

    @Override
    public String getOptionDesc()
    {
        return "Name";
    }
}

class SearchByEmail implements IOption
{
    @Override
    public String doOption(AddressBook addBook, String query)
    {
        
    }

    @Override
    public String getOptionDesc()
    {
        return "Email";
    }
}