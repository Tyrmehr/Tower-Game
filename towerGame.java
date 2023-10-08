/*
 * Tower Of Krane
 */
package towerOfKrane;

import java.util.Random;

public class towerGame {
	static String blank = "         |        ";
	static boolean gameOver = false;
	static String tower1Base  = "    **********    ";
	static String tower1Middle= "      ******      ";
	static String tower1Top   = "        **        ";
	
	static String tower2Base  = "    oooooooooo    ";
	static String tower2Middle= "      oooooo      ";
	static String tower2Top   = "        oo        ";
	
	static String tower3Base  = "    ^^^^^^^^^^    ";
	static String tower3Middle= "      ^^^^^^      ";
	static String tower3Top   = "        ^^        ";
	
	static String bases       = " ---------------- ";
	static int count =0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		
				//Initialize the pieces into an array
				String[] originalPieces = new String[9];
				originalPieces[0]=tower1Base; 
				originalPieces[1]=tower1Middle;
				originalPieces[2]=tower1Top;
				originalPieces[3]=tower2Base;
				originalPieces[4]=tower2Middle;
				originalPieces[5]=tower2Top;   
				originalPieces[6]=tower3Base;  
				originalPieces[7]=tower3Middle;
				originalPieces[8]=tower3Top;   
				//shuffle the pieces
				String[] shuffled = shufflePieces(originalPieces);/****/
				//make an array to store the shuffled pieces for display
				String[][] gameBoard = insertInto2DArray(shuffled);/***/
				
				//Print Welcome message with instructions
				Scanner in = new Scanner(System.in);
				System.out.println("           Welcome to the Amazing Towers of Krane Game !");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("\n\nYou must move the pieces around one at a time into an empty slot to build ");
				System.out.println("the three towers in the right way. The least amount of moves, the better!\n\n");
				System.out.println("The completed towers could look something like this:\n");
				printGame(exampleBoard());
				System.out.println("\n\nThe '|' symbols are empty slots that you can move a piece to. ");
				System.out.println("Good luck!\n\n\n");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("--------------------------------GET READY---------------------------------");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("-----------------------------Your Game Board------------------------------\n\n");
				
