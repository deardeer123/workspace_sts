public class DoubleBox<L,R> {
    private L left;
    private R right;

    public void set(L a, R b){
        this.left = a;
        this.right = b;
    }

    public String toString(){
        return left + " & " + right;
    }
}
