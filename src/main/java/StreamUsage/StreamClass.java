package StreamUsage;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class StreamClass {

    public void orderPrint(List<String> list) {
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }

    public List<Long> createList (Stack<Long> intStack, int init) {
        return intStack.stream()
                .filter(element -> element <= init*10000000000L+10000000000L)
                .filter(element -> element >= init*10000000000L)
                .collect(Collectors.toList());
    }

    public static void main(final String... args) {
        StreamClass streamnator = new StreamClass();

        //NEW LIST WITH ELEMENTS
        List<String> myList = Arrays.asList("a1", "a2", "c2", "c1", "b1");
        List<String> nombres = Arrays.asList("Alberto", "Juan", "Maria", "Paco", "Vegeta777");
        streamnator.orderPrint(myList);
        streamnator.orderPrint(nombres);


        //NEW STACK WITH PESELS
        System.out.print("I have a stack of PESEL numbers:  ");
        Stack<Long> PESELstack = new Stack();
        List<Long> inputList = Arrays.asList(97061000005L, 12300000000L, 52090557562L, 76090453562L, 56090233562L);
        PESELstack.addAll(inputList);
        System.out.println(Arrays.toString(PESELstack.toArray()));

        System.out.println("These are the ones from the stack that start with 5:");
        for (Long n : streamnator.createList(PESELstack, 5)){
            System.out.print(n+" ");
        }

    }

}
