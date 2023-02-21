package lab3.Lab3_phone;

import dao.ManufactureDAO;
import dao.PhoneDAO;
import model.Manufacture;
import model.Phone;

/**
 * Hello world!
 *
 */
public class Test 
{
    public static void main( String[] args )
    {
    	ManufactureDAO m = new ManufactureDAO();
    	m.add(new Manufacture("B01","Apple","China",1000));
        PhoneDAO p = new PhoneDAO();
        //p.add(new Phone("A03","iphone13",9000,"green","USA",100));
        //boolean re = p.add(new Phone("A0","iphone8",8000,"gold","USA",100));
        //p.update(new Phone("A03","iphone8",8500,"gold","USA",200));
        //System.err.println(re);
        p.remove(2);
        boolean pr = p.hasPhonePricedAbove50Million();
        System.err.println(pr);
        
        for(Phone s : p.getAll()) {
        	System.out.println(s);
        }
    }
}
