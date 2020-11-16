package practice;

public class print {
	public static void main(String[] args) {
		int s[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
		int d[] = { 15, 25, 35, 45, 55, 65, 75, 85, 95, 105 };

		int source_arr[], sourcePos, dest_arr[], destPos, len;
		source_arr = s;
		sourcePos = 3;
		dest_arr = d;
		destPos = 5;
		len = 4;

		// Print elements of source
		System.out.print("source_array : ");
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println("");

		System.out.println("sourcePos : " + sourcePos);

		// Print elements of source
		System.out.print("dest_array : ");
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i] + " ");
		}
		System.out.println("");

		System.out.println("destPos : " + destPos);

		System.out.println("len : " + len);

		// Use of arraycopy() method
		System.arraycopy(source_arr, sourcePos, dest_arr,
				destPos, len);

		// Print elements of destination after
		System.out.print("final dest_array : ");
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i] + " ");
		}
	}

}
