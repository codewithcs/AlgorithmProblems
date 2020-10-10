package LeetCodePremium.Amazon;

/*
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 */

public class CompareVersionNumbers {

    public static void main(String[] args) {
        System.out.println("diff is : "+ compareVersion("0.1", "1.1"));
    }

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int[] i1 = new int[v1.length];
        int[] i2 = new int[v2.length];

        for (int i = 0; i < v1.length; i++) {
            i1[i] = Integer.parseInt(v1[i]);
        }

        for (int i = 0; i < v2.length; i++) {
            i2[i] = Integer.parseInt(v2[i]);
        }

        int i = 0, j = 0;

        while (i < i1.length && j < i2.length) {
            if (i1[i] < i2[j]) {
                return -1;
            } else if (i1[i] > i2[j]) {
                return 1;
            }
            i++;
            j++;
        }

        if (i == i1.length && j == i2.length) { // equal length and equal
            return 0;
        } else if (j == i2.length) {
            while (i < i1.length) {
                if (i1[i] != 0) {
                    return 1;
                } else {
                    i++;
                }
            }
        } else {
            while (j < i2.length) {
                if (i2[j] != 0) {
                    return -1;
                } else {
                    j++;
                }
            }

        }

        return 0;
    }

    // Cleaner Code. Add 0s to the shorter string.
    // Time: O( n + m + max(n, m)), n and m are lengths of input strings.
    // O(n+m) space
    public static int compareVersion2(String version1, String version2){
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length, n2 = nums2.length;

        // compare versions
        int i1, i2;
        for (int i = 0; i < Math.max(n1, n2); ++i) {
            i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        // the versions are equal
        return 0;
    }
}