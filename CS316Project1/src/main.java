/**
 *
 * @author Jinqiu Liu
 */
public abstract class main extends IO {
    public enum State { 
        //non-final states      ordinal number
        Start,                  // 0
        Backslash,              // 1
        AddSubPeriod,           // 2
        Colon,                  // 3
        E,                      // 4
        EPlusMinus,             // 5

        //final states
        Mul,                    // 6
        Div,                    // 7
        LParen,                 // 8
        RParen,                 // 9
        LBracket,               // 10
        RBracket,               // 11
        Lt,                     // 12
        Gt,                     // 13
        Ge,                     // 14
        Eq,                     // 15
        Le,                     // 16
        Neq,                    // 17
        Bar,                    // 18
        Var,                    // 19
        Functor,                // 20
        Int,                    // 21
        Add,                    // 22
        Sub,                    // 23
        Period,                 // 24
        Float,                  // 25
        FloatE,                 // 26
        If,                     // 27
        Comma,                  // 28
        Keyword_is,             // 29
        UNDEF;

        private boolean isFinal() {
            return (this.compareTo(State.Mul) >= 0 );  
        }
    }
    
    public static String t; // holds an extracted token
    public static State state; // the current state of the FA
    
    /**
     * Driver function for the DFA.
     * @return  1 if a valid token is found;
     *          0 if an invalid token is found;
     *         -1 if the end-of-stream token is found.
     */
    private static int driver() {
        State nextSt; // the next state of the FA

        t = "";
        state = State.Start;

        if ( Character.isWhitespace((char) a) )
            a = getChar(); // get the next non-whitespace character
        if ( a == -1 ) // end-of-stream is reached
            return -1;

        while ( a != -1 ) { // do the body if "a" is not end-of-stream
            c = (char) a;
            nextSt = nextState( state, c );
            if ( nextSt == State.UNDEF ) { // The FA will halt.
                if ( state.isFinal() ) {
                    if (state == State.Functor && t.equals("is")) {
                        //Already accepted in the functor state 
                        //AND the keyword is "is"
                        state = State.Keyword_is;
                    }
                    return 1; // valid token extracted
                } else { // "c" is an unexpected character
                    t = t+c;
                    a = getNextChar();
                    return 0; // invalid token found
                }
            } else { // The FA will go on.
                state = nextSt;
                t = t+c;
                a = getNextChar();
            }
        }
        // end-of-stream is reached while a token is being extracted

        if ( state.isFinal() )
            return 1; // valid token extracted
        else
            return 0; // invalid token found
    } // end of driver
    
    /**
     * Follows the DFA's rules and determine the next state 
     * based on current state and character taken in.
     * @param s Current state
     * @param c Character taken in
     * @return next state, UNDEF if it's undefined.
     */
    private static State nextState(State s, char c) {	
        switch (state) {
            case Start:
                if (c == '*')
                    return State.Mul;
                else if (c == '/')
                    return State.Div;
                else if (c == '(')
                    return State.LParen;
                else if ( c == ')' )
                    return State.RParen;
                else if ( c == '[' )
                    return State.LBracket;
                else if ( c == ']' )
                    return State.RBracket;
                else if ( c == '<' )
                    return State.Lt;
                else if ( c == '>' )
                    return State.Gt;
                else if ( c == '=' )
                    return State.Eq;
                else if ( c == '\\' )
                    return State.Backslash;
                else if ( c == '|' )
                    return State.Bar;
                else if ( c >= 'A' && c <= 'Z' ) //Uppercase Letter
                    return State.Var;
                else if ( c >= 'a' && c <= 'z' ) //Lowercase Letter
                    return State.Functor;
                else if ( Character.isDigit(c) ) //Digit
                    return State.Int;
                else if ( c == '+' )
                    return State.Add;
                else if ( c == '-' )
                    return State.Sub;
                else if ( c == '.' )
                    return State.Period;
                else if ( c == ':' )
                    return State.Colon;
                else if ( c == ',' )
                    return State.Comma;
                else
                    return State.UNDEF;
            case Gt:
                if ( c == '=' )
                    return State.Ge;
                else
                    return State.UNDEF;
            case Eq:
                if ( c == '<' )
                    return State.Le;
                else
                    return State.UNDEF;
            case Backslash:
                if ( c == '=' )
                    return State.Neq;
                else
                    return State.UNDEF;
            case Var:
                if ( Character.isLetterOrDigit(c) )
                    return State.Var;
                else
                    return State.UNDEF;
            case Functor:
                if ( Character.isLetterOrDigit(c) )
                    return State.Functor;
                else
                    return State.UNDEF;
            case Int:
                if ( Character.isDigit(c) )
                    return State.Int;
                else if ( c == '.' )
                    return State.Float;
                else
                    return State.UNDEF;
            case Add:
                if ( Character.isDigit(c) )
                    return State.Int;
                else if ( c == '.' )
                    return State.AddSubPeriod;
                else
                    return State.UNDEF;
            case Sub:
                if ( Character.isDigit(c) )
                    return State.Int;
                else if ( c == '.' )
                    return State.AddSubPeriod;
                else
                    return State.UNDEF;
            case AddSubPeriod:
                if ( Character.isDigit(c) )
                    return State.Float;
                else
                    return State.UNDEF;
            case Period:
                if ( Character.isDigit(c) )
                    return State.Float;
                else
                    return State.UNDEF;
            case Colon:
                if ( c == '-' )
                    return State.If;
                else
                    return State.UNDEF;
            case Float:
                if ( Character.isDigit(c) )
                    return State.Float;
                else if ( c == 'e' || c == 'E' )
                    return State.E;
                else
                    return State.UNDEF;
            case E:
                if ( Character.isDigit(c) )
                    return State.FloatE;
                else if ( c == '+' || c == '-' )
                    return State.EPlusMinus;
                else
                    return State.UNDEF;
            case FloatE:
                if ( Character.isDigit(c) )
                    return State.FloatE;
                else
                    return State.UNDEF;
            case EPlusMinus:
                if ( Character.isDigit(c) )
                    return State.FloatE;
                else
                    return State.UNDEF;
            default:
                return State.UNDEF;
        }
    } // end of nextState
    
    public static void main(String[] args) {
        setIO( args[0], args[1] );		
        int i;
        while ( a != -1 ) { // while "a" is not end-of-stream
            i = driver(); // extract the next token
            if ( i == 1 )
                displayln( t+"   : "+state.toString() );
            else if ( i == 0 )
                displayln( t+" : Lexical Error, invalid token");
        } 
        closeIO();
    } //end of main
}
