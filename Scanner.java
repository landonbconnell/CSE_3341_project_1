import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

class Scanner {
    
    BufferedReader in;
    Core token;

    static Pattern alphaNumericPattern;
    static final int MAX_CONST = 999983;

    // Initialize the scanner
    Scanner(String filename) {
        // Initializes the input reader
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + filename + "\" not found.");
        }

        // Initializes the alpha-numeric pattern
        alphaNumericPattern = Pattern.compile("^[a-zA-Z0-9]+$");
    }

    // Advance to the next token
    public void nextToken() {
        try {
            String word = "";
            char currentChar = (char) in.read();

            // Advances char pointer until whitespace is fully consumed
            while (currentChar == ' ') {
                currentChar = (char) in.read();
            }

            // Builds the word string until whitespace is detected
            while (currentChar != ' ') {
                word += currentChar;
                currentChar = (char) in.read();
            }

            if (isAlphaNumeric(word)) {
                switch (word) {
                    case "procedure":
                        token = Core.PROCEDURE;
                        break;
                    case "begin":
                        token = Core.BEGIN;
                        break;
                    case "is":
                        token = Core.IS;
                        break;
                    case "end":
                        token = Core.END;
                        break;
                    case "if":
                        token = Core.IF;
                        break;
                    case "else":
                        token = Core.ELSE;
                        break;
                    case "in":
                        token = Core.IN;
                        break;
                    case "integer":
                        token = Core.INTEGER;
                        break;
                    case "return":
                        token = Core.RETURN;
                        break;
                    case "do":
                        token = Core.DO;
                        break;
                    case "new":
                        token = Core.NEW;
                        break;
                    case "not":
                        token = Core.NOT;
                        break;
                    case "and":
                        token = Core.AND;
                        break;
                    case "or":
                        token = Core.OR;
                        break;
                    case "out":
                        token = Core.OUT;
                        break;
                    case "object":
                        token = Core.OBJECT;
                        break;
                    case "then":
                        token = Core.THEN;
                        break;
                    case "while":
                        token = Core.WHILE;
                        break;
                    default:
                        token = Core.ID;
                }
            } else if (canParseAsInt(word)) {
                if (Integer.parseInt(word) <= MAX_CONST) {
                    token = Core.CONST;
                } else {
                    // TODO - descriptive error message goes here.
                }
                
            }
            
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
        
    }

    // Return the current token
    public Core currentToken() {
        return token;
    }

	// Return the identifier string
    public String getId() {

    }

	// Return the constant value
    public int getConst() {

    }

    private static boolean isAlphaNumeric(String s) {
        return alphaNumericPattern.matcher(s).matches();
    }

    private static boolean canParseAsInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
