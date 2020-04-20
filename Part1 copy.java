import java.util.Random;
import java.util.Scanner;
public class Part1 {

  public static void main(String[] args){
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    int a = rand.nextInt(8999) + 1000 ; // make sure it is four digits
    int b = prompt(sc);
    System.out.println("answer = " + a);
    while(!guess(a,b)){
        b = prompt(sc);
    }


  }

  /*
  @param Scanner
  @return a number entered by the user
  this program prompt user for a guess
  rejects any invalid guesses that are more than or less than four digits
  */
  public static int prompt(Scanner sc){
    System.out.print("Please enter your guess (a four-digit number): ");
    int a = sc.nextInt();
    while( a < 1000 || a > 9999 ){
      System.out.println("Invalid input!");
      System.out.print("Please enter your guess: ");
      a = sc.nextInt();
    }
    return a;
  }

/*
@param1 four digits number
@param2 user's guess
@return boolean if a == b
this program converts both integers into arrays (for comparision convience)
It iterate array aS.
at each position of array aS, it first compares the value of that position with the value in array bS at same position; (looks for correct guess at a correct postion)
  if the values at the same position do not equal each other, then it will compares with values of all positions in bS.  Break the loop once found (looks for correct guess at a wrong position)
*/
  public static boolean guess(int a, int b){
    if ( a != b ) {
        int[] aS = convert(a);
        int[] bS = convert(b);
        int i = 0;
        int count1 = 0;
        int count2 = 0;
        for(int n : aS){
            if(n == bS[i++]) {
              count1++;
            }else{
              for (int m : bS){
                if ( n == m ) {
                  count2++;
                  break;
                }
              }
            }
        }
        System.out.printf("%d guesses are correct and in the correct place%n" , count1 );

        System.out.printf("%d guesses are correct but in the wrong place%n" , count2);
        return false;
    }else {
        System.out.printf("right%n");
        return true;
    }
  }

/*
@param a four digit number
@return a array of length four
this program converts a number into an array of same length
*/
  public static int[] convert(int a){
    int[] b = new int[4];
    int d = 1000;
    int i = 0;
    while( d > 0 ){
      b[i++] = a/d;
      a = a%d;
      d = d/10;
    }
    return b;
  }


}
