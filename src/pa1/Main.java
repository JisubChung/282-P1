package pa1;


import java.util.Scanner;

import proj1.cacheMem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author afterafx
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	static Scanner input = new Scanner(System.in);

	public static int[] mainMem;
	public static int[] cacheSize;
	public static int blockSize;


	public static void enterParam(){
		System.out.println("Enter main memory size (words):");
		int inputMemSize = input.nextInt();
		mainMem = new int[inputMemSize];

		System.out.println("Enter Cache Size (words):");
		int inputCacheSize = input.nextInt();
		cacheSize = new int[inputCacheSize];

		System.out.println("Enter block size (words/block);");
		blockSize = input.nextInt();
	}

	public static void readCache(){
		System.out.println("Enter main memory address to read from: ");
		int memReadFrom = input.nextInt();
		System.out.println("Word 15 of block 63 with tag 63 contains value:  " + mainMem[memReadFrom]);
	}

	public static void writeCache(){

	}


	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Select an option:");
		System.out.println("1. Enter Parameters");
		System.out.println("2. Read Cache");
		System.out.println("3. Write to Cache");
		System.out.println("4. Quit Program");

		cacheMem cacheObj = new cacheMem();

		int choice = input.nextInt();

		switch(choice){
		case 1: enterParam();
		break;
		case 2: readCache();
		break;
		case 3: writeCache();
		break;
		case 4: System.out.println("You Have Exited the Program!");
		break;
		default: System.out.println("Invalid Entry");
		break;
		}

	}

}