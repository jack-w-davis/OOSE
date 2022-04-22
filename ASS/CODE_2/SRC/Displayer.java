import java.util.*;

//The abstract interface
interface Displayer
{
    abstract public Grid<String> performOperation(Maze maze);
}


//TODO: Refactor into an internal part of a class.
//TODO: Make an orient dependent class that has some util methods
enum Dir
{
    UP,
    RIGHT,
    DOWN,
    LEFT
}