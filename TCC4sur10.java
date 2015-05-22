class TCC4sur10 {
    private int x, y, z;
    
    public void methodA() {
        x = 5;
    }
    
    public void methodB() {
        x = 7;
    }
    
    public void methodC() {
        y = 5;
        x = 5; // Augmente la cohésion
    }
    
    public void methodD() {
        y = 7;
    }
    
    public void foo() {
        z = 5;
    }
    
    public void foo2() {
        System.out.println("salut");
    }
}