				//make a loop to start the game
				while(gameOver==false) {
					while(true) {
						printGame(gameBoard);
						System.out.println("Choose a column to move a piece from: ");
						int from = in.nextInt();
						System.out.println("Choose a column to move the piece to: ");
						int to = in.nextInt();
						
						if(validMove(gameBoard, from-1, to-1)) {
							movePiece(gameBoard, from-1, to-1);
							System.out.println("Total number of moves: "+count);
							break;
						}
						else
							System.out.println("Illegal Move! Try again.");
						
					}//end while
					gameOver = checkGameOver(gameBoard);
				}//end while
				printGame(gameBoard);
				System.out.println("You win!!!");
				System.out.println("Total number of moves: "+count);
				System.out.println("Final Score: " +(10000-count*100)+ " points");
				System.out.println("Thanks for playing!");
					
			}//end main
			
			
			
			// An example game board for the Welcome Message
			public static String[][] exampleBoard(){
				String[][] board = new String[5][4];
				board[0][0]=tower1Top;
				board[1][0]=tower1Middle;
				board[2][0]=tower1Base;
				board[3][0]=bases;
				board[4][0]="       1           ";
				board[0][1]=tower2Top;
				board[1][1]=tower2Middle;
				board[2][1]=tower2Base;
				board[3][1]=bases;
				board[4][1]="       2           ";
				board[0][2]=tower3Top;
				board[1][2]=tower3Middle;
				board[2][2]=tower3Base;
				board[3][2]=bases;
				board[4][2]="       3           ";
				board[0][3]=blank;
				board[1][3]=blank;
				board[2][3]=blank;
				board[3][3]=bases;
				board[4][3]="       4           ";
				return board;
				
			}
			
			// Returns a 2D array with the shuffled pieces inserted 
			public static String[][] insertInto2DArray(String[] array){
				
				String[][] pieces2D = new String[5][5];
				int count = 0;
				for(int i = 0; i<3;i++){
					for(int j =0;j <3;j++){
						pieces2D[i][j]= array[count];
						count++;
					}
					
				}
				for(int i = 0; i<5;i++){
					pieces2D[3][i]=" ---------------- ";
				}
				
				for(int i = 0; i<5;i++){
					pieces2D[4][i]="       " + (i+1) +"           ";
				}
				
				for(int i = 0; i<3;i++){
					pieces2D[i][3]=blank;
				}
				return pieces2D;
			}
			
			//prints out the current game board given as the parameter
			public static void printGame(String[][] array){
				
			    System.out.println();
			    for(int i = 0; i<5;i++){
				for(int j =0;j <4;j++){
				      System.out.print(array[i][j]);
				}
				System.out.println();
			    }
			}
			
			
			/*
			 *  The following methods are to be implemented by you. 
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			
			
			// Returns a 1D array with its contents shuffled in a random order
			public static String[] shufflePieces(String[] pieces){
			
				{
			        Random piece = new Random();  // Random number generator
			        //Create a for loop for the shuiffle
			        for (int i=0; i<pieces.length; i++) 
			        {
			            //Create an int called random that equals to the piece
			        	int random = piece.nextInt(pieces.length);
			            //Create a string that equal to the pieces
			        	String temp = pieces[i];
			            //This is what will mix up the pieces
			        	pieces[i] = pieces[random];
			            //This method will bring us back to our temp string so that the method will definitely shuffle
			        	pieces[random] = temp;
			        }
			        //Return pieces
			        return pieces;
			    }
				
			}
			
			
			//Determines the top unoccupied slot in the given column to move a piece to
			public static int topT(String[][] array, int col) {
				//Create an int for the slot
				int slot=0;
		       //Create a for loop for when slot is less greater than array.length and increase slot by one
				for( slot=0; slot<array.length; slot++)
		        {
		            while(array[slot][col]==blank)
		            {
		                //Create an if statement when array is blank
		            	if(array[slot][col]!=blank)
		                {
		                    //If true return 1
		            		return 1;
		                }
		                //Else when array slot and col is blank
		            	else if(array[slot][col]==blank)
		                {
		                    //Return 2
		            		return 2;
		                }
		            }
		        }
				//In the end return -1
				return -1;
		 
				
				
			}
			//Determines the top occupied slot in the given column to take the piece from
			public static int topF(String[][] array, int col) {
				
				/***your code goes here***/  
				return -1;//remove this when you have completed this method
			}
			
			//Returns true if we can move a piece from column "from" into column "to"
			public static boolean validMove(String [][] array , int from, int to) {
				//Create a boolean for when boolean valid is true
				boolean valid=true;
		        //Create an if statment when topF array is less than the top T array
				if(topF(array, to)<topT(array,from))
		        {
		            //Return true if so
					return true;
		        }
		        //Else if top is greater than topT
				else if (topF(array, to)>topT(array,from))
		        {
		           //Return false
					return false;
		        }
		        //Return valid when complete
				return valid;
				
			}
			
			// moves a piece from column "from" into column "to"
			public static void movePiece(String [][] array , int from, int to) {
				
				
				//increases # of moves by one if move can be done
		        {
		           //Create an int for the number of moves 
		        	int move = 3;
		             
		        	for (int i=3; i>= 0; i--)
		             {
		                 for (int j=3; j>=0; j++)
		                 {
		                     if (validMove(array,from,to)==true)
		                     {
		                         move = i-1;
		                     }
		                     else
		                     {
		                         move = 3;
		                     }
		                 }

		             }
		             return;
		        }
			}
			
			//Returns true if the tower1 is complete
			public static boolean tower1Complete(String[][] array) {
				//Create a string for your first tower
				String [][]tower1 =new String[3][3];
			     //Create int for the amount which is zero   
				int amount = 0;
			    //Create a for loop for when i is less or equal to zero and while i is getting added by 2    
				for(int i=0; i<=7; i+=2) 
			        {
			         //Create a while loop when the first tower is blank   
					while(tower1[1][3]!=blank)
			            {
			               
						//Create an if statement when the int amount is greater or equal to 4
					if(amount>=4)
			                {
			            //If it is true return true        
						return true;
			                }
			                else
			                {
			                    //Else return the int amount zero
			                	amount=0;
			                }
			            }

			        }
			        return false;
			    }
			
			
			//Returns true if the tower2 is complete
			public static boolean tower2Complete(String[][] array) {
				//Create a string for your second  tower     
				 String [][]tower2 =new String[3][3];
				 //Create int for the amount which is zero   
				 int amount = 0;
				//Create a for loop for when i is less or equal to zero and while i is getting added by 2    
				 for(int i=0; i<=7; i+=2) 
			        {
					//Create a while loop when the second tower is blank  
					 while(tower2[2][3]!=blank)
			            {
						//Create an if statement when the int amount is greater or equal to 4   
						 if(amount>=4)
			                {
							//If it is true return true      
							 return true;
			                }
			                else
			                {
			                	//Else return the int amount zero
			                	amount=0;
			                }
			            }

			        }
			        return false;
			    }
			
			
			//Returns true if the tower3 is complete
			public static boolean tower3Complete(String[][] array) {
				//Create a string for your third  tower        
				 String [][]tower3 =new String[3][3];
				//Create int for the amount which is zero  
				 int amount = 0;
				//Create a for loop for when i is less or equal to zero and while i is getting added by 2    
				 for(int i=0; i<=7; i+=2) 
			        {
					//Create a while loop when the third tower is blank    
					 while(tower3[3][3]!=blank)
			            {
						//Create an if statement when the int amount is greater or equal to 4    
						 if(amount>=4)
			                {
							//If it is true return true    
							 return true;
			                }
						 
						 else
			                {
							//Else return the int amount zero     
							 amount=0;
			                }
			            }

			        }
			        return false;
			    }
			
			
			//Returns true if all towers are complete
			public static boolean checkGameOver(String[][] array) {
				//Make an if statement for when tower 1, 2 and 3 are complete and true
				if(tower1Complete(array)==true || tower2Complete(array)==true || tower3Complete(array)==true)
		        {
		            //If it it true return a true statement
					return true;
		        }
				//If it is false return false
				return false;
		        
		    }    
				
	}


