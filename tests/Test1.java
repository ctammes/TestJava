import junit.framework.TestCase;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 16-3-13
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class Test1 extends TestCase{

    Map<String, String> map = null;

    Dier aap;
    Dier paard;
    Dier ezel;
    Dier koe;
    Dier kraai;
    Dier mus;
    Dier uil;
    Dier aal;
    Dier baars;
    Dier rog;


    @org.junit.Before
    public void setUp() throws Exception {
        map = new HashMap<String, String>();
        map.put("aap", "zoogdier");
        map.put("paard", "zoogdier");
        map.put("ezel", "zoogdier");
        map.put("koe", "zoogdier");
        map.put("kraai", "vogel");
        map.put("mus", "vogel");
        map.put("uil", "vogel");
        map.put("aal", "vis");
        map.put("baars", "vis");
        map.put("rog", "vis");

        aap = new Dier("aap", "zoogdier");
        paard = new Dier("paard", "zoogdier");
        ezel = new Dier("ezel", "zoogdier");
        koe = new Dier("koe", "zoogdier");
        kraai = new Dier("kraai", "vogel");
        mus = new Dier("mus", "vogel");
        uil = new Dier("uil", "vogel");
        aal = new Dier("aal", "vis");
        baars = new Dier("baars", "vis");
        rog = new Dier("rog", "vis");

    }

    public void testMap() {
        System.out.println(map.toString());
        System.out.println("Map (for):");
        for (String key: map.keySet()) {
            System.out.printf("\t%s -> %s\n", key, map.get(key));
        }
        System.out.println("Map (iterator):");
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.printf("\t%s -> %s\n", key, map.get(key));
        }

        Set<String> keys = map.keySet();
        Collection<String> values = new ArrayList<String>();
        values = map.values();
        System.out.println("keys:");
        for (String key: keys) {
            System.out.printf("\t%s\n", key);
        }

        System.out.println("values:");
        for (String value: values) {
            System.out.printf("\t%s\n", value);
        }

        Collection<String> uniquevalues = new HashSet<String>(values);
        System.out.println("uniquevalues:");
        for (String value: uniquevalues) {
            System.out.printf("\t%s\n", value);
        }

        TreeMap<String, String> sorted = new TreeMap<String, String>(map);
        System.out.println("sortedmap:");
        for (String key: sorted.keySet()) {
            System.out.printf("\t%s -> %s\n", key, sorted.get(key));
        }

        List<Dier> dieren = Dier.getInstances();
        for (Dier dier: dieren) {
            System.out.printf("naam: %s, soort: %s\n", dier.getNaam(), dier.getSoort());
        }
        aap.destroy();
        Dier aap = null;
        koe.destroy();
        Dier koe = null;

        dieren = Dier.getInstances();
        for (Dier dier: dieren) {
            System.out.printf("naam: %s, soort: %s\n", dier.getNaam(), dier.getSoort());
        }




    }



}

/**
 * Testclass met dieren
 */
class Dier {

    private static List dieren = new ArrayList();   // houdt de objecten vast

    private String soort;
    private String naam;

    public Dier(String naam, String soort) {
        this.soort = soort;
        this.naam = naam;
        dieren.add(this);
    }

    public String getSoort() {
        return soort;
    }

    public String getNaam() {
        return naam;
    }

    public void print() {
        System.out.printf("soort: %s, naam: %s\n", soort, naam);
    }

    public static List getInstances() {
        return dieren;
    }

    // Anders wordt het geheugen niet vrijgegeven
    public void destroy() {
        dieren.remove(this);
    }

}