abstract class OrientGameObj extends GameObj
{
    private Orientation ori;

    public void setOri(Orientation inOri){
        ori = inOri;
    }

    public Orientation getOri(){
        return ori;
    }
}