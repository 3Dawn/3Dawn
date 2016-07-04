package parser;

import java.util.LinkedList;

import lexer.TokenType;

public class BooleanExpression extends AST<Boolean> {

	public BooleanExpression(LinkedList<TokenType> tokens) {
		super(tokens);
	}

	public Boolean parse() {
		nextChar();
		boolean x = parseExpression();
		return x;
	}

	TokenType actual;

	private void nextChar() {
		actual = tokens.pollFirst();
	}

	private boolean eat(TokenType charToEat) {
		if (actual != null && actual.getValue().equals(charToEat.getValue())) {
			nextChar();
			return true;
		}
		return false;
	}

	private boolean parseExpression() {
		boolean x = parseEqual();
		while (true) {
			if (eat(TokenType.EqualsComperator)){
				x =  x == parseEqual(); 
			}
			else
				return x;
		}
	}
	
	private boolean parseEqual() {
		boolean x = parseDisjunction();
		while (true) {
			if (eat(TokenType.Disjunction))
				x |= parseDisjunction(); 
			else
				return x;
		}
	}

	private boolean parseDisjunction() {
		boolean x = parseXor();
		while (true) {
			if (eat(TokenType.Conjunction))
				x &= parseXor(); 
			else
				return x;
		}
	}
	
	private boolean parseXor() {
		boolean x = parseBool();
		while (true) {
			if (eat(TokenType.XORKeyword))
				x ^= parseBool(); 
			else
				return x;
		}
	}

	private boolean parseBool() {
		boolean negated = false;
		while(true){
			if (eat(TokenType.BooleanInverter))
				negated = !negated;
			else break;
		}

		
		if (eat(TokenType.TrueKeyword)) return !negated;
		else if (eat(TokenType.FalseKeyword)) return negated;
		else
			throw new RuntimeException("Unaccepted boolean expression");
	}

}
