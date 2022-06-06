package jwdavis.observers;

//           Aka Subject
public interface Observable
{
    void addObserver(Observer... inObservers);
    void removeObserver(Observer inObserver);
    void notifyObservers(String message);    
}
