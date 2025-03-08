package ananpn.siistitjavajutut.TRA1_Stuff;

public class tetrationTest {
    public static void main(String[] args){
        long result = 1;
        for (long i = 0; i < 2000000000; i++)
            result += 1;
        System.out.println(result);
    }
}
