package edu.curtin.matheval;

public class MathEvalException extends Exception 
{
    public MathEvalException(String msg) 
    {
        super(msg);
    }

    public MathEvalException(String msg, Throwable cause)
    {
        super(msg,cause);
    }
}
