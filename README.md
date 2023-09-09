# LibArgs

A simple java helper library for processing command line arguments.

## Maven Dependency

Include the library in your project by adding the following dependency to your pom.xml

```
<dependency>
	<groupId>com.mclarkdev.tools</groupId>
	<artifactId>libargs</artifactId>
	<version>1.5.1</version>
</dependency>
```

## Example

Initialize the library by using the array of arguments passed into the main class.

```
public static void main(String[] args) {
	LibArgs.instance().parse(args);
	System.out.printf("Hello, %s.", LibArgs.instance().getString("name"));
}
```

Then simply run your application with the desired arguments:

```
$ java -jar myApp.jar --name world
Hello, world.
```

# License

Open source & free for all. ❤
