/*										Project 1

Professor: Roger Eggen
Class:Compilers
Author: Gavin Murray
N#:N01062011
Description: Lexical analyzer 



*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class proj1{
	

		
	public static void main(String[] args){
		String lexeme="";
		String S="";
		int n=0;
		String token="";
		String A="+-{}[](),;";
		String B="<=>";

		char x=' ';
		char y=' ';
		boolean comment=false;

		try{
			File file = new File(args[0]);
			Scanner input= new Scanner(file);
			while(input.hasNext()){
				S=input.nextLine();
				n=S.length();

				System.out.println("Input:"+S);
				System.out.println("Output:");
				for(int i=0;i<n;i++){
					lexeme="";
					token="";

					x=S.charAt(i);
					if((i+1)<n){
						y=S.charAt(i+1);
					}
					else{
						y=' ';
					}

					if(x==' '||x=='\t'){
						continue;
					}
					else if(A.indexOf(x)>-1){
						lexeme+=x;
						token="Symbol";
					}
					else if(B.indexOf(x)>-1){
						lexeme+=x;
						token="Symbol";
						if(y=='='){
							lexeme+=y;
							i++; //Prepare to skip next character.
						}
					}
					else if(x=='!'&&y=='='){
						lexeme+=x;
						lexeme+=y;
						token="Symbol";
						i++;
					}
					else if(x=='/'){
						lexeme+=x;
						token="Symbol";
						if(y=='*'||y=='/'){
							lexeme+=y;
							i++;
						}
					}
					else if(x=='*'){
						lexeme+=x;
						token="Symbol";
						if(y=='/'&&comment){
							lexeme+=y;
							i++;
						}
					}
					else if(isNumber(x)){
						lexeme+=x;
						token="Number";
						while((i+1)<n&&(isNumber(S.charAt(i+1)))){
							lexeme+=(S.charAt(++i));
						}
					}
					else if(isLetter(x)){
						lexeme+=x;
						while((i+1)<n&&isLetter(S.charAt(i+1))){
							lexeme+=(S.charAt(++i));
						}
						switch(lexeme){
							case "if":
							case "int":
							case "else":
							case "while":
							case "void":
							case "return":
								token="Keyword";
								break;
							default:
								token="ID";
						}

					}
					else{
						token="Error";
						lexeme+=x;
					}
					if(lexeme.equals("/*")){
						comment=true;
					}
					else if(lexeme.equals("//")){
						break;
					}

					if(!comment){
						if(lexeme=="Symbol"){
							System.out.println(lexeme);
						}
						else{
							System.out.println(token+':'+lexeme);
						}
					}
					else if(lexeme.equals("*/")){
						comment=false;
					}



				}
				System.out.println();
			}
			input.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Void file");
		}
	
	}


static boolean isLetter(char ch){
	if(ch>=65&&ch<=90||(ch>=97&&ch<=122))
		return true;
	
	return false;
}

static boolean isNumber(char ch){
	if(ch>=48&&ch<=57){
		return true;
	}

	return false;
}


	
	
}

