public abstract class Parser extends LexAnalyzer {
    static boolean errorFound = false;
		
    public static Clauses clauses() {
        //<clauses> → <clause> | <clause> <clauses>
        Clause cl = clause();
        if (state == State.Functor) {
            return new Clauses(cl, clauses());
        } else {
            return new Clauses(cl);
        }
    }
    
    public static Clause clause() {
        //<clause> → <head> | <head> [":−" <atomic formulas>] "." 
        Head head = head();
        if (state == State.If) {            
            getToken();
            AtomicFormulas atomicFormulas = atomicFormulas();
            if (state != State.Period) {
                errorMsg(".");
            }
            getToken();
            return new Clause(head, atomicFormulas);
        }
        if (state != State.Period) {
            errorMsg(".");
        }
        getToken();
        return new Clause(head);
    }
    
    public static Head head() {
        //<head> → <functor> [ "(" <terms> ")" ]
        Functor f = functor();
        if (state == State.LParen) {
            getToken();
            Terms tm = terms();
            if (state != State.RParen) {
                errorMsg(")");
            }
            getToken();
            return new Head(f, tm);
        } else {
            return new Head(f);
        }
    }
    
    public static Terms terms() {
        //<terms> → <term> | <term> "," <terms> 
        Term tm = term();
        if (state == State.Comma) {
            getToken();
            return new Terms(tm, terms());
        } else {
            return new Terms(tm);
        }
    }
    
    public static Term term() {
        //<term> → <var> | <number> | <FunctorTerms> | <list term> 
        if (state == State.Var) {
            Var v = new Var(t);
            getToken();
            return new Term(v);
        } else if (state == State.Int || state == State.Float || state == State.FloatE) {
            //<number> → <int> | <float> | <floatE>
            Number n = new Number(state.toString(), t);
            getToken();
            return new Term(n);
        } else if (state == State.Functor) {
            FunctorTerms ft = functorTerms();
            return new Term(ft);
        } else if (state == State.LBracket) {
            List l = list();            
            return new Term(l);
        } else {
            errorMsg("<var>, <number>, <functor> or <list term>");            
        }      
        return null;
    }
    
    public static List list() {
        //<list> → 
        //"[" [ <terms> ] "]"                   list of terms
        //"[" <term> "|" ( <var> | <list> ) "]"    list with "|"
        getToken();
        if (state == State.RBracket) {
            //Empty Brackets, empty terms
            List l = new List(null);
            getToken();
            return l; //List that contains empty terms
        }
        Terms ts = terms();
        if (state == State.RBracket) {
            List l = new List(ts);
            getToken();
            return l; //List that contains non-empty terms
        } else if (state == State.Bar) {
            Term tm = ts.t;
            getToken();
            if (state == State.Var) {
                Var v = new Var(t);
                getToken();
                if (state != State.RBracket) {
                    errorMsg("]");
                }
                getToken();
                return new List(tm, v);
            } else {
                //another list
                List l = list();
                getToken();
                return new List(tm, l);
            }
        } else {
            errorMsg("<List>");
        }
        return null;
    }
    
    public static AtomicFormulas atomicFormulas() {
        //<atomic formulas> → <atomic formula> | <atomic formula> "," <atomic formulas> 
        AtomicFormula af = atomicFormula();
        if (state == State.Comma) {
            getToken();
            return new AtomicFormulas(af, atomicFormulas());
        } else {
            return new AtomicFormulas(af);
        }        
    }
    
    public static AtomicFormula atomicFormula() {
        //<atomic formula> → <functor> [ "(" <terms> ")" ] | <built-in relation> 
        if (state == State.Functor) {
            FunctorTerms ft = functorTerms();
            if (state == State.Eq || state == State.Neq) {
                //tricky, this is actually a built-in relation
                String s = t;
                getToken();
                Term tm = term();
                BuiltInRelation bir = new BuiltInRelation(ft, s, tm);
                return new AtomicFormula(bir);
            }
            return new AtomicFormula(ft);
        } else {
            BuiltInRelation bir = builtInRelation();
            return new AtomicFormula(bir);
        }
    }
    
