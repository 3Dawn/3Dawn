package parser;

import java.util.LinkedList;

import lexer.Lexer;
import lexer.TokenType;

public abstract class AST<T> {

	public static Lexer lexer;
	LinkedList<TokenType> tokens = new LinkedList<TokenType>();
	public AST(String input) {	
		lexer = new Lexer(input);
		tokens = lexer.getTokens();
	}
	
	public AST(LinkedList<TokenType> tokens) {	
		this.tokens = tokens;
	}
	
	public abstract T parse();
	
}
