// This class represents a die game
public class Die extends Object{
// A field to represent the current vlaue
  private int currentValue = 1;
//  A method to access the current value
  public int getValue(){
    return currentValue;
  }
//  A method to change the current value with input
  public void setValue(int value){
  currentValue = value;
  }
}