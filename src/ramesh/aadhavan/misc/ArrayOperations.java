package ramesh.aadhavan.misc;

import java.util.*;

public class ArrayOperations {

    public static class CharOperations {
        public static void reverseChars(char[] arr) {
            reverseChars(arr, 0, arr.length-1);
        }

        private static void reverseChars(char[] arr, int start, int end) {
            if(start >= end)
                return;

            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            reverseChars(arr, start+1, end-1);
        }

        public static boolean isValidParentheses(String s) {
            char[] arr = s.toCharArray();
            Stack<Character> charStack = new Stack<>();

            for(Character c : arr) {
                if(c == '{' || c == '(' || c == '[')
                    charStack.push(c);
                else {
                    Character peek = (charStack.isEmpty()) ? null : charStack.peek();
                    if(peek != null) {
                        switch (c) {
                            case '}': {
                                if ('{' == peek)
                                    charStack.pop();
                                else
                                    charStack.push(c);
                                break;
                            }
                            case ')': {
                                if ('(' == peek)
                                    charStack.pop();
                                else
                                    charStack.push(c);
                                break;
                            }
                            case ']': {
                                if ('[' == peek)
                                    charStack.pop();
                                else
                                    charStack.push(c);
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    else
                        return false;
                }
            }
            return charStack.isEmpty();
        }

        public static boolean insertStringForSpaces(char[] chars, String str) {
            int strLength = str.length();
            int charArrayLength = chars.length;
            int actualCharLength = charArrayLength;
            for(int i=charArrayLength-1; i>=0; i--) {
                if(chars[i] == 32)
                    actualCharLength--;
                else
                    break;
            }

            int numberOfSpaces=0;
            for(int i=0; i<actualCharLength; i++) {
                if(chars[i] == 32)
                    numberOfSpaces++;
            }

            int additionalChars = numberOfSpaces * (strLength - 1);
            if(charArrayLength < actualCharLength + additionalChars)
                return false;

            int newCharLength = actualCharLength + additionalChars - numberOfSpaces;
            char[] strArray = str.toCharArray();

            for(int i=0; i<newCharLength;) {
                if(chars[i] == 32) {
                    System.arraycopy(chars, i+1, chars, i+strLength, actualCharLength-i+1);
                    System.arraycopy(strArray, 0, chars, i, strLength);
                    i = i+strLength;
                }
                else
                    i++;
            }
            return true;
        }
    }

    public static int count(int[] array) {
        if(array.length == 1)
            return 1;
        return 1 + count(Arrays.copyOf(array, array.length - 1));
    }

    public static int sum(int[] array) {
        if(array.length == 1)
            return array[0];
        return array[0] + sum(Arrays.copyOfRange(array, 1, array.length));
    }

    public static int binarySearch(int[] array, int element, int start, int end) {
        if(start <= end) {
            int mid = (end + start)/2;

            if(element == array[mid])
                return mid;

            else if (array[mid] > element)
                return binarySearch(array, element, start, mid - 1);

            return binarySearch(array, element, mid + 1, end);
        }

        return -1;
    }

    public static int[] twoSum(int[] arr, int target) {
        int[] sumIndices = new int[2];
        for(int i=0; i<arr.length; i++) {
            for(int j=i+1; j< arr.length; j++) {
                if(arr[i] + arr[j] == target) {
                    sumIndices[0] = i;
                    sumIndices[1] = j;
                    return sumIndices;
                }

            }
        }
        return null;
    }

    public static int[] twoSumHashMap(int[] arr, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            int diff = target - arr[i];
            if(sumMap.containsKey(diff)) {
                int[] sumIndices = new int[2];
                sumIndices[0] = i;
                sumIndices[1] = sumMap.get(diff);
                return sumIndices;
            }
            sumMap.put(arr[i], i);
        }
        return null;
    }

    public static int searchInsert(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == target)
                return i;

            if(target < nums[i])
                return i;
        }
        return nums.length;
    }

    public static int binarySearchInsert(int[] nums, int target) {
        return binarySearchInsert(nums, 0, nums.length-1, target);
    }

