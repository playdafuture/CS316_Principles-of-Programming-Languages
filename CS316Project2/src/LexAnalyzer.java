/**
 
This class is a lexical analyzer for the 23 token categories <var> through <period> defined by:

<lowercase letter> --> a | b | ... | z 
<uppercase letter> --> A | B | ... | Z 
<letter> --> <lowercase letter> | <uppercase letter> 
<digit> --> 0 | 1 | ... | 9 
<var> --> <uppercase letter> {<letter> | <digit>} 
<functor> --> <lowercase letter> {<letter> | <digit>} 
<int> --> [+|-] {<digit>}+ 
<float> --> [+|-] ( {<digit>}+ "." {<digit>}  |  "." {<digit>}+  ) 
<floatE> --> <float> (e|E) [+|-] {<digit>}+ 
<if> --> ":-" 
<add> --> + 
<sub> --> - 
<mul> --> * 
<div> --> / 
<lt> --> "<" 
<le> --> "=<" 
<gt> --> ">" 
<ge> --> ">=" 
<eq> --> "=" 
<neq> --> "\=" 
<LParen> --> "(" 
<RParen> --> ")" 
<LBracket> --> "[" 
<RBracket> --> "]" 
<bar> --> "|" 
<comma> --> "," 
<period> --> "." 

This class implements a DFA that will accept the above tokens.
The DFA states are represented by the Enum type "State".
The DFA has 23 final and 6 non-final states represented by enum-type literals.

There is also 1 special state for the keyword "is".
This keyword is initially extracted as <functor>.
The keywordCheck() function checks if the extracted token is "is",
and if so, moves the DFA to the special state.

The function "driver" operates the DFA. 
The array "nextState" returns the next state given the current state and the input character.

To recognize a different token set, modify the following:

  enum type "State" and function "isFinal"
  size of array "nextState"
  functions "setNextState", "keywordCheck"

The functions "driver", "getToken", "setLex" remain the same.

**/


public abstract class LexAnalyzer extends IO
{
	public enum State 
       	{ 
	  // final states     ordinal number  token accepted 

		Add,             // 0          +
		Sub,             // 1          -
		Mul,             // 2          *
		Div,             // 3          /
		Lt,              // 4          <
		Le,              // 5          =<
		Gt,              // 6          >
		Ge,              // 7          >=
		Eq,              // 8          =
		Neq,             // 9          \=
		If,              // 10         :-
		Var,             // 11         variables
		Functor,         // 12         functors
		Int,             // 13         integers
		Float,           // 14         floats without exponentiation part
		FloatE,          // 15         floats with exponentiation part
		LParen,          // 16         (
		RParen,          // 17         )
		LBracket,        // 18         {
		RBracket,        // 19         }
		Period,          // 20         .
		Bar,             // 21         |
		Comma,           // 22         ,

	  // non-final states                 string recognized    

		Start,           // 23         the empty string
		AddSubPeriod,    // 24         "+.", "-."
		Backslash,       // 25         \      
		Colon,           // 26         :
		E,               // 27         float parts ending with E or e
		EPlusMinus,      // 28         float parts ending with + or - in exponentiation part

	  // keyword state for "is"

		Keyword_is,

		UNDEF;

		private boolean isFinal()
		{
			return ( this.compareTo(State.Comma) <= 0 );  
		}	
	}

	// By enumerating the final states first and then the non-final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is less than or equal to that of Comma.

	// The following variables are inherited from "IO" class:

	//   static int a; the current input character
	//   static char c; used to convert the variable "a" to the char type whenever necessary

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA

	static State nextState[][] = new State[29][128];
 
          // This array implements the state transition function State x (ASCII char set) --> State.
          // The state argument is converted to its ordinal number used as
          // the first array index from 0 through 28.

	static final String keyword_is = "is";

	private static int driver()

	// This is the driver of the FA. 
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character, returns -1.

