
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */

/**
 *
 * @author Nassim
 */
public class Foo {
    
    int a, b, a1, b1, a2, b2, c, d, e, f, z, h, omega;
    float alpha, gamma, urlu;
    OutputStreamWriter osw;
    private void blabla() {
        omega = 3;
        System.out.println("hello world");
    }
    public void example() {
        int HELOOOOOOOO = 2;
        double a = 5;
        urlu = 2;
        this.gamma = 5;
        b++;
        osw = new OutputStreamWriter(null);
        blabla();
        if(HELOOOOOOOO == 2)
        {
            System.out.println("bonjour");
        }
        try {
            FileInputStream fis = new FileInputStream(new File("salut"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger();
        }
        while(a == b)
        {
            System.out.println("salut");
        }
        for(int i = 0; i < z; i++)
        {
            System.out.println("salut");
        }
        do {
            System.out.println("salut");
        } while(a == b);
        if (a == b) {
            if (a1 == b1) {
                fiddle();
            } else if (a2 == b2) {
                fiddle();
            } else {
                fiddle();
            }
        } else if (c == d) {
            while (c == d) {
                fiddle();
            }
        } else if (e == f) {
            for (int n = 0; n < h; n++) {
                fiddle();
            }
        } else {
            switch (z) {
                case 1:
                    fiddle();
                    break;
                case 2:
                    fiddle();
                    break;
                case 3:
                    fiddle();
                    break;
                default:
                    fiddle();
                    break;
            }
        }
    }
    
    public void fiddle() {
        System.out.println("salut " + omega);
    }
}