    private static int binarySearchInsert(int[] nums, int start, int end, int target) {
        if(start <= end) {
            int mid = (start + end)/2;
            if(target == nums[mid])
                return mid;
            else if(target < nums[mid])
                return binarySearchInsert(nums, start, mid-1, target);
            else
                return binarySearchInsert(nums, mid+1, end, target);
        }
        return start;
    }

    public static int removeElement(int[] nums, int val) {
        int last = nums.length - 1;
        int i = 0;
        while(i <= last) {
            if(val == nums[i]) {
                if(i<last) {
                    int temp = nums[i];
                    nums[i] = nums[last];
                    nums[last] = temp;
                }
                last--;

            }
            else {
                i++;
            }
        }
        return last+1;
    }

    public static void printNextGreaterElements(int[] nums) {
        _printNextGreaterElementsUsingStack(nums);
    }

    private static void _printNextGreaterElements(int[] nums, int begin) {
        if(begin >= nums.length)
            return;

        int max = nums[begin];
        int i = begin;
        for(;i<nums.length; i++)
            if(max < nums[i]) {
                max = nums[i];
                break;
            }

        if(i==nums.length && max == nums[begin])
            System.out.println("Next greater element of "+max+" is null");
        else
            System.out.println("Next greater element of "+nums[begin]+" is "+max);

        _printNextGreaterElements(nums, begin+1);
    }

    private static void _printNextGreaterElementsUsingStack(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();

        for(int num : nums) {
            if(stack.isEmpty())
                stack.push(num);
            else {
                while(!stack.isEmpty()) {
                    if(stack.peek() < num)
                        System.out.println("Next greater element of "+stack.pop()+" is "+num);
                    else
                        break;
                }
                stack.push(num);
            }
        }

        while(!stack.isEmpty())
            System.out.println("Next greater element of "+stack.pop()+" is null");
    }

    public static int findLastIndexOfElementSorted(int[] arr, int begin, int end, int element) {
        if(begin > end)
            return -1;

        int mid = (begin + end)/2;

        if(element == arr[mid]) {
            if((mid + 1) > end || arr[mid+1] > element)
                return mid;
            else
                return findLastIndexOfElementSorted(arr, mid+1, end, element);
        }
        else if(element < arr[mid]) {
            return findLastIndexOfElementSorted(arr, begin, mid - 1, element);
        }
        else {
            return findLastIndexOfElementSorted(arr, mid + 1, end, element);
        }
    }

    public static int findOddlyOccuringElementInEvenOccuringElementsArray(int[] arr) {
        return Arrays.stream(arr).reduce(0, (x, y) -> x^y);
    }

    public static int[] rearrangeArrayIntoAlternativePositiveNegatives(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        Queue<Integer> negative = new LinkedList<>();
        Queue<Integer> positive = new LinkedList<>();
        boolean isLastPositive = false;

        int j=0;
        for(int i=0; i<len; i++) {
            if(arr[i] > 0) {
                if(isLastPositive) {
                    positive.offer(arr[i]);
                }
                else {
                    if(positive.peek() != null) {
                        result[j] = positive.poll();
                        positive.offer(arr[i]);
                        j++;
                    }
                    else {
                        result[j] = arr[i];
                        j++;
                    }
                    isLastPositive = true;
                }
            }
            else {
                if(isLastPositive) {
                    if(negative.peek() != null) {
                        result[j] = negative.poll();
                        negative.offer(arr[i]);
                        j++;
                    }
                    else {
                        result[j] = arr[i];
                        j++;
                    }
                    isLastPositive = false;
                }
                else {
                    negative.offer(arr[i]);
                }
            }
        }

        while (positive.peek() != null && negative.peek() != null) {
            if(isLastPositive) {
                result[j++] = negative.poll();
                result[j++] = positive.poll();
            }
            else {
                result[j++] = positive.poll();
                result[j++] = negative.poll();
            }
        }

        while(positive.peek() != null) {
            result[j++] = positive.poll();
        }

        while (negative.peek() != null) {
            result[j++] = negative.poll();
        }

        return result;
    }

