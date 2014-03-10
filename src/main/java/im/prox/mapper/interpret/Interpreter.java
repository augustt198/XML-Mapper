package im.prox.mapper.interpret;

import im.prox.mapper.exception.InterpretationException;

public abstract class Interpreter<T> {

	Class<T> type;

	public Interpreter(Class<T> type) {
		this.type = type;
		InterpreterStore.addInterpreter(type, this);
	}

	public abstract T convert(String s) throws InterpretationException;

}
