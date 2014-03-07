package im.prox.mapper.interpret;

import org.dom4j.Element;

public abstract class Interpreter<T> {

	Class type;

	public Interpreter(Class type) {
		this.type = type;
		InterpreterStore.addInterpreter(type, this);
	}

	public abstract T convert(Element e) throws InterpretationException;

}
