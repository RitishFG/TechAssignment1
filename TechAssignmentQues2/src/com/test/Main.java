package com.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
//How will you tackle the challenge above?
// splitting the input values on basis of certain characters
// if values are directly given, store them in a hashMap else evaluate expression and store in map
// save data in new output file


//What type of errors you would you check for?
//  Whether file exists or not
//  Whether the expression contains valid symbols to evaluate the expression

//How might a user break your code?
//  if expression contains other than + operator the code will not be able to split and evaluate
//  if any expression has variable which will be encountered later while reading the file, then inconsistent output will be given

public class Main {
	public static void saveFile(Map<String,Integer>map) throws FileNotFoundException
	{
//		creating printwriter object 
		PrintWriter pw=new PrintWriter("Output.csv");
//		looping over map to save evaluated expressions
		for(Map.Entry<String, Integer>e:map.entrySet())
		{
			pw.print(e.getKey()+" : "+e.getValue()+" , ");
		}
		pw.flush();
	}
	public static void main(String[] args) throws IOException {
//		creating file reader and buffered reader object to read input from file
		FileReader fr=new FileReader("Input.txt");
		BufferedReader br=new BufferedReader(fr);
		
//		reading initial line from the file
		String str=br.readLine();
		StringBuilder sb=new StringBuilder("");
//		looping till the pointer reacher eof
		while(str!=null)
		{
//			appending the string in stringbuilder
			sb.append(str);
			sb.append("\n");
			str=br.readLine();
		}
//		getting array of string after splitting the input on ,
		String arr[]=sb.toString().trim().split(",");
		Map<String,Integer>map=new HashMap<>();
		for(int i=0;i<arr.length;i++)
		{
//			splitting the array further on : 
			String arr2[]=arr[i].trim().split(":");
			for(int j=0;j<arr2.length-1;j++)
			{
//				if string contains = means expression needs to be evaluated
				if(arr2[j+1].trim().contains("="))
				{
//					splitting arr2, using trim to remove whitespaces, substring to get rid of =
//					splitting on +
					String exp[]=arr2[j+1].trim().substring(1).split("\\+");
					int res=0;
//					if map does not contain the first input means it must be Integer and not variable
					if(!map.containsKey(exp[0]))
					{
//						using parseInt to get integer value
						res=res+Integer.parseInt(exp[0]);
					}
					else
					{
//						this block states that variable is present in map already, getting value
						res=res+map.get(exp[0]);
					}
					if(!map.containsKey(exp[1]))
					{
						res=res+Integer.parseInt(exp[0]);
					}
					else
					{
						res=res+map.get(exp[1]);
					}
//					saving the calculated result as value corresponding to key
					map.put(arr2[j].trim(), res);
				}
//				direct value present in input
				else
				{
//					saving the value in map
					map.put(arr2[j].trim(), Integer.parseInt(arr2[j+1].trim()));
				}
			}
		}
//		calling save file method
		saveFile(map);
		System.out.println("Done");
	}

}
