package parser;

import org.junit.Test;

import junit.framework.TestCase;
import lexer.Lexer;

public class ParserTest extends TestCase{

	@Test
	public void testShouldParseCorrect(){
	    String foo = "40+2*2";
	    String foo2 = "---40*2-2";
	    String foo3 = "40+-2/2";
	    AST.lexer = new Lexer(foo);
	    NumericExpression test = new NumericExpression(AST.lexer.getTokens());
	    assertTrue(test.parse()==44f);
	    AST.lexer = new Lexer(foo2);
	    NumericExpression test2 = new NumericExpression(AST.lexer.getTokens());
	    assertTrue(test2.parse()==-82f);
	    AST.lexer = new Lexer(foo3);
	    NumericExpression test3 = new NumericExpression(AST.lexer.getTokens());
	    assertTrue(test3.parse()==39f);
	    
	    
	    
	    String bar = "true | false &false|true ";
	    String bar2 = "true | false &false|true == false";
	    String bar3 = "!!true | !false &!false|true == !false";
	    AST.lexer = new Lexer(bar);
	    BooleanExpression test4 = new BooleanExpression(AST.lexer.getTokens());
	    assertTrue(test4.parse());
	    AST.lexer = new Lexer(bar2);
	    BooleanExpression test5 = new BooleanExpression(AST.lexer.getTokens());
	    assertFalse(test5.parse());
	    AST.lexer = new Lexer(bar3);
	    BooleanExpression test6 = new BooleanExpression(AST.lexer.getTokens());
	    assertTrue(test6.parse());
	}
	
}
