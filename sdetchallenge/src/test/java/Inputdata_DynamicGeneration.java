import java.util.*;
public class Inputdata_DynamicGeneration {
      // Create HashSet to store random values
      public List<ArrayList<Integer>> randomInput() {
            HashSet<Integer> randomSet = new HashSet<>();

            // Creating Random object
            Random random = new Random();

            // Generating random values and add them to the set until size reaches 9
            while (randomSet.size() < 9) {
                int randomNumber = random.nextInt(9); // Generating random number between 0 (inclusive) and 9 (exclusive)
                randomSet.add(randomNumber);
            }

            // Converting the set to a list
            ArrayList<Integer> list = new ArrayList<>(randomSet);

            // Shuffling the list
            Collections.shuffle(list);

            // Divide the list into three parts
            int size = list.size();
            int partSize = size / 3;
            List<ArrayList<Integer>> allList = new ArrayList<>();
            ArrayList<Integer> part1 = new ArrayList<>(list.subList(0, partSize));
            ArrayList<Integer> part2 = new ArrayList<>(list.subList(partSize, 2 * partSize));
            ArrayList<Integer> part3 = new ArrayList<>(list.subList(2 * partSize, size));

            allList.add(part1);
            allList.add(part2);
            allList.add(part3);
            return allList;
    }
}


