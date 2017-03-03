
package com.RTTS.NeoLoad;

import java.util.Scanner;

/**
 * @author      Wei Huang <whuang@1800flowers.com>
 * @version     1.0
 * @since       1.0      
 */

public class WeiDriver 
{ 
	public static void main(String [] args)
	{ 	
		SysInfo si = new SysInfo();
		System.out.println(
				"\"OSA:"	+si.getOSArcitecture()
				+"\" \"OSName:"			+si.getOSName()
				+"\" \"SysLang:"	+si.getSysUserLang()
				+"\" \"SysUsername:"	+si.getSysUserName());

		RandomNumberGenerator rng = new RandomNumberGenerator();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter x: ");
		int x = sc.nextInt();
		
		while( x < 100 )
		{
			System.out.print("Repeat: ");
			x = sc.nextInt(); 
		}
		System.out.print("Enter y: ");
		int y = sc.nextInt();
		while( y <= x )
		{
			System.out.print("Repeat: ");
			y = sc.nextInt(); 
		}
		
		int rand = Integer.parseInt(rng.randomNumber(x, y));
		System.out.println("Random Area Code: " +rand);

		String areCode = Integer.toString(rand);

		System.out.println("\"Give me 10 different numbers with same area code\"");


		/**
		 * Loop Start 
		 * Use {@link #TelephoneGenerator(Sting AreaCode)}
		 */
		int count =1;
		for(int i = 0; i <10; i++)
		{
			TelephoneGenerator tg = new TelephoneGenerator();
			String png = tg.phoneNumberGenerator(areCode);
			String ext = tg.phoneExtension();
			if (count == 10 ) {System.out.println("X: " + png +"-"+ ext);break;}
			System.out.println(count + ": " + png +"-"+ ext);
			count++;
		}
		/**
		 * Loop End 
		 */



	}
}
