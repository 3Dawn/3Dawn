package lexer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

	LinkedList<TokenType> tokens = new LinkedList<TokenType>();
	LinkedList<String> variableOccurance = new LinkedList<String>();
	LinkedList<Float> numberOccurance = new LinkedList<Float>();
	
	public Lexer(String input){
		// removes all comments
		input = input.replaceAll("(/\\*(?:.|[\\n\\r])*?\\*/)|(//.*\\n)", ";");
		// creates an array with the codelines with all whitespaces removed
		String[] lines = input.
				replaceAll("[\\n]+", ";").replaceAll("[\\s]", "").split("[;]+");
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			while(line.length() != 0){

			// if
			if(line.matches(TokenType.IfKeyword.getValue()+TokenType.OpeningParathesis.getValue()+"($|.*)")){
				tokens.add(TokenType.IfKeyword);tokens.add(TokenType.OpeningParathesis);
				line=line.substring(3, line.length());
			}
			// else
			else if(line.equals("else")){
				tokens.add(TokenType.IfKeyword);
				break;
			}
			// elseif
			else if(line.matches(TokenType.ElseIfKeyword.getValue()+TokenType.OpeningParathesis.getValue() +"($|.*)")){
				tokens.add(TokenType.ElseIfKeyword);tokens.add(TokenType.OpeningParathesis);
				line=line.substring(7, line.length());
			}
			// print
			else {if(line.matches(TokenType.PrintKeyword.getValue()+TokenType.OpeningParathesis.getValue() +"($|.*)")){
				tokens.add(TokenType.PrintKeyword);tokens.add(TokenType.OpeningParathesis);
				line=line.substring(6, line.length());
			}			
			// while
			else if(line.matches(TokenType.ElseIfKeyword.getValue()+TokenType.OpeningParathesis.getValue() +"($|.*)")){
				tokens.add(TokenType.WhileKeyword);tokens.add(TokenType.OpeningParathesis);
				line=line.substring(6, line.length());
			}
			// loop
			else if(line.matches(TokenType.ElseIfKeyword.getValue()+TokenType.OpeningParathesis.getValue() +"($|.*)")){
				tokens.add(TokenType.LoopKeyword);tokens.add(TokenType.OpeningParathesis);
				line=line.substring(5, line.length());
			}
			// true
			else if(line.matches(TokenType.TrueKeyword.getValue()+"($|[?=(\\W)].*)")){
				tokens.add(TokenType.TrueKeyword);
				line=line.substring(4, line.length());
			}
			// false
			else if(line.matches(TokenType.FalseKeyword.getValue()+"($|[?=(\\W)].*)")){
				tokens.add(TokenType.FalseKeyword);
				line=line.substring(5, line.length());
			}
			// &&
			else if(line.startsWith("&&")){
				tokens.add(TokenType.Conjunction);
				line=line.substring(2, line.length());
			}
			// &
			else if(line.startsWith("&")){
				tokens.add(TokenType.Conjunction);
				line=line.substring(1, line.length());
			}
			// ||
			else if(line.startsWith("||")){
				tokens.add(TokenType.Disjunction);
				line=line.substring(2, line.length());
			}
			// |
			else if(line.startsWith("|")){
				tokens.add(TokenType.Disjunction);
				line=line.substring(1, line.length());
			}
			// ^
			else if(line.startsWith("^")){
				tokens.add(TokenType.XORKeyword);
				line=line.substring(1, line.length());
			}
			
			// +
			else if(line.startsWith("+")){
				tokens.add(TokenType.Addition);
				line=line.substring(1, line.length());
			}
			// -
			else if(line.startsWith("-")){
				tokens.add(TokenType.Substraction);
				line=line.substring(1, line.length());
			}
			// *
			else if(line.startsWith("*")){
				tokens.add(TokenType.Multiplication);
				line=line.substring(1, line.length());
			}
			// /
			else if(line.startsWith("/")){
				tokens.add(TokenType.Division);
				line=line.substring(1, line.length());
			}

			// ==
			else if(line.startsWith("==")){
				tokens.add(TokenType.EqualsComperator);
				line=line.substring(2, line.length());
			}
			
			// >=
			else if(line.startsWith(">=")){
				tokens.add(TokenType.GreaterEqualsComperator);
				line=line.substring(2, line.length());
			}
			// >
			else if(line.startsWith(">")){
				tokens.add(TokenType.GreaterComperator);
				line=line.substring(1, line.length());
			}
			// <=
			else if(line.startsWith("<=")){
				tokens.add(TokenType.LessEqualsComperator);
				line=line.substring(2, line.length());
			}
			// <
			else if(line.startsWith("<")){
				tokens.add(TokenType.LessComperator);
				line=line.substring(1, line.length());
			}
			
			// !
			else if(line.startsWith("!")){
				tokens.add(TokenType.BooleanInverter);
				line=line.substring(1, line.length());
			}
			
			// =
			else if(line.startsWith("=")){
				tokens.add(TokenType.EqualsOperator);
				line=line.substring(1, line.length());
			}
			
			// point
			else if(line.startsWith(".")){
					tokens.add(TokenType.Point);
					line=line.substring(1, line.length());
				}	

			// class
			else if(line.matches(TokenType.ClassKeyword.getValue()+"[\\w]+[{].*")){
					variableOccurance.add(line.substring(5, line.indexOf("{")));
					tokens.add(TokenType.ClassKeyword);
					tokens.add(TokenType.Identifier);
					tokens.add(TokenType.OpeningBrace);
					line=line.substring(line.indexOf("{")+1, line.length());
				}	
			// object
			else if(line.matches(TokenType.ObjectKeyword.getValue()+"[\\w]+[{].*")){
				variableOccurance.add(line.substring(6, line.indexOf("{")));
				tokens.add(TokenType.ObjectKeyword);
				tokens.add(TokenType.Identifier);
				tokens.add(TokenType.OpeningBrace);
				line=line.substring(line.indexOf("{")+1, line.length());
			}	
			
			// identifier
			else if(line.matches(TokenType.Identifier.getValue()+"($|[\\W].*)")){
				Pattern p = Pattern.compile("[\\W]");  
				Matcher m = p.matcher(line);
				int position = -1;
				if (m.find()) {
				   position = m.start();
				}
				String variable = "";
				if(position == -1){
					variable = line;
					line="";
				}else {
					variable = line.substring(0, position);
					line=line.substring(position, line.length());
				}
				variableOccurance.add(variable);
				tokens.add(TokenType.Identifier);
			}
			
			// number
			else if(line.matches(TokenType.Number.getValue()+"($|[^0-9].*)")){
				Pattern p = Pattern.compile("[^0-9]");  
				Matcher m = p.matcher(line);
				int position = -1;
				if (m.find()) {
				   position = m.start();
				}
				float number ;
				if(position == -1){
					number = Float.parseFloat(line);
					line="";
				}else {
					number = Float.parseFloat(line.substring(0, position));
					line=line.substring(position, line.length());
				}
				numberOccurance.add(number);
				tokens.add(TokenType.Number);
			}
			else{
				System.err.println("Lexer: Unknown tokentype: " + line);
				System.exit(0);
			}
			}
		}}
	}
	
	public LinkedList<TokenType> getTokens(){
		return tokens;
	}
	
	public LinkedList<String> getVariableOccurances(){
		return variableOccurance;
	}
	
	public LinkedList<Float> getNumberOccurances(){ 
		return numberOccurance;
	}
	
	public String getNextVariableOccurances(){
		return variableOccurance.poll();
	}
	
	public float getNextNumberOccurances(){ 
		return numberOccurance.poll();
	}
	
}
