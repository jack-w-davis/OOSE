class Message extends GameObj
{
    private String text;
    
    public Message()
    {
    }

    public void setText(String inText)
    {
        text = inText;
    }

    public String getText()
    {
        return text;
    }
}