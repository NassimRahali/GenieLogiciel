class TCC4sur10 {
    private int x, y;
    
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
    
    
    public void methodE() {
        System.out.println("salut");
    }
}