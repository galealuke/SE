
public interface Subject
{

 public void setState(Position state);


 public void attach(Observer observer);

 public void notifyAllObservers(Position pos);
}
