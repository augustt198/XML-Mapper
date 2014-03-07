package im.prox.mapper.interpret;

import java.util.HashMap;
import java.util.Map;

public class InterpreterStore {

	private static Map<Class, Interpreter> map = new HashMap<>();

	public static Interpreter get(Class c) {
		return map.get(c);
	}

	public static void addInterpreter(Class c, Interpreter i) {
		map.put(c, i);
	}

}
