package lexer;

import junit.framework.TestCase;

import org.junit.Test;

public class LexerTest extends TestCase{

	@Test
	public void testShouldLexCorrect(){
		String[] test = "foo   bar\n\n;\n foobar;foobar;;;foobar\n\t  \t;;; foo   \t bar".
				replaceAll("[\\n]+", ";").replaceAll("[\\s]", "").split("[;]+");
		assertTrue(test.length == 5);
		for (int i = 0; i < test.length; i++) {
			assertTrue(test[i].equals("foobar"));
		}
		String test2 = "foobar/*shouldntbeshown*/foobar/*shouldntbeshown*/foobar";
		String test3 = "foobar//shouldntbeshown\nfoobar";
		String regex = "(/\\*(?:.|[\\n\\r])*?\\*/)|(//.*\\n)";
		test2 = test2.replaceAll(regex,";");
		test3 = test3.replaceAll(regex, ";");
		assertTrue(test2.equals("foobar;foobar;foobar"));
		assertTrue(test3.equals("foobar;foobar"));
		
		Lexer lexer = new Lexer("class    test    {");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.ClassKeyword.getValue()));
		assertTrue(lexer.getTokens().get(1).getValue().equals(TokenType.Identifier.getValue()));
		assertTrue(lexer.getTokens().get(2).getValue().equals(TokenType.OpeningBrace.getValue()));
		assertTrue(lexer.getVariableOccurances().get(0).equals("test"));
		
		lexer = new Lexer("variable2");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.Identifier.getValue()));
		assertTrue(lexer.getVariableOccurances().get(0).equals("variable2"));
		
		lexer = new Lexer("variable2 = 2");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.Identifier.getValue()));
		assertTrue(lexer.getTokens().get(1).getValue().equals(TokenType.EqualsOperator.getValue()));
		assertTrue(lexer.getTokens().get(2).getValue().equals(TokenType.Number.getValue()));
		assertTrue(lexer.getVariableOccurances().get(0).equals("variable2"));
		assertTrue(lexer.getNumberOccurances().get(0) == 2);
		
		lexer = new Lexer("variable2 = variable1");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.Identifier.getValue()));
		assertTrue(lexer.getTokens().get(1).getValue().equals(TokenType.EqualsOperator.getValue()));
		assertTrue(lexer.getTokens().get(2).getValue().equals(TokenType.Identifier.getValue()));
		assertTrue(lexer.getVariableOccurances().get(0).equals("variable2"));
		assertTrue(lexer.getVariableOccurances().get(1).equals("variable1"));
		
		lexer = new Lexer("true&false|^");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.TrueKeyword.getValue()));
		assertTrue(lexer.getTokens().get(1).getValue().equals(TokenType.Conjunction.getValue()));
		assertTrue(lexer.getTokens().get(2).getValue().equals(TokenType.FalseKeyword.getValue()));
		assertTrue(lexer.getTokens().get(3).getValue().equals(TokenType.Disjunction.getValue()));
		assertTrue(lexer.getTokens().get(4).getValue().equals(TokenType.XORKeyword.getValue()));
		
		lexer = new Lexer("+-*/");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.Addition.getValue()));
		assertTrue(lexer.getTokens().get(1).getValue().equals(TokenType.Substraction.getValue()));
		assertTrue(lexer.getTokens().get(2).getValue().equals(TokenType.Multiplication.getValue()));
		assertTrue(lexer.getTokens().get(3).getValue().equals(TokenType.Division.getValue()));
		
		lexer = new Lexer(">=><=<");
		assertTrue(lexer.getTokens().get(0).getValue().equals(TokenType.GreaterEqualsComperator.getValue()));
		assertTrue(lexer.getTokens().get(1).getValue().equals(TokenType.GreaterComperator.getValue()));
		assertTrue(lexer.getTokens().get(2).getValue().equals(TokenType.LessEqualsComperator.getValue()));
		assertTrue(lexer.getTokens().get(3).getValue().equals(TokenType.LessComperator.getValue()));
	}
	
}