    public static void permute(Integer arr[], int begin, int end, List<List<Integer>> permutedList) {
        if(begin == end)
            permutedList.add(new ArrayList<>(Arrays.asList(arr)));

        for(int i=begin; i<end; i++) {
            _swapInArray(arr, i, begin);
            permute(arr, begin+1, end, permutedList);
            _swapInArray(arr, i, begin);
        }
    }

    private static void _swapInArray(Integer[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void reverseArray(int[] arr) {
        int halfN = arr.length/2;

        for(int i=0, j=arr.length-1; i < halfN; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void threeSum(int[] arr) {

    }

    public static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        int counter = k % n;
        while(counter > 0) {
            int first = arr[0];
            for (int i=1; i<n; i++) {
                arr[i-1] = arr[i];
            }
            arr[n-1] = first;
            counter--;
        }
    }

    public static void rotateArrayRecurse(int[] arr, int k) {
        int n = arr.length;
        int counter = k % n;

        if(counter == 0)
            return;

        reverseRange(arr, 0, counter-1);
        reverseRange(arr, counter, arr.length-1);
        reverseRange(arr, 0, arr.length-1);
    }

    private static void reverseRange(int[] arr, int start, int endInclusive) {
        if(start >= endInclusive)
            return;

        int mid = (start + endInclusive)/2;

        for(int i=start, j=endInclusive; i <= mid && j >= mid; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static List<Integer> leadersInArray(int[] arr) {
        if(arr == null || arr.length == 0)
            return Collections.emptyList();

        Deque<Integer> stack = new LinkedList<>();
        for(int i : arr)
            stack.push(i);

        int leader = Integer.MIN_VALUE;
        List<Integer> leaders = new ArrayList<>();
        while(stack.peek() != null) {
            int val = stack.pop();
            if(val > leader) {
                leaders.add(val);
                leader = val;
            }
        }
        return leaders;
    }

    public static boolean flowerPots(int[] flowerpots, int n) {
        int canPlace = 0;

        for(int i=0; i<flowerpots.length; i++) {
            if ((i+1)==1) {
                if(flowerpots[i] == 0 && flowerpots[i+1] == 0) {
                    canPlace++;
                }
            }
            else if(i==flowerpots.length-1) {
                if(flowerpots[i-1] == 0 && flowerpots[i] == 0) {
                    canPlace++;
                }
            }
            else {
                if(flowerpots[i-1] == 0 && flowerpots[i] == 0 && flowerpots[i+1] == 0) {
                    flowerpots[i] = 1;
                    canPlace++;
                }
            }
        }

        return canPlace >= n;
    }

    public static int maxSubArraySum(int[] arr) {
        return _maxSubArraySum(arr, 0, arr.length-1, 0);
    }

    // TODO Optimize
    private static int _maxSubArraySum(int[] arr, int begin, int end, int maxSum) {
        if(begin == end)
            return maxSum;

        int nextSum;
        int sum;

        for(int i=begin; i<=end; i++) {
            sum = _getSubArraySum(arr, begin, i);
            if(begin == 0 && i == end)
                return maxSum;

            if(sum > maxSum)
                maxSum = sum;
            nextSum = _maxSubArraySum(arr, i+1, end, maxSum);
            maxSum = (nextSum > sum) ? nextSum : sum;
        }

        return maxSum;
    }

    private static int _getSubArraySum(int[] arr, int begin, int end) {
        if(begin == end)
            return arr[begin];

        int sum = 0;
        for(int i=begin; i<=end; i++) {
            sum+=arr[i];
        }
        return sum;
    }

    public static int maxSubArraySumKadaneAlgorithm(int[] arr) {
        //TODO implement
        return 0;
    }

    public static int[] frequenciesOfNumbers(int[] arr, int range) {
        //TODO implement
        return null;
    }

    /**
     * Element greater than its neighbours
     * @return
     */
    public static int findPeakElement(int[] arr) {
        return _findPeakElement(arr, 0, arr.length - 1);
    }

    public static int _findPeakElement(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return _findPeakElement(nums, l, mid);
        return _findPeakElement(nums, mid + 1, r);
    }

    private static boolean _isPeakElement(int a, int b, int c) {
        return (b > a) && (b > c);
    }

    public static int findPivotedElementInSortedArray(int[] arr) {
        return _findPivot(arr, 0, arr.length-1);
    }

    private static int _findPivot(int[] arr, int begin, int end) {
        if(begin > end)
            return -1;

        int mid = (begin + end)/2;

        if((mid - 1) >= 0 && arr[mid-1] > arr[mid])
            return mid;
        else if(arr[mid] > arr[end])
            return _findPivot(arr, mid+1, end);
        else
            return _findPivot(arr, begin, mid-1);
    }

    // TODO Optimize
    public static int findIndexOf0InContinuousOnes(int[] arr) {
        int maxLength = 0;
        int zeroIndex = -1;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 0) {
                int length = _findLengthOf1s(arr, i) + 1;
                if (length > maxLength) {
                    maxLength = length;
                    zeroIndex = i;
                }
            }
        }

        return zeroIndex;
    }

    private static int _findLengthOf1s(int[] arr, int idx) {
        int leftLength = 0;
        for(int i=idx-1; i>0; i--) {
            if(arr[i] == 1)
                leftLength++;
            else
                break;
        }

        int rightLength = 0;
        for(int i=idx+1; i<arr.length; i++) {
            if(arr[i] == 1)
                rightLength++;
            else
                break;
        }

        return leftLength + rightLength;
    }

    public static int findIndexOf0InContinuousOnesOptimized(int[] arr) {
        int leftLength = 0;
        int rightLength = 0;
        int maxLength = 0;
        int prevIdx = -1, currIdx, maxIdx = -1;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 0) {
                currIdx = i;
                int length = leftLength + 1 + rightLength;
                if(length > maxLength) {
                    maxLength = length;
                    maxIdx = (prevIdx == -1) ? i : prevIdx;
                }
                prevIdx = currIdx;
                leftLength = rightLength;
                rightLength = 0;
            }
            else {
                rightLength = rightLength+1;
            }
        }

        if(arr[arr.length-1] == 0) {
            if(leftLength+1 > maxLength) {
                maxIdx = arr.length - 1;
            }
        }

        return maxIdx;
    }

    public static void pancakeSort(int[] arr) {
        boolean isSorted = false;

        while(!isSorted) {
            int beginIdx = -1;
            for (int i = 1; i < arr.length; i++) {
                if(arr[i-1] > arr[i]) {
                    beginIdx = i-1;
                    isSorted = false;
                    break;
                }
            }

            if(beginIdx == -1) {
                return;
            }

            int curr = beginIdx+1;
            int endIdx = arr.length - 1;
            while(curr < arr.length) {
                if(arr[beginIdx] < arr[curr]) {
                    endIdx = curr-1;
                    break;
                }
                curr++;
            }

            reverseRange(arr, beginIdx, endIdx);
        }
    }

    public static void duplicateZeroes(int[] arr) {
        for(int i=1; i<arr.length; i++) {
            if(arr[i-1] == 0) {
                rightShiftByOne(arr, i);
                arr[i] = 0;
                i++;
            }
        }
    }

    private static void rightShiftByOne(int [] arr, int begin) {
        if(begin == arr.length - 1)
            return;

        for(int i=arr.length-1; i>begin; i--) {
            arr[i] = arr[i-1];
        }
    }

    public static int[] plusOne(int[] digits) {
        int carry = 1;

        for(int i=digits.length-1; i>=0; i--) {
            if(carry == 0)
                break;

            int sum = digits[i] + carry;
            int digit = sum%10;
            digits[i] = digit;

            carry = sum/10;
        }

        if(carry>0) {
            int[] result = new int[digits.length+1];
            result[0] = carry;
            for(int i=1; i<result.length; i++)
                result[i] = digits[i-1];
            return result;
        }

        return digits;
    }

    private int _makeNumFromArray(int[] digits) {
        int result = 0;

        for(int i=0; i<digits.length; i++) {
            result = result*10 + digits[i];
        }

        return result;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums2Idx = 0;

        while(nums2Idx < n) {
            int item = nums2[nums2Idx];

            int i=0;
            for(; i<m; i++) {
                if(nums1[i] > item)
                    break;
            }

            if(i==m) {
                nums1[m] = item;
            }
            else {
                for(int j=m; j>i; j--) {
                    nums1[j] = nums1[j-1];
                }
                nums1[i] = item;
            }

            nums2Idx++;
            m++;
        }
    }

    public static void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1)
            return;

