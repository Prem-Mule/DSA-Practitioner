import java.util.Stack;

public class S3IsValidParenthesis {
    public static boolean isValidParenthesis1(String s) {
        Stack<Character> a = new Stack<>();
        for (char c : s.toCharArray()) {

            if (c == ']' || c == ')' || c == '}') {
                if (a.size() == 0) {
                    return false;
                } else if (c == ']' && a.peek() == '[') {
                    a.pop();
                } else if (c == ')' && a.peek() == '(') {
                    a.pop();
                } else if (c == '}' && a.peek() == '{') {
                    a.pop();
                } else {
                    return false;
                }
            } else {
                a.add(c);
            }
        }
        if (!a.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isValidParenthesis2(String s) {
        char[] cArray = new char[s.length()];
        int index = -1;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                cArray[++index] = c;
            } else {
                if (index < 0) {
                    return false;
                }
                if (c == ']' && cArray[index] != '[') {
                    return false;
                } else if (c == '}' && cArray[index] != '{') {
                    return false;
                } else if (c == ')' && cArray[index] != '(') {
                    return false;
                } else {

                    index--;
                }
            }
        }
        if (index <= 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String[] testCases = {
                "()[]{}", "(})", "((()))", "{[()]}", "([)]", "", "(((((", "}}}}"
        };

        System.out.println("Testing isValidParenthesis1:");
        for (String str : testCases) {
            System.out.println(str + " -> " + isValidParenthesis1(str));
        }

        System.out.println("\nTesting isValidParenthesis2:");
        for (String str : testCases) {
            System.out.println(str + " -> " + isValidParenthesis2(str));
        }

    }
}
