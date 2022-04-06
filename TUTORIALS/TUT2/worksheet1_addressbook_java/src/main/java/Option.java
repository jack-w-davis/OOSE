import java.util.*;

// import edu.curtin.addressbook.AddressBook;

/**
    @author Jack Davis

 */


public abstract class Option
{
    public static Scanner input = new Scanner(System.in);
    protected String query;

    abstract public Entry doOption(AddressBook adBook);

    protected String getOptionDesc()
    {
        return "Search by " + query;  
    }
    
    protected String getQuery()
    {
        System.out.printf("Please enter the %s to search\n",query);

        String query = input.nextLine();

        return query;
    }
}

class SearchByName extends Option
{
    public SearchByName()
    {
        query = "Name";
    }

    @Override
    public Entry doOption(AddressBook adBook) throws NoSuchElementException
    {
        try
        {
            Entry entry = null; 
            entry = adBook.getEntryFromKey(getQuery());
            return entry;
        }
        catch (NoSuchElementException e) 
        {
            throw new NoSuchElementException("Name not found!");
        }
    }
}

class SearchByEmail extends Option
{
    public SearchByEmail()
    {
        query = "Email";
    }

    @Override
    public Entry doOption(AddressBook adBook) throws NoSuchElementException
    {
        try
        {
            Entry entry = null; 
            entry = adBook.getEntryFromValue(getQuery());
            return entry;
        }
        catch (NoSuchElementException e) 
        {
            throw new NoSuchElementException("Email not found!");
        }
    }
}