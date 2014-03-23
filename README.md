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

try {
    person = MappingFactory.map(person, /* Your XML Element */);
} catch(MappingException e) {
    e.printStackTrace();
}
```

## Annotation Descriptions

#### `@Attribute(String)`
Gets the value of the attribute given.

#### `@Path(String)`
Navigates through sub-elements when supplied a string in the format of `tag1.tag2.tag3...` - Should be used in conjunction with annotations that get a value from an element.

#### `@Text`
Gets the value of the inner text of an element.

#### `@Tag`
Gets a boolean value based on if the element exists.

#### `@Required`
Marks the field as necessary to be assigned a value - will throw a `MissingRequiredFieldException` if not.

## Conflicts

Not all annotations can be used in conjuction. Annotations that get a value from an element (`@Attribute`, `@Text`, `@Tag`) will throw an `IllegalAnnotationException` if used with each other.

## To Do
* Add mapping for Arrays (`[]` and `List`)
* Add ability to parse complex sub-elements defined in a `Mappable` class.
