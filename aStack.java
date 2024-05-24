import java.util.ArrayList;

public class aStack<T> {
  ArrayList<T> myStack = new ArrayList<T>();
  T tempReturn;
  
  public T peek(){
    return myStack.get(myStack.size()-1);
  }

  public T pop(){
    if(myStack.size() == 0) return null;
    tempReturn = myStack.get(myStack.size()-1);
    myStack.remove(myStack.size()-1);

    return tempReturn;
  
  }

  public void push(T s){
    myStack.add(s);
  }

  public boolean isEmpty() {
    if (myStack.size() == 0)
      return true;
    else
      return false;
  }
  
}
