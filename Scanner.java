import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Scanner {
    
    BufferedReader in;
    char currentChar;
    String word;
    Core token;

    static final int MAX_CONST = 999983;

    // Initialize the scanner
    Scanner(String filename) {
        // Initializes the input reader
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + filename + "\" not found.");
        }
    }

    // Advance to the next token
    public void nextToken() {
        word = "";
        getNextChar();

        // Advances char pointer until whitespace is fully consumed
        while (currentChar == ' ')
            getNextChar();

        // Builds the word string until whitespace is detected
        while (currentChar != ' ') {
            word += currentChar;
            getNextChar();
        }

        boolean isAlphaNumeric = word.matches("^[a-zA-Z0-9]+$");
        boolean isConstant = word.matches("^-?\\d+$");
        
        //  
        if (isAlphaNumeric) {
            setKeywordOrIdentifier();

        // 
        } else if (isConstant) {
            int value = Integer.parseInt(word);
            if (value >= 0 && value <= MAX_CONST) {
                token = Core.CONST;
            } else {
                System.out.println("Constant falls outside of range 0 - " + MAX_CONST + " (inclusive).");
            }
        } else if (word.length() == 1) {
            switch (word) {
                case "+":
                    token = Core.ADD;
                    break;
                case "-":
                    token = Core.SUBTRACT;
                    break;
                case "*":
                    token = Core.MULTIPLY;
                    break;
                case "/":
                    token = Core.DIVIDE;
                    break;
                case "=":
                    token = Core.ASSIGN;
                    break;
                case "<":
                    token = Core.LESS;
                    break;
                case ":":
                    token = Core.COLON;
                    break;
                case ";":
                    token = Core.SEMICOLON;
                    break;
                case ".":
                    token = Core.PERIOD;
                    break;
                case ",":
                    token = Core.COMMA;
                    break;
                case "(":
                    token = Core.LPAREN;
                    break;
                case ")":
                    token = Core.RPAREN;
                    break;
                case "[":
                    token = Core.LBRACE;
                    break;
                case "]":
                    token = Core.RBRACE;
                    break;
                default:
                    System.out.println("Invalid token '" + word + "' detected.");
                    break;
            }
        }
            
        
        
    }

    // Return the current token
    public Core currentToken() {
        return token;
    }

	// Return the identifier string
    public String getId() {
        return word;
    }

	// Return the constant value
    public int getConst() {
        return Integer.parseInt(word);
    }

    private void setKeywordOrIdentifier() {
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
    }

    private void getNextChar() {
        try {
            currentChar = (char) in.read();
        } catch(IOException e) {
            System.out.println("Error reading from file.");
        }
    }
}
