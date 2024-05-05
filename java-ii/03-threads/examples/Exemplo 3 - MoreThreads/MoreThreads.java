class MoreThreads { 
	public static void main(String args[]) {
		System.out.println("Main thread starting.");

		MyThread mt1 = new MyThread("Child #1"); 
		MyThread mt2 = new MyThread("Child #2"); 
		MyThread mt3 = new MyThread("Child #3");

		for(int i=0; i < 50; i++) { 
			System.out.print("."); 
			try {
				Thread.sleep(100);
			} catch(InterruptedException exc) {
				System.out.println("Main thread interrupted.");
			}
		}
		System.out.println("Main thread ending.");
	}
}