    public static FunctorTerms functorTerms() {
        //<functor> [ "(" <terms> ")" ]
        Functor f = functor();
        if (state == State.LParen) {
            getToken();
            Terms tm = terms();
            if (state != State.RParen) {
                errorMsg(")");
            }
            getToken();
            return new FunctorTerms(f, tm);
        }
        return new FunctorTerms(f);
    }
    
    public static BuiltInRelation builtInRelation() {
        if (state == State.Var) {
            //<built-in relation> → 
            //<var> "is" <arith exp> | 
            //<var> <comp op 1> <var> |
            //<var> <comp op 1> <number> | 
            //<var> <comp op 2> <term>           
            Var v1 = new Var(t);
            getToken();
            if (state == State.Keyword_is) {
                //arithExp
                getToken();
                ArithExp ae = arithExp();
                return new BuiltInRelation(v1, "is", ae);
            } else if (state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge) {
                //comp op 1, var/number is next
                String s = t;
                getToken();
                if (state == State.Var) {
                    Var v2 = new Var(t);
                    getToken();
                    return new BuiltInRelation(v1, s, v2);
                } else {
                    Number n2 = new Number(state.toString(), t);
                    getToken();
                    return new BuiltInRelation(v1, s, n2);
                }
            } else if (state == State.Eq || state == State.Neq) {
                //comp op 2, term is next
                String s = t;
                getToken();
                Term tm = term();
                return new BuiltInRelation(v1, s, tm);                
            } else {
                errorMsg("Built-in Relation");
            }
        } else if (state == State.Int || state == State.Float || state == State.FloatE) {
            //<built-in relation> → 
            //<number> <comp op 1> <var> |
            //<number> <comp op 1> <number> | 
            //<number> <comp op 2> <term>
            Number n1 = new Number(state.toString(), t);
            getToken(); //number token
            String s = t;
            getToken(); //operator token
            if (state == State.Var) {
                Var v2 = new Var(t);
                getToken();
                return new BuiltInRelation(n1, s, v2);
            } else if (state == State.Int || state == State.Float || state == State.FloatE) {
                Number n2 = new Number(state.toString(), t);
                getToken();
                return new BuiltInRelation(n1, s, n2);
            } else {
                Term t2 = term();
                return new BuiltInRelation(n1, s, t2);
            }            
        } else if (state == State.Functor) {
            //<built-in relation> → 
            //<functor> <comp op 2> <term> 
            FunctorTerms ft = functorTerms();
            String s = t;
            getToken();
            Term tm = term();
            return new BuiltInRelation(ft, s, tm);
        } else {
            //<built-in relation> → 
            //<list term> <comp op 2> <term> 
            List l = list();
            String s = t;
            getToken();
            Term tm = term();
            return new BuiltInRelation(l,s,tm);            
        }
        return null;
    }
    
    public static ArithExp arithExp() {
        if (state == State.Var) {
            Var v1 = new Var(t);
            getToken();
            String s = t;
            getToken();
            if (state == State.Var) {
                Var v2 = new Var(t);
                getToken();
                return new ArithExp(v1, s, v2);
            } else {
                Number n2 = new Number(state.toString(), t);
                getToken();
                return new ArithExp(v1, s, n2);
            }
        } else {
            Number n1 = new Number(state.toString(), t);
            getToken();
            String s = t;
            if (state == State.Var) {
                Var v2 = new Var(t);
                getToken();
                return new ArithExp(n1, s, v2);
            } else {
                Number n2 = new Number(state.toString(), t);
                getToken();
                return new ArithExp(n1, s, n2);
            }
        }
    }
    
    public static Functor functor() {
        //Functor is final state
        if (state == State.Functor) {
            Functor f = new Functor(t);
            getToken();
            return f;
        } else {
            errorMsg("functor");
        }
        return null;
    }
    
    public static void main(String argv[]) {
        // argv[0]: input file containing an assignment list
        // argv[1]: output file displaying the parse tree		
        setIO( argv[0], argv[1] );
        setLex();
        getToken();

        Clauses mc = clauses(); // build a parse tree
        if (!errorFound) {
            mc.printParseTree("");
        }
        closeIO();
    }

    private static void errorMsg(String s) {
        errorFound = true;
        display(t+" : Syntax Error, unexpected symbol");
        if (s.length() > 0) {
            display(" where \"" + s + "\" expected");
        }
        displayln("");
    }    
}