	{
		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState[state.ordinal()][a];
			if ( nextSt == State.UNDEF ) // The FA will halt.
			{
				if ( state.isFinal() )
					return 1; // valid token extracted
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else // The FA will go on.
			{
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
	} // end driver

	private static void setNextState()
	{
		for (int s = 0; s <= 28; s++ )
			for (int c = 0; c <= 127; c++ )
				nextState[s][c] = State.UNDEF;

		for (char c = 'A'; c <= 'Z'; c++)
		{
			nextState[State.Start  .ordinal()][c] = State.Var;
			nextState[State.Var    .ordinal()][c] = State.Var;
			nextState[State.Functor.ordinal()][c] = State.Functor;
		}

		for (char c = 'a'; c <= 'z'; c++)
		{
			nextState[State.Start  .ordinal()][c] = State.Functor;
			nextState[State.Var    .ordinal()][c] = State.Var;
			nextState[State.Functor.ordinal()][c] = State.Functor;
		}

		for (char d = '0'; d <= '9'; d++)
		{
			nextState[State.Var         .ordinal()][d] = State.Var;
			nextState[State.Functor     .ordinal()][d] = State.Functor;
			nextState[State.Start       .ordinal()][d] = State.Int;
			nextState[State.Int         .ordinal()][d] = State.Int;
			nextState[State.Add         .ordinal()][d] = State.Int;
			nextState[State.Sub         .ordinal()][d] = State.Int;
			nextState[State.AddSubPeriod.ordinal()][d] = State.Float;
			nextState[State.Period      .ordinal()][d] = State.Float;
			nextState[State.Float       .ordinal()][d] = State.Float;
			nextState[State.E           .ordinal()][d] = State.FloatE;
			nextState[State.EPlusMinus  .ordinal()][d] = State.FloatE;
			nextState[State.FloatE      .ordinal()][d] = State.FloatE;
		}

		nextState[State.Start.ordinal()]['+'] = State.Add;
		nextState[State.Start.ordinal()]['-'] = State.Sub;
		nextState[State.Start.ordinal()]['*'] = State.Mul;
		nextState[State.Start.ordinal()]['/'] = State.Div;
		nextState[State.Start.ordinal()]['<'] = State.Lt;
		nextState[State.Start.ordinal()]['>'] = State.Gt;
		nextState[State.Start.ordinal()]['='] = State.Eq;
		nextState[State.Start.ordinal()]['\\'] = State.Backslash;
		nextState[State.Start.ordinal()]['|'] = State.Bar;
		nextState[State.Start.ordinal()][','] = State.Comma;
		nextState[State.Start.ordinal()][':'] = State.Colon;
		nextState[State.Start.ordinal()]['.'] = State.Period;
		nextState[State.Start.ordinal()]['('] = State.LParen;
		nextState[State.Start.ordinal()][')'] = State.RParen;
		nextState[State.Start.ordinal()]['['] = State.LBracket;
		nextState[State.Start.ordinal()][']'] = State.RBracket;
		
		nextState[State.Eq       .ordinal()]['<'] = State.Le;
		nextState[State.Gt       .ordinal()]['='] = State.Ge;
		nextState[State.Backslash.ordinal()]['='] = State.Neq;

		nextState[State.Colon.ordinal()]['-'] = State.If;
		
		nextState[State.Add.ordinal()]['.'] = State.AddSubPeriod;
		nextState[State.Sub.ordinal()]['.'] = State.AddSubPeriod;
		nextState[State.Int.ordinal()]['.'] = State.Float;
			
		nextState[State.Float.ordinal()]['e'] = state.E;
		nextState[State.Float.ordinal()]['E'] = state.E;
		
		nextState[State.E.ordinal()]['+'] = State.EPlusMinus;
		nextState[State.E.ordinal()]['-'] = State.EPlusMinus;

	} // end setNextState

	private static void keywordCheck()
	{
		if ( t.equals(keyword_is) )
			state = State.Keyword_is;
	}

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if ( state == State.Functor )
			keywordCheck();
		else if ( i == 0 )
			displayln(t + " : Lexical Error, invalid token");
	}

	public static void setLex()

	// Sets the nextState array and keywordMap.

	{
		setNextState();
	}

	public static void main(String argv[])

	{
		// argv[0]: input file containing source code using tokens defined above
		// argv[1]: output file displaying a list of the tokens 

		setIO( argv[0], argv[1] );
		setLex();

		int i;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
			{
				if ( state == State.Functor )
					keywordCheck();
				displayln( t+"   : "+state.toString() );
			}
			else if ( i == 0 )
				displayln( t+" : Lexical Error, invalid token");
		} 

		closeIO();
	}
} 
