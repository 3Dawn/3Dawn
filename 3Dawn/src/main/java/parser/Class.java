package parser;

import java.util.LinkedList;

public class Class extends AST<Void>{
	
	String name;
	LinkedList<Statement> statements = new LinkedList<Statement>();
	LinkedList<Object> objects = new LinkedList<Object>();
	
	public Class(String name, LinkedList<Statement> statements,
			LinkedList<Object> objects) {
		super();
		this.name = name;
		this.statements = statements;
		this.objects = objects;
	}
	
	@Override
	public Void parse() {
		
		
		return null;
	}
	
	

}
