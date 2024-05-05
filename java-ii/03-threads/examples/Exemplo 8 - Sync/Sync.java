class Sync { 
	public static void main(String args[]) {

		int a[] = {1, 2, 3, 4, 5};
		MyThread mt1 = new MyThread("Child #1", a); 
		MyThread mt2 = new MyThread("Child #2", a);

		try { 
			mt1.thrd.join(); 
			mt2.thrd.join();
		} catch(InterruptedException exc) {
			System.out.println("Main thread interrupted.");
		}
	}
}