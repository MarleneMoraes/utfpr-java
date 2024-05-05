class UseThreads { 
	public static void main(String args[]) {
		System.out.println("Main thread starting.");
		MyThread mt = new MyThread("Child #1");
		Thread newThrd = new Thread(mt);
		newThrd.start();

		for(int i=0; i<50; i++) {
			System.out.print("."); 
			try {
				Thread.sleep(50);
			} catch(InterruptedException exc) {
				System.out.println("Main thread interrupted.");
			}
		}
		System.out.println("Main thread ending.");
	}
}