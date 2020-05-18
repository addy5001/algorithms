package questions;

import java.util.*;

public class UniqueElements<T> {

    public Collection<T> findUniqueElements(Collection<T> collection) {
        Set<T> set = new HashSet<>();
        set.addAll(collection);

        return set;
    }

    public Collection<T> findUniqueElements(Collection<T> collection, Comparator<T> comparator) {
        List<T> collectionAsList = new ArrayList<>(collection);
        Collections.sort(collectionAsList, comparator);
        Collection<T> result = new ArrayList<>();

        for(int i = 0; i<collectionAsList.size(); i++) {
            if(i+1 < collectionAsList.size() && collectionAsList.get(i) == collectionAsList.get(i+1)) {
                continue;
            }

            result.add(collectionAsList.get(i));
        }

        return result;
    }
}
