# mdb-engine-core

This is the core part of my Game Engine written in Java using [LWJGL](https://www.lwjgl.org/).

## Getting started

Create a class which implements the IGameLogic interface
```java
public class SimpleExample implements IGameLogic
```

This class needs the main function, in which you can create the GameEngine and then start it.
It will be forced to implement the methods init, input, update, render and cleanup. These will be called by the Engine at the appropriate Time.

The creation of the GameEngine takes in the Title, Width and Height for a Window as its first three parameters, the fourth one is if vSync should be enabled and the last one is a class which implements IGameLogic.

Example:
```java
public static void main(String[] args) {
		GameEngine engine = new GameEngine("SimpleExample", 1280, 860, true, new SimpleExample());
		engine.start();
}
```

With this you should get a Window with black background and the given title, width and height.

**Code:** [SimpleExample](https://github.com/MattisDerBock/mdb-engine-core/blob/master/example/examples/SimpleExample.java)

For further reference check the [Wiki](https://github.com/MattisDerBock/mdb-engine-core/wiki) or the [Examples](https://github.com/MattisDerBock/mdb-engine-core/tree/master/example/examples).



### Features

* Automatic GameLoop
* Custom Event System
* Texture loading
* Model loading
* OpenGL Shader wrappers
* Nuklear GUI wrappers (WIP)
* Easy rendering System

### Prerequisites

This Engine carries all its required prerequisites. You shouldn't have to install anything in addition to that.

## Authors
* **Mattis Böckle** - *Initial Work* - [MattisDerBock](https://github.com/MattisDerBock)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgements

* [PurpleBooth](https://github.com/PurpleBooth) - *For the Templates to make this Readme*