        int i = 0;
        for(int counter=0; counter < nums.length; counter++) {
            if(nums[i] == 0) {
                int j = i;
                while(j+1 < nums.length) {
                    nums[j] = nums[j+1];
                }
                nums[nums.length-1] = 0;
            }
            else {
                i++;
            }
        }
    }

    public static int sumDivideAndConquer(int[] nums) {
        return _sumDivideAndConquer(nums, 0, nums.length-1);
    }

    private static int _sumDivideAndConquer(int[] nums, int begin, int end) {
        if(begin == end)
            return nums[begin];

        int mid = (begin+end)/2;

        int leftSum = _sumDivideAndConquer(nums, begin, mid);
        int rightSum = _sumDivideAndConquer(nums, mid+1, end);

        return leftSum+rightSum;
    }

    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        for(int i=0; i<nums.length; i++)
            nums[i] = nums[i]-1;

        for(int i=0; i<nums.length; i++) {
            int idx = nums[i] % n;
            nums[idx] = nums[idx] + n;
        }

        int[] results = new int[2];
        for(int i=0; i<nums.length; i++) {
            nums[i] = nums[i]/n;
            if(nums[i] == 2) {
                results[0] = i+1;
            }
            else if(nums[i] == 0) {
                results[1] = i+1;
            }
        }

        return results;
    }

    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length < 1)
            return new int[]{-1, -1};

        int[] results = new int[2];
        results[0] = _firstElement(nums, target);
        results[1] = _lastElement(nums, target);

        return results;
    }

    private int _firstElement(int[] nums, int target) {
        int begin = 0, end = nums.length - 1;

        while(begin <= end) {
            int mid = begin + (end - begin)/2;

            if(nums[mid] == target) {
                if(mid == 0 || (nums[mid-1] < nums[mid]))
                    return mid;
                else
                    end = mid - 1;
            }
            else if(nums[mid] > target)
                end = mid - 1;
            else
                begin = mid + 1;
        }

        return -1;
    }

    private int _lastElement(int[] nums, int target) {
        int begin = 0, end = nums.length -1;

        while(begin <= end) {
            int mid = begin + (end - begin)/2;

            if(nums[mid] == target) {
                if((mid == nums.length - 1) || (nums[mid] < nums[mid+1]))
                    return mid;
                else
                    begin = mid + 1;
            }
            else if(nums[mid] < target)
                begin = mid + 1;
            else
                end = mid - 1;
        }

        return -1;
    }

    public int removeDuplicates(int[] nums) {
        if(nums == null)
            return 0;

        if(nums.length == 1)
            return 1;

        int i = 0;
        int j = 1;

        while(j < nums.length) {
            if(nums[i] == nums[j]) {
                j++;
            }
            else {
                if(j != i+1) {
                    nums[i+1] = nums[j];
                }
                i++;
                j++;
            }
        }

        return i+1;
    }

    public void rotate(int[] nums, int k) {
        if(nums.length == 1)
            return;

        int n = nums.length;
        int numRotations = k % n;

        if(k == 0)
            return;

        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }

    private void reverse(int[] nums, int start, int end) {
        if(start >= end)
            return;

        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;

        reverse(nums, ++start, --end);
    }

    public void shuffle(int[] nums) {
        Random random = new Random();

        for(int i=0; i<nums.length; i++) {
            int x = random.nextInt(nums.length - i);
            int idx = x + i;

            int temp = nums[i];
            nums[i] = nums[idx];
            nums[idx] = temp;
        }
    }
}
