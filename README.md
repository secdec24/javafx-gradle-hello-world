# Hello World

There are a lot of good resources available on how to use the [Java plugin for Gradle](https://docs.gradle.org/current/userguide/tutorial_java_projects.html), but I had a hard time finding information on how to use it with JavaFX while also allowing me to do the following with my projects:

**1. Use the the [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) pattern.**

**2. Use FXML to build the UI.** A lot of the examples I found did not use FXML and simply coded their UI elements in their Java classes.

**3. Work with IntelliJ and Scene Builder.** I wanted to be able to use Gradle from within my IDE and to still be able to use Scene Builder to create my FXML files.

I created this Hello World example to demonstrate how to use Gradle to build a JavaFX project while satisfying the above requirements.


## Project Structure

One of the first issues I had was figuring out what the project structure should look like. Following the Model-View-Controller pattern, my JavaFX projects look the following way:

```
src
	|
	Main.java
	model
		|
		Model1.java
		Model2.java
	util
		|
		Util1.java
		css
			|
			style1.css
		img
			|
			img1.jpg
	view
		|
		View1.fxml
		View1Controller.java
```

This structure is not possible with Gradle though since it expects to find the project source code under `src/main/java` and resources which will be included in the JAR file under `src/main/resources`. Based on this and on the discussion in [this issue](https://github.com/kelemen/netbeans-gradle-project/issues/160), I decided to use the following structure:

```
src/main/java
	|
	Main.java
	model
		|
		Model1.java
	controller
		|
		View1Controller.java
	util
		|
		Util1.java
src/main/resources
	|
	view
		|
		View1.fxml
	css
		style1.css
	img
		img1.jpg
```

The biggest problem with this new structure is that it becomes slightly harder to link each view with its controller in Scene Builder. When selecting a `Controller class`, it is now necessary to enter it manually since the dropdown is no longer available.

For this Hello World example, the controller class input in Scene Builder is `app.helloworld.controller.HelloWorldController` for the directory structure:

```
src/main/java
	|
	app/helloworld/controller
		|
		HelloWorldController.java
```

The same problem arises when adding an `fx:id` or a function call to an UI object from within Scene Builder: the dropdown is gone so the id and function name must be added manually.


## JavaFX Gradle plugin

To actually build the project with Gradle, it is easier to use a JavaFX Gradle plugin such as [Danno Ferrin's](https://bitbucket.org/shemnon/javafx-gradle/) or [FibreFox's](https://github.com/FibreFoX/javafx-gradle-plugin). I used the former for this example because it was the first one I found but it is not being maintained anymore so the latter is probably a better option.

The plugin can be used by adding `apply from: "http://dl.bintray.com/content/shemnon/javafx-gradle/8.1.1/javafx.plugin"` to the [build.gradle](https://github.com/GerardoPrada/javafx-gradle-hello-world/blob/master/build.gradle) file created by IntelliJ when starting a new Gradle project.

After adding this line, the Gradle tasks under `Tasks > build` should now show some JavaFX specific tasks. See the [Gradle documentation](https://docs.gradle.org/current/userguide/java_plugin.html) for information on what the tasks do (not including the JavaFX specific tasks).

Additionally, since my main class is in `src/main/java/app/helloworld/HelloWorld.java`, I added the following to `build.gradle`:

```
javafx {
    mainClass = 'app.helloworld.HelloWorld'
}
```


## Disclaimer

*This is a collection of information I found when learning how to use Gradle to build JavaFX projects. I am not an expert on the issue and the guide may contain incorrect or incomplete information. Please feel free to open an issue to discuss any errors or improvements.*


## License

Code released under the MIT license. See [LICENSE](https://github.com/GerardoPrada/javafx-gradle-hello-world/blob/master/LICENSE) for details.
