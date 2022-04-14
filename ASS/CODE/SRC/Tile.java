public interface Tile 
{}

interface Interactable extends Tile
{
    public void interact();
}

interface Collidable extends Tile
{
    // public void 
}

interface Drawable extends Tile
{

}