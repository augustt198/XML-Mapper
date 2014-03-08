package im.prox.mapper.example;

import im.prox.mapper.annotation.Attribute;
import im.prox.mapper.annotation.Path;
import im.prox.mapper.annotation.Text;

public class Person {

	@Attribute("age")
	public String age;

	@Path("name.first")
	@Text
	public String firstName;

	@Path("name.last")
	@Text
	public String lastName;


}

/*
Represents this XML:

<person age="30">
	<name>
		<first>John</first>
		<last>Doe</last>
	</name>
</person>

 */
