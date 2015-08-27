class LCOM4_1 {
    private int x, y;
    
    public void methodA() {
        methodB();
    }
    
    public void methodB() {
        x = 7;
    }
    
    public void methodC() {
        y = 5;
        x = 2;
    }
    
    public void methodD() {
        y = 7;
        methodE();
    }

    public void methodE() {
    	return;
    }
}