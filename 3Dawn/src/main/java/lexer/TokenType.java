package lexer;

public enum TokenType {
	// all the java keywords, if needed
//	AbstractKeyword("abstract"),ContinueKeyword("continue"),ForKeyword("for"),NewKeyword("new"),SwitchKeyword("switch"),AssertKeyword("assert"),
//	DefaultKeyword("default"),GotoKeyword("goto"),PackageKeyword("package"),SynchronizedKeyword("synchronized"),BooleanKeyword("boolean"),DoKeyword("do"),
//	IfKeyword("if"),PrivateKeyword("private"),ThisKeyword("this"),BreakKeyword("break"),DoubleKewyword("double"),ImplementsKeyword("implements"),
//	ProtectedKeyword("protected"),ThrowKeyword("throw"),ByteKeyword("byte"),ElseKeyword("else"),ImportKeyword("import"),PublicKeyword("public"),
//	ThrowsKeyword("throws"),CaseKeyword("case"),EnumKeyword("enum"),InstanceOfKeyword("instanceof"),ReturnKeyword("return"),TransientKeyword("transient"),
//	CatchKeyword("catch"),ExtendsKeyword("extends"),IntKeyword("int"),ShortKeyword("short"),TryKeyword("try"),
//	CharKeyword("char"),FinalKeyword("final"),InterfaceKeyword("interface"),StaticKeyword("static"),VoidKeyword("void"),
//	ClassKeyword("class"),FinallyKeyword("finally"),LongKeyword("long"),StrictfpKeyword("strictfp"),VolatileKeyword("volatile"),
//	ConstKeyword("const"),FloatKeyword("float"),NativeKeyword("native"),SuperKeyword("super"),WhileKeyword("while"),
	ClassKeyword("class"),ObjectKeyword("object"),
	IfKeyword("if"),ElseKeyword("else"),ElseIfKeyword("elseif"),WhileKeyword("while"),LoopKeyword("loop"),
//	ImportKeyword("import"),ExtendsKeyword("extends"),IntKeyword("int"),
	PrintKeyword("print"),
	ClosingBrace("[}]"),OpeningBrace("[{]"),
	ClosingParathesis("[)]"),OpeningParathesis("[(]"),
	TrueKeyword("true"),FalseKeyword("false"),
	EqualsOperator("="),EqualsComperator("=="),
	Identifier(""),
	Integer("-?[0-9]+"),Whitespace("[ \t\f\r\n]+"),
	Conjunction("&"),Disjunction("[|]{1,2}"),XORKeyword("[\\^]"),
	Addition("+"),Substraction("-"),Division("/"),Multiplication("*"),
	LessEqualsComperator("<="),LessComperator("<"),
	GreaterEqualsComperator(">="),GreaterComperator(">"),
	NumericOperation("[*/+\\-]"), 
//	BooleanOperation("[\\^]"),
//	BooleanOperation1("[&|\\^]"),	BooleanOperation2("[&]{2}|[|]{2}"),
	BooleanInverter("!"), 
	LiteralComperator("([><][=]?)|[=][=]");
 
	private String symbol;
	private TokenType(String symbol){
		this.symbol = symbol;
	}
	
	public String getValue() {
		return symbol;
	}
	
	public static boolean isTokenType(String st){
		for (TokenType tt: TokenType.values()) {
			if (st.matches(tt.getValue())) return true;
		}
		return false;
	}
	
	
}