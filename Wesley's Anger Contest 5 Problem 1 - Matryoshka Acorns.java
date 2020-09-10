import java.io.*;
import java.util.*;

public class Other_Coding_Contests {

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int n = in.nextInt();
		ArrayList<pair> arr = new ArrayList<pair>();
		while (n-- != 0) {
			arr.add(new pair(in.nextInt()));
		}
		Collections.sort(arr, new Comparator<pair>() {
			public int compare(pair o1, pair o2) {
				return o1.s - o2.s;
			}
		});
		for (int i = arr.size() - 1; i > -1; i--) {
			for (int j = i - 1; j > -1; j--) {
				if (arr.get(j).s < arr.get(i).s && arr.get(i).prev > arr.get(j).s) {
					arr.get(i).prev = arr.get(j).s;
					arr.remove(j);
					i = arr.size();
					break;
				}
			}
		}
		int sum = 0;
		for (pair p : arr) {
			sum += p.s;
		}
		System.out.println(sum);
	}

	static class pair {
		int s, prev = Integer.MAX_VALUE;

		pair(int s) {
			this.s = s;
		}
	}

	static class Reader {
		final private int BUFFER_SIZE = 100000;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[BUFFER_SIZE];
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}