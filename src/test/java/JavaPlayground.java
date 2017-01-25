import java.util.Set;
import java.util.TreeSet;

/**
 * Created by idorovskikh on 1/24/17.
 */
public class JavaPlayground {
    public static void main(String[] args) {
        findName();
    }

    private static void findName(){
        Set<String> names = new TreeSet<String>();

        names.add("Alex");
        names.add("Kyle");

        System.out.println(names);
        System.out.println(names.contains("Alex"));

        System.out.println(names.size());

        for (String n : names) {
            if (n.contains("Kyle")){
                System.out.println("found" + n);
            }
        }
    }
}
