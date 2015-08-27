/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package test;

/**
 *
 * @author Nassim
 */
public class SuperTest {

    int a = 2, b = 3;
    int c;
    
    public SuperTest() {
        this.a = 5;
    }

    public void foo1() {
        System.out.println("hello");
    }
    public void foo2() {
        System.out.println("hello");
    }
    public void foo3() {
        System.out.println("hello");
    }
            
    public int getA() {
        if(a == 2)
            System.out.println("salut");
        return a;
    }

    public int getB() {
        if(a == 2)
            System.out.println("salut");
        else
            System.out.println("salut");
        return b;
    }

    public int getC() {
        while(a == 2)
        {
            System.out.println("salut");
        }
        return c;
    }

    public void setA(int a) {
        for(int i = 0; i < 5; i++)
        {
            System.out.println("salut");
        }
        this.a = a;
    }

    public void setB(int b) {
        switch(b)
        {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
        this.b = b;
    }

    public void setC(int c) {
        do {
            System.out.println("salut");
        } while(c == 2);
        this.c = c;
    }
}
