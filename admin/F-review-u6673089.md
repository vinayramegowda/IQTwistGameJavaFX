Reviewer: Llewelyn Goodall (u6673089)
Component: <  private static boolean isAuthentic(String string){
                  boolean valid = true;
                  int bluePegCount=0, greenPegCount=0, yellowPegCount=0,redPegCount =0;
                  int length = string.length();
                  int currentCharacter = 0;
                  int nextCharacter = 1;
                  redPegCount = string.length() - string.replace("i", "").length();
                  bluePegCount = string.length() - string.replace("j", "").length();
                  greenPegCount = string.length() - string.replace("k", "").length();
                  yellowPegCount = string.length() - string.replace("l", "").length();
                  while (nextCharacter < length){
                    if (string.charAt(currentCharacter) > string.charAt(nextCharacter) || redPegCount > 1 || bluePegCount > 2 || greenPegCount > 2 || yellowPegCount > 2) {
                      // this  loops through every side-by-side pair of characters, moving up one each iteration
                      valid = false;
                    }
                    else if(string.charAt(currentCharacter)=='a'||string.charAt(currentCharacter)=='b'
                            ||string.charAt(currentCharacter)=='c'||string.charAt(currentCharacter)=='d'
                            ||string.charAt(currentCharacter)=='e'||string.charAt(currentCharacter)=='f'
                            ||string.charAt(currentCharacter)=='g'||string.charAt(currentCharacter)=='h')
                    {
                      if(string.charAt(currentCharacter)>=string.charAt(nextCharacter))
                      {
                        valid=false;
                      }
                    }
                    currentCharacter++;
                    nextCharacter++;
                  }
                  return valid;
                }   >
Author: Vinay Nagamangala Ramegowda (u6776177)

Review Comments:

1. Best features of the code is that it is quick and effective, there is no obviously wasted efficiency in executing the code. The use of if statements within else if statements
ensures that code is only processed if absolutely necessary. Furthermore, the code is also effective and robust. It does what it needs to do successfully in every scenario.
2. Java code conventions are followed throughout the coding. Clear and concise naming of all variables is present, and everything has proper capitalisation. Some slight inconsistencies
exist throughout, namely that when using operators there are sometimes spaces and sometimes none, but this is not a serious issue, but could be fixed.
3. No errors are apparent in the code, and no situations that could cause errors are realistically possible. It may be that some errors could be caused but only by abnormal entries
that could only be entered in a deliberate test. No changes are needed as this would slow down the code.
4. The program decomposition is appropriate as it does not use anything from other classes that would deem it appropriate to write the code in said class. Furthermore, the code is a
helper function for another method in its class (TwistGame), making it appropriate for isAuthentic to be where it is.
5. The code is adequately documented, as the only potentially confusing part of the code is commented. However, it would not be harmful for there to be another comment or two.