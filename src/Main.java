/****************************************************************
 * file: Main.java 
 * author: Derek Nowicki
 * class: CS 141 – Programming and Problem Solving
 * 
 * assignment: program 1
 * date last modified: 2018-01-21
 * 
 * purpose: This program accepts from the standard input a sequence
 * of integer values with each value separated by a space.
 * It builds a binary search tree using these values in the order they are entered
 * then prints 3 traversals: pre-order, in-order, and post-order.
 * 
 ****************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import TreePackage.BinarySearchTree;

public class Main {
	private static String [] command;
	private static String numbers;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

	static void printCommandMenu() {
		System.out.println("Please Enter a Command");
		System.out.println("I: Insert a value");
		System.out.println("D: Delete a value");
		System.out.println("P: Find predecessor");
		System.out.println("S: Find successor");
		System.out.println("E: Exit the program");
		System.out.println("H: Display this message");
	}
	
	static void getNumbers() {
		System.out.println("Please enter the initial sequence of values:");
		try {
			numbers = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String [] arrOfStr = numbers.split(" ");
		
		for (String a : arrOfStr) {
			bst.add(Integer.parseInt(a));
		}
		System.out.println();
		System.out.println("In-order: "+bst.inorderTraverse());
	}
	
	static void getCommand() {
		String cmdString = "";
		try {
			cmdString = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		command = cmdString.split(" ");
	}
	
	static void handleInsertCommand() {
		if(command.length > 1) {
			bst.add(Integer.parseInt(command[1]));
			System.out.println(bst.inorderTraverse());
		} else {
			System.out.println("Too few arguments");
		}
	}
	
	static void handleDeleteCommand() {
		if(command.length > 1) {
			System.out.println("D: Delete a value -> " + command[1]);
			bst.remove(Integer.parseInt(command[1]));
			System.out.println(bst.inorderTraverse());
		} else{
			System.out.println("Too few arguments");
		}
	}
	
	static void handlePredecessorCommand() {
		if(command.length > 1) {
			List<Integer> tmp = bst.inorderTraverse();
			int index = tmp.indexOf(Integer.parseInt(command[1]));
			if(index > 0) {
				System.out.println(tmp.get(index - 1));
			} else {
				System.out.println("Predecessor of "+ command[1] + " not found");
			}
		} else {
			System.out.println("Too few arguments");
		}
	}
	
	static void handleSuccessorCommand() {
		if(command.length > 1) {
			List<Integer> tmp = bst.inorderTraverse();
			int index = tmp.indexOf(Integer.parseInt(command[1]));
			if(index < tmp.size() - 1) {
				System.out.println(tmp.get(index + 1));
			} else {
				System.out.println("Successor of "+ command[1] + " not found");
			}
		} else {
			System.out.println("Too few arguments");
		}
	}
	
	public static void main(String[] args) {
		getNumbers();
		
		while(true) {
			printCommandMenu();
			getCommand();
			
			switch(Character.toUpperCase(command[0].charAt(0))) {
			case 'I':
				handleInsertCommand();
				break;
			case 'D':
				handleDeleteCommand();
				break;
			case 'P':
				handlePredecessorCommand();
				break;
			case 'S':
				handleSuccessorCommand();
				break;
			case 'E':
				System.exit(0);
				break;
			case 'H':
				printCommandMenu();
				break;
			default:
				System.out.println("Invalid command, try again -> " + command[0]);
				break;
			}
			
		}

	}
}
