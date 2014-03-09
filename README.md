# XML-Mapper

A Java ORM for easily parsing XML.


Uses dom4j for intermediate parsing and handling of `Documents` and `Elements`

## Example

To represent the following XML
```xml
<person age="30">
  <name>
    <first>John</first>
    <last>Doe</last>
  </name>
</person>
```

The class `Person` could be created, using annotations to reflect values in the XML
```java
public class Person implements Mappable {

	@Attribute("age")
	public String age;

	@Path("name.first")
	@Text
	public String firstName;

	@Path("name.last")
	@Text
	public String lastName;
	
}
```

Values can then be assigned to the `Person` class by using the `MappingFactory`
```java
Person person = new Person();
MappedObject obj = new MappedObject(person);

try {
    person = (Person) MappingFactory.map(obj, base);
} catch(MissingRequiredFieldException e) {
    e.printStackTrace();
}
```


