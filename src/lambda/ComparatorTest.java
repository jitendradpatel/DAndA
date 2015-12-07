package lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jpatel on 10/5/15.
 */
public class ComparatorTest {

    public static void main(String[] args) throws Exception {
        List<String> coll = new ArrayList<>(Arrays.asList("A", "D", "B", "C", "A"));

        coll.parallelStream().unordered().forEach(s -> System.out.println(s));
        System.out.println();
        coll.parallelStream().unordered().forEach(s -> System.out.println(s));
        System.out.println();
        coll.parallelStream().unordered().forEach(s -> System.out.println(s));
        System.out.println();
        coll.stream().filter(s -> s.equals("A")).forEach(s -> System.out.println(s));
        Map<String, List<String>> collect = coll.stream().collect(Collectors.groupingBy(s -> s));
        Map<String, String> m1 = new HashMap<String, String>();

        collect.forEach((k, v) -> System.out.println(k + ":" + v));
        Predicate<String> p1 = (String s) -> s.equals("E");
        coll.stream().distinct().forEach(s3 -> System.out.println(s3));
        System.out.println();
        Collections.sort(coll, (String s1, String s2) -> s1.compareTo(s2));
        coll.forEach(s -> System.out.println(s));
        coll.forEach(s -> {
            if (p1.test(s)) {
                System.out.println(s);
            }
        });
    }

    public static void e1(List<Person> roster) {
        for (Person p : roster) {
            if (p.getGender() == Person.Sex.MALE) {
                System.out.println(p.getName());
            }
        }
        roster.stream().mapToInt(p -> p.personNo).sum();
        roster.stream().filter(p -> p.getGender() == Person.Sex.MALE).forEach(p -> System.out.println(p));
        roster.stream().filter(p -> p.addresses.stream().anyMatch(a -> a > 0)).sorted(Comparator.comparing(p -> p.name)).collect(Collectors.toList());
        //roster.stream().flatMap(p -> p.addresses.stream()).filter(a -> a.equals("5959")).
    }
    public static void printStrings(List<String> l, Predicate<String> p) {

    }
}

class Person {
    String name;
    Sex gender;
    List<Integer> addresses;
    int personNo;

    enum Sex {
        MALE, FEMALE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }
}