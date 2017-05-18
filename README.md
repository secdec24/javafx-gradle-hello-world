# JavaFX Gradle Plugin: Hello World Example

There are a lot of good resources available on how to use the [Java plugin for Gradle][java-plugin-gradle], but I had a hard time finding information on how to use it with JavaFX while also allowing me to do the following with my projects:

**1. Use the the [Model-View-Controller][mvc] pattern.**

**2. Use FXML to build the UI.** A lot of the examples I found did not use FXML and simply coded their UI elements in their Java classes.

**3. Work with IntelliJ and Scene Builder.** I wanted to be able to use Gradle from within my IDE and to still be able to use Scene Builder to create my FXML files.

I created this Hello World example to demonstrate how to use Gradle to build a JavaFX project while satisfying the above requirements.


## Project Structure

One of the first issues I had was figuring out what the project structure should look like. Following the Model-View-Controller pattern, my JavaFX projects look the following way:

```
src
|--Main.java
|--model
  |--Model1.java
  |--Model2.java
|--util
  |--Util1.java
  |--css
    |--style1.css
  |--img
    |--img1.jpg
|--view
  |--View1.fxml
  |--View1Controller.java
```

This structure is not possible with Gradle since it expects to find the project source code under `src/main/java` and resources to be included in the JAR file under `src/main/resources`. Based on these expectations and on the discussion in [this issue][issue], I decided to use the following structure:

```
src/main/java
|--Main.java
|--model
  |--Model1.java
|--controller
  |--View1Controller.java
|--util
  |--Util1.java

src/main/resources
|--view
  |--View1.fxml
|--css
  |--style1.css
|--img
  |--img1.jpg
```

The issue with this new structure is that it becomes a bit more annoying to link each view with its controller in Scene Builder. When selecting a `Controller class`, it is now necessary to enter it manually since the dropdown is no longer available.

For this Hello World example, the controller class input in Scene Builder is `app.helloworld.controller.HelloWorldController` for the directory structure:

```
src/main/java
|--app/helloworld/controller
  |--HelloWorldController.java
```

The same issue arises when adding an `fx:id` or a function call to an UI object from within Scene Builder: the dropdown is gone so the id and function name must be added manually.


## JavaFX Gradle plugin

To actually build the project with Gradle, it is easier to use a JavaFX Gradle plugin such as [Danno Ferrin's aka shemnon][shemnon] or [FibreFoX's][fibre-fox]. This Hello World example originally used shemnon's plugin. However, since it is no longer being maintained, I have updated the project's `build.gradle` to use  FibreFoX's instead. I have kept the instructions for shemnon's plugin in case someone is still interested in using it.

Information on what each Gradle task does (excluding JavaFX specific tasks) can be found in the [Gradle documentation][gradle-doc].

#### FibreFoX Plugin (recommended)

The README for FibreFoX's plugin is very detailed and complete. For this example, I used the [minimal setup of `build.gradle`][min-setup-build-gradle]. The [Gradle Tasks][gradle-tasks] section also has a helpful description of each of the JavaFX specific tasks.

#### shemnon Plugin

The plugin can be used by adding `apply from: "http://dl.bintray.com/content/shemnon/javafx-gradle/8.1.1/javafx.plugin"` to the [build.gradle][build-gradle] file created by IntelliJ when starting a new Gradle project.

After adding this line, the Gradle tasks under `Tasks > build` should now show some JavaFX specific tasks.

Additionally, since my main class is in `src/main/java/app/helloworld/HelloWorld.java`, I added the following to `build.gradle`:

```
javafx {
    mainClass = 'app.helloworld.HelloWorld'
}
```


## License

Code released under the MIT license. See [LICENSE][license] for details.

[java-plugin-gradle]: https://docs.gradle.org/current/userguide/tutorial_java_projects.html
[mvc]: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
[issue]: https://github.com/kelemen/netbeans-gradle-project/issues/160
[shemnon]: https://bitbucket.org/shemnon/javafx-gradle/
[fibre-fox]: https://github.com/FibreFoX/javafx-gradle-plugin
[gradle-doc]: https://docs.gradle.org/current/userguide/java_plugin.html
[min-setup-build-gradle]: https://github.com/FibreFoX/javafx-gradle-plugin#minimal-setup-of-buildgradle
[gradle-tasks]: https://github.com/FibreFoX/javafx-gradle-plugin#gradle-tasks
[build-gradle]: https://github.com/GerardoPrada/javafx-gradle-hello-world/blob/master/build.gradle
[license]: https://github.com/GerardoPrada/javafx-gradle-hello-world/blob/master/LICENSE
