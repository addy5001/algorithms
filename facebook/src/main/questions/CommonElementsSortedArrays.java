package questions;

public class CommonElementsSortedArrays {
    public static void printCommonElements(int[][] arrays) {
        if (arrays.length < 2) {
            System.out.println("Too few arrays");
            return;
        }

        // to store the current index for 0th array
        int baseIndex = 0;

        // to store the current index for each of the remaining n-1 arrays
        int[] indices = new int[arrays.length - 1];

        // to track in how many arrays a specific element is found
        int totalMatchFound;

        // variable used to terminate the search early
        boolean smallestArrayTraversalComplete = false;
        /*
         *  pick elements one by one from the first array
         *  and check we find a match for them in all other arrays
         */
        while ((baseIndex < arrays[0].length) && (!smallestArrayTraversalComplete)) {
            totalMatchFound = 0;
            for (int i = 1; i < arrays.length; i++) {
                // get the index for this array where we last stopped
                int currIndex = indices[i - 1];

                // skip all the elements in this array which are less than the element at baseIndex in 0th array
                while ((currIndex < arrays[i].length) && (arrays[i][currIndex] < arrays[0][baseIndex])) {
                    currIndex += 1;
                }

                // check if common element(which is equal to element at baseIndex in 0th array) has been found in this array
                if (currIndex < arrays[i].length) {
                    if ((arrays[i][currIndex] == arrays[0][baseIndex])) {
                        totalMatchFound += 1;
                    }
                }
                // indicates that we have looked at all the elements of 'i'th array
                else {
                    smallestArrayTraversalComplete = true;
                }

                // store this incremented index for this array-
                // which would help to resume from this point for next matching round
                indices[i - 1] = currIndex;
            }

            // check if element arrays[0][baseIndex] is found in all other arrays
            if (totalMatchFound == arrays.length - 1) {
                System.out.println(arrays[0][baseIndex]);
            }
            baseIndex += 1;
        }
    }
}
