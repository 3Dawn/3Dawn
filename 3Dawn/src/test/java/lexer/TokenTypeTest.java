package lexer;

import org.junit.Test;

import junit.framework.TestCase;


public class TokenTypeTest extends TestCase{
	
	@Test
	public void testShouldBeTokenType() {
		assertTrue(TokenType.isTokenType("{"));
		assertTrue(TokenType.isTokenType("}"));
		assertTrue(TokenType.isTokenType("("));
		assertTrue(TokenType.isTokenType(")"));
		assertTrue(TokenType.isTokenType("if"));
		assertTrue(TokenType.isTokenType("else"));
		assertTrue(TokenType.isTokenType("elseif"));
		assertTrue(TokenType.isTokenType("print"));
		assertTrue(TokenType.isTokenType("while"));
		assertTrue(TokenType.isTokenType("="));
		assertTrue(TokenType.isTokenType("15616548912"));
		assertTrue(TokenType.isTokenType("\n   \t   "));
//		assertTrue(TokenType.isTokenType("+"));
//		assertTrue(TokenType.isTokenType("-"));
//		assertTrue(TokenType.isTokenType("*"));
//		assertTrue(TokenType.isTokenType("/"));
//		assertTrue(TokenType.isTokenType("&"));
//		assertTrue(TokenType.isTokenType("&&"));
		assertTrue(TokenType.isTokenType("|"));
		assertTrue(TokenType.isTokenType("||"));
		assertTrue(TokenType.isTokenType("^"));
//		assertTrue(TokenType.isTokenType("!"));
//		assertTrue(TokenType.isTokenType("<"));
//		assertTrue(TokenType.isTokenType(">"));
//		assertTrue(TokenType.isTokenType("<="));
//		assertTrue(TokenType.isTokenType(">=")); 
//		assertTrue(TokenType.isTokenType("=="));
//		assertTrue(TokenType.isTokenType("false"));
//		assertFalse(TokenType.isTokenType("false&"));
//		assertTrue(TokenType.isTokenType("true"));
		
//		assertFalse(TokenType.isTokenType("==="));
	
		assertTrue("while".matches(TokenType.WhileKeyword.getValue()));
		assertTrue("if".matches(TokenType.IfKeyword.getValue()));
		assertTrue("else".matches(TokenType.ElseKeyword.getValue()));
		assertTrue("elseif".matches(TokenType.ElseIfKeyword.getValue()));
		assertTrue("while".matches(TokenType.WhileKeyword.getValue()));
		
		assertTrue("23891273981729".matches(TokenType.Integer.getValue()));
		assertTrue("-23891273981729".matches(TokenType.Integer.getValue()));	
		assertFalse("--23891273981729".matches(TokenType.Integer.getValue()));	
		assertFalse("2389127.3981729".matches(TokenType.Integer.getValue()));
		
//		assertTrue("&".matches(TokenType.BooleanOperation.getValue())); 
//		assertTrue("&&".matches(TokenType.BooleanOperation.getValue()));
//		assertTrue("|".matches(TokenType.BooleanOperation.getValue()));
//		assertTrue("||".matches(TokenType.BooleanOperation.getValue()));
//		assertTrue("^".matches(TokenType.BooleanOperation.getValue()));
//		assertTrue("!".matches(TokenType.BooleanInverter.getValue()));
//		assertTrue("!!".matches(TokenType.BooleanInverter.getValue()));
//		assertTrue("!!!".matches(TokenType.BooleanInverter.getValue()));
		
		assertTrue(">".matches(TokenType.LiteralComperator.getValue()));
		assertTrue("<".matches(TokenType.LiteralComperator.getValue()));
		assertTrue(">=".matches(TokenType.LiteralComperator.getValue()));
		assertTrue("<=".matches(TokenType.LiteralComperator.getValue()));
		assertTrue("==".matches(TokenType.LiteralComperator.getValue()));
	}

}
