import external.ValueClass;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
  public static final int COLLECTION_SIZE = 50_000;

  public static void main(String[] args) {
    ArrayList<ValueClass> arrayList = new ArrayList<>();
    LinkedList<ValueClass> linkedList = new LinkedList<>();
    HashSet<ValueClass> hashSet = new HashSet<>();

    populate(arrayList);
    long arrayListSize = GraphLayout.parseInstance(arrayList).totalSize();

    populate(linkedList);
    long linkedListSize = GraphLayout.parseInstance(linkedList).totalSize();

    populate(hashSet);
    long hashSetSize = GraphLayout.parseInstance(hashSet).totalSize();

    System.out.println("Memory Footprint (Collection size: " + COLLECTION_SIZE + "):");
    System.out.println("ArrayList: " + arrayListSize);
    System.out.println("LinkedList: " + linkedListSize);
    System.out.println("Overhead (compared to ArrayList): " + (((double)linkedListSize)/arrayListSize));
    System.out.println("HashSet: " + hashSetSize);
    System.out.println("Overhead (compared to ArrayList): " + (((double)hashSetSize)/arrayListSize));
  }

  private static void populate(Collection<ValueClass> collection) {
    for (int i = 0; i < COLLECTION_SIZE; i++) {
      collection.add(new ValueClass(i, String.valueOf(i)));
    }
  }
}