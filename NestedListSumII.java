import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class NestedInteger {
    public List<NestedInteger> list;
    public Integer num;
    public boolean isinteger;

    public NestedInteger() {
        list = null;
        isinteger = true;
    }

    public NestedInteger(int value) {
        num = value;
        isinteger = true;
    }

    // return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger() {
        return isinteger;
    }

    // return the single integer that this NestedInteger holds, if it holds a single
    // integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        if (isinteger)
            return num;
        else
            return null;
    }

    public void setIntger(int value) {
        if (isinteger)
            num = value;
    }

    public void add(NestedInteger ni) {
        isinteger = false;
        if (list == null)
            list = new ArrayList<NestedInteger>();
        list.add(ni);
    }

    // return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        if (isinteger)
            return null;
        else
            return this.list;
    }
}

public class NestedListSumII {
    static int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        Stack<Integer> levelSum = new Stack<>();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }
        while (!queue.isEmpty()) {
            int lsum = 0;
            Queue<NestedInteger> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                NestedInteger number = queue.poll();
                if (number.isinteger) {
                    lsum += number.getInteger();
                } else {
                    for (NestedInteger ni : number.getList()) {
                        queue2.offer(ni);
                    }
                }
            }
            levelSum.push(lsum);
            queue = queue2;
        }
        int total = 0, level = 1;
        while (!levelSum.isEmpty()) {
            total += level * levelSum.pop();
            level++;
        }
        return total;
    }

    static List<NestedInteger> loadData(String text) {
        Stack<NestedInteger> stk = new Stack<NestedInteger>();
        Stack<Integer> nums = new Stack<Integer>();
        nums.push(0);
        int index = 0;
        while (index < text.length()) {
            if (text.charAt(index) == '[') {
                nums.push(0);
            } else if (text.charAt(index) == ']') {
                NestedInteger ni = new NestedInteger();
                Integer n = nums.pop();
                Stack<NestedInteger> tmp = new Stack<NestedInteger>();
                while (n-- > 0) {
                    tmp.push(stk.pop());
                }
                while (!tmp.empty()) {
                    ni.add(tmp.pop());
                }
                stk.push(ni);
                int number = nums.pop();
                nums.push(++number);
            } else if (text.charAt(index) == '-' || (text.charAt(index) >= '0' && text.charAt(index) <= '9')) {
                int beginIndex = index++;
                while (text.charAt(index) >= '0' && text.charAt(index) <= '9') {
                    index++;
                }
                NestedInteger tmp = new NestedInteger(Integer.parseInt(text.substring(beginIndex, index)));
                stk.push(tmp);
                int number = nums.pop();
                nums.push(++number);
                index--;
            }
            index++;
        }
        return stk.pop().getList();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine().trim();
        List<NestedInteger> nestedList = loadData(text);

        int res = depthSumInverse(nestedList);
        System.out.println(String.valueOf(res));
    }
}
