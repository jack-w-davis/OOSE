import java.util.*;

// import edu.curtin.addressbook.AddressBook;

/**
    @author Jack Davis

 */


public abstract class Option
{
    public static Scanner input = new Scanner(System.in);
    private static boolean requires_text;

    abstract public Entry doOption(AddressBook adBook, String query);
    abstract public String getOptionDesc();
}

class SearchByName extends Option
{
    @Override
    public Entry doOption(AddressBook adBook, String query)
    {
        System.out.print("Enter name: ");
        String name = null;
        Entry entry = null;
        
        try
        {
            name = input.nextLine();
            entry = adBook.getEntryFromName(name);
        }
        catch (NoSuchElementException e) 
        {
            System.out.println( "Entry for '" + name + "' not found!");
        }

        return entry;
    }

    @Override
    public String getOptionDesc()
    {
        return "Name";
    }
}

class SearchByEmail extends Option
{
    @Override
    public Entry doOption(AddressBook adBook, String query)
    {
        System.out.print("Enter email: ");
        String email = null;
        Entry entry = null;
        
        try
        {
             = input.nextLine();
            entry = adBook.getEntryFromName(email);
        }
        catch (NoSuchElementException e) 
        {
            System.out.println( "Entry for '" + name + "' not found!");
        }

        return entry;
    }

    @Override
    public String getOptionDesc()
    {
        return "Email";
    }
}