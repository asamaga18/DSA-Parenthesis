import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Stack;

class Main {

  public static void main(String[] args) {
    try {
      File cases = new File("test_cases.txt"); // getting lines from text file
      Scanner myReader = new Scanner(cases);
      while (myReader.hasNextLine()) { 
        String line = myReader.nextLine(); // line is the variable for the text in the line
        System.out.println(line);
        int error = checker(line);
        char j = '~'; // placeholder character that cannot be a result of the function

        if (error == -1) { // all clear case
          System.out.println("No errors detected. \n");
          error = 0;
        } 
          else{ // checking for errors and specific cases
            j = line.charAt(error); //sets j to character
            if ((j == 40) || (j == 91) || (j == 123)) { //left over open
              System.out.println("Error at character #" + (error+1) + ". '" + j + "' is not closed. \n");
        }   else if ((j == 41) || (j == 93) || (j == 125)) { //left over closed
              System.out.println("Error at character #" + (error+1) + ". '" + j + "' is not opened. \n");
          }
        }
      }

      myReader.close();
    } catch (FileNotFoundException e) { //error with file
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static int checker(String line) { //method allows for ending search when error is found
    Stack<Character> brackStack = new Stack<Character>();
    Stack<Integer> LocStack = new Stack<Integer>();

    for (int i = 0; i < line.length(); i++) {
      char bracket = line.charAt(i); // runs character by character
      if ((bracket == 40) || (bracket == 91) || (bracket == 123)) { //puts open bracket and index in stack
        brackStack.push(bracket);
        LocStack.push(i);
      }

      else if ((bracket == 41) || (bracket == 93) || (bracket == 125)) { //when closed...
        if(!brackStack.isEmpty())
        {  
          if (((bracket == 41) && (brackStack.peek() == 40)) || ((bracket == 93) && (brackStack.peek() == 91))
            || ((bracket == 125) && (brackStack.peek() == 123))) { //if the closed matches with the open
          brackStack.pop();
          LocStack.pop();//removes top value from open and index stack
          } 
          else
           return i; //returns index of close error if no match
        }
        else
          return i; //returns if no values in open stack
      }
    }
    if (!brackStack.isEmpty()) { //returns index of open error
      return LocStack.pop();
    } else
      return -1; //no error
  }
}
