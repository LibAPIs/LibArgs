# LibArgs

A simple helper library for processing command line arguments.

## Maven Dependency

Include the library in your project by adding the following dependency to your pom.xml

```
<dependency>
	<groupId>com.mclarkdev.tools</groupId>
	<artifactId>libargs</artifactId>
	<version>1.1</version>
</dependency>
```

## Example

Initialize the library by using the array of arguments passed into the main class.

```
public static void main(String[] args) {
	LibArgs libArgs = new LibArgs(args);
	System.out.printf("My name is %s.", libArgs.getString("name"));
}
```

Then run your application with the desired argumentsi, accessing the values in code.

```
$ java -jar myApp.jar --name Matt
My name is Matt.
```
