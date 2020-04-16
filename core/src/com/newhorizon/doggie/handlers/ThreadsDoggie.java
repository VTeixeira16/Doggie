package com.newhorizon.doggie.handlers;

public class ThreadsDoggie {

	static int i = 0;
	public void create() {
		new Thread(t1).start();
		new Thread(t2).start();
	}
	
	private static void printaThread(String name)
	{
		i++;
		System.out.print("Contador atual " + i + ", processo: " + name + "\n");
		
	}
	
	public static Runnable t1 = new Runnable()
	{
		public void run()
		{
//			try
			{
				for(int i=0; i<5;i++)
				{
					printaThread("t1");
				}
			} // catch (Exception e) {}
		}
	};
	
	public static Runnable t2 = new Runnable()
	{
		public void run()
		{
			try
			{
				for(int i=0; i<5;i++)
				{
					printaThread("t2");
				}
			} catch (Exception e) {}
		}
		
	};
	
}
