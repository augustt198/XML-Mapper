package im.prox.mapper.interpret;

import im.prox.mapper.exception.InterpretationException;

public abstract class Interpreter<T> {

	protected Class<T> type;

	public Interpreter(Class<T> type) {
		this.type = type;
	}

	public Class<T> getType() {
		return type;
	}

	public abstract T convert(String s) throws InterpretationException;

}
