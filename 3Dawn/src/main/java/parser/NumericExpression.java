package parser;

import java.util.LinkedList;

import lexer.TokenType;

public class NumericExpression extends AST<Float> {

	public NumericExpression(LinkedList<TokenType> tokens) {
		super(tokens);
	}

	public Float parse() {
		nextChar();
		float x = parseExpression();
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

	private float parseExpression() {
		float x = parseTerm();
		while (true) {
			if (eat(TokenType.Addition))
				x += parseTerm(); 
			else if (eat(TokenType.Substraction))
				x -= parseTerm(); 
			else
				return x;
		}
	}

	private float parseTerm() {
		float x = parseFactor();
		while (true) {
			if (eat(TokenType.Multiplication))
				x *= parseFactor(); 
			else if (eat(TokenType.Division))
				x /= parseFactor(); 
			else
				return x;
		}
	}

	private float parseFactor() {
		boolean negated = false;
		while(true){
			if (eat(TokenType.Substraction))
				negated = !negated;
			else break;
		}
		nextChar();
		if(negated) return -lexer.getNextNumberOccurances();
		else return lexer.getNextNumberOccurances();
	}

}
