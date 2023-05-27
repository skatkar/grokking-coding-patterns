package org.educative.twopointers;

public class ReverseWords {
    public static String reverseWords(String sentence) {

        StringBuilder sb = trimSpaces(sentence);
        reverseString(sb, 0, sb.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }

    private static void reverseEachWord(StringBuilder sb) {
        int left = 0, right = 0;

        while(right < sb.length()) {

            while(right < sb.length() && sb.charAt(right) != ' ')
                right++;
            reverseString(sb, left, right - 1);
            left = right++ + 1;
        }
    }

    private static void reverseString(StringBuilder sb, int left, int right) {
        while(left <= right) {
            char temp = sb.charAt(left);

            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);

            left++;
            right--;
        }
    }

    private static StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (left < right && s.charAt(left) == ' ') left++;
        while (left < right && s.charAt(right) == ' ') right--;

        while (left <= right) {
            char temp = s.charAt(left);

            // Check whether the current char is space or not
            // if it is a space, append it to the sb
            // else check whether the last character in the sb is not space. If not, then only append it, else ignore.
            if (temp != ' ') {
                sb.append(temp);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(' ');
            }
            left++;
        }
        return sb;
    }
}
