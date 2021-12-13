import java.util.logging.Logger;

public class SampleObject {
    public void sampleMethod() {
        System.out.println("Sample Method");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object of class " + this.getClass().getName() + " being garbage collected ");
        super.finalize();
    }
}
