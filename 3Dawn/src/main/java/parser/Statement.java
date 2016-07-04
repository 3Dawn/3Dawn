package parser;


enum Type {
	Assignment,
	Object,
	Print,
	While,
	Loop,
	If,
	ObjectAction,
	NumericExpression,
	BooleanExpression,
	Expression;
	
}
public class Statement extends AST<Void>{

	Type type;
	
	Statement(Type type, AST ... nodes){
		this.type = type;
		switch (type) {
		case Assignment:
			
			break;
		case Object:
			
			break;
		case Print:
			
			break;
		case While:
			
			break;
		case Loop:
			
			break;
		case If:
			
			break;
		case ObjectAction:
						
			break;

		default:
			break;
		}
	}

	@Override
	public Void parse() {
		
		return null;
	}
	
	
}
