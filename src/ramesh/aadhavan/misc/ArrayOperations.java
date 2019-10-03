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

    public static void rotateArray(int[] arr) {
        int halfN = arr.length/2;

        for(int i=0, j=arr.length-1; i < halfN; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        rotateArray(arr);

    }
}
