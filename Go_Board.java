import java.util.*;

public class Go_Board{

	//every go board is an N x N vector
	int size;
	int[][] elements;
	String last_move = "first move";

	// Constructor
	public Go_Board(){
		size = 11;
		elements = new int[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				elements[i][j] = 0;
			}
		}
	}
	// Overloaded Constructor
	public Go_Board(int s){
		size = s;
		elements = new int[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				elements[i][j] = 0;
			}
		}
	}

	// Copy Constructor
	public Go_Board(Go_Board board){
		size = board.size;
		elements = board.elements;
	}

	public int[][] get_elements(){
		return elements;
	}

	public int get_size(){
		return size;
	}

	public void set_size(int s){
		size = s;
	}

	public String int_to_alpha_row(int i){
		String row_letter= "";
			switch(i+1){
				case 1: row_letter = "a"; break;
				case 2: row_letter = "b"; break;
				case 3: row_letter = "c"; break;
				case 4: row_letter = "d"; break;
				case 5: row_letter = "e"; break;
				case 6: row_letter = "f"; break;
				case 7: row_letter = "g"; break;
				case 8: row_letter = "h"; break;
				case 9: row_letter = "i"; break;
				case 10: row_letter = "j"; break;
				case 11: row_letter = "k"; break;
				case 12: row_letter = "l"; break;
				case 13: row_letter = "m"; break;
				case 14: row_letter = "n"; break;
				case 15: row_letter = "o"; break;
				case 16: row_letter = "p"; break;
				case 17: row_letter = "q"; break;
				case 18: row_letter = "r"; break;
				case 19: row_letter = "s"; break;
				case 20: row_letter = "t"; break;
				case 21: row_letter = "u"; break;
				case 22: row_letter = "v"; break;
				case 23: row_letter = "w"; break;
				case 24: row_letter = "x"; break;
				case 25: row_letter = "y"; break;
				case 26: row_letter = "z"; break;

				default: row_letter= "!";			
			}		
			return row_letter;
	}

	public int alpha_row_to_int(char c){
		int ret = 0;
		switch(c){
				case 'a': ret = 1; break;
				case 'b': ret = 2; break;
				case 'c': ret = 3; break;
				case 'd': ret = 4; break;
				case 'e': ret = 5; break;
				case 'f': ret = 6; break;
				case 'g': ret = 7; break;
				case 'h': ret = 8; break;
				case 'i': ret = 9; break;
				case 'j': ret = 10; break;
				case 'k': ret = 11; break;
				case 'l': ret = 12; break;
				case 'm': ret = 13; break;
				case 'n': ret = 14; break;
				case 'o': ret = 15; break;
				case 'p': ret = 16; break;
				case 'q': ret = 17; break;
				case 'r': ret = 18; break;
				case 's': ret = 19; break;
				case 't': ret = 20; break;
				case 'u': ret = 21; break;
				case 'v': ret = 22; break;
				case 'w': ret = 23; break;
				case 'x': ret = 24; break;
				case 'y': ret = 25; break;
				case 'z': ret = 26; break;

				default: ret= 0;					
		}
		return ret;
	}

	public Boolean is_valid_move(String move){
		if(move.length() > 2) return false;
		if(Character.isLetter(move.charAt(0)) == false) return false;
		if(Character.isDigit(move.charAt(1)) == false) return false;
		return true;
	}

	public Boolean does_move_exist(Go_Board board, int i , int j){
		int[][] b = board.get_elements();
		if( b[i-1][j-1] == 0) return false;
		return true;
	}

	public static void print_board(Go_Board board){
		int[][] b = board.get_elements();
		String row_letter;
		String print_let =  "";
		int val;
		System.out.print(" ");
		for(int i = 0; i < board.get_size(); i++){
			val= i + 1;
			if(val >= 10){
				System.out.print(" " + val + " ");
			}
			else{
				System.out.print("  " + val + " ");
			}	
		}

		System.out.println("");
		System.out.print("  ");
		for(int i = 0; i< board.get_size(); i++){
			System.out.print("___ ");
		}
		System.out.println();
		for(int i = 0; i < board.get_size(); i++){
			row_letter = board.int_to_alpha_row(i);
			System.out.print(row_letter);
			System.out.print("|");
			
			for(int k = 0; k < board.get_size(); k++){
				System.out.print("   |");
			}
			System.out.println("");
			System.out.print(" |");
			
			for(int j = 0; j < board.get_size(); j++){
				if(b[i][j] == 0){
					System.out.print("___|");
				}
				else{
					if(b[i][j] == 1) print_let = "D";
					if(b[i][j] == 2) print_let = "L";
					if(b[i][j] == 0) print_let = "0";
					System.out.print("_"+ print_let + "_|" );
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void change_board(Go_Board board, int i, int j, int value){
		int[][] b = board.get_elements();
		b[i][j] = value;
		int j_plus_one = j + 1;
		board.last_move = board.int_to_alpha_row(i)+ ((Integer)(j_plus_one)).toString();
	}

	// Returns a heuristic value of a board based on the BEST number of connecting pieces a player has
	public int value_of_board(Go_Board board, int player){
		int val = 0;
		int max_connecting_pieces = 0;
		int tmp_connecting_pieces = 0;

		// Total number of connected pieces ...
		// ... used to multiply final heuristic value by h_val *= (num_x_piece) * x
		int num_one_piece = 0;
		int num_two_piece = 0;
		int num_three_piece = 0;
		int num_four_piece = 0;

		int[][] b = board.get_elements();
		Boolean winning = false;
		for(int i = 0; i < board.size; i++){
			for(int j = 0; j < board.size; j++){
				tmp_connecting_pieces = 0; // reset
				// check if board is winning board, if so, return max val
				winning = board.is_winning_board(board, player);
				if(winning == true) return val = 100000;
				if(b[i][j] == player){
					tmp_connecting_pieces = 1;
					// position 'i' has ATLEAST enough space for 5
					if( i < board.size - 4 ){

						// 1) Check vertically going down
						if( b[i+1][j] == player ){
							tmp_connecting_pieces = 2;
							if( b[i+2][j] == player){
								tmp_connecting_pieces = 3;
								if( b[i+3][j] == player){
									tmp_connecting_pieces = 4;
								}
							}
						}
						// Compare to final and change
						if (tmp_connecting_pieces > max_connecting_pieces) max_connecting_pieces =tmp_connecting_pieces;

						if ( j < board.size - 4){
							// 2) Check diagonally going down and ->	
							if( b[i+1][j+1] == player){
								tmp_connecting_pieces = 2;
								if(b[i+2][j+2] == player){
									tmp_connecting_pieces = 3;
									if(b[i+3][j+3] == player){
										tmp_connecting_pieces = 4;
									}
								}
							}
							// Compare to final and change
							if (tmp_connecting_pieces > max_connecting_pieces) max_connecting_pieces =tmp_connecting_pieces;
						}							
					}

					if ( j < board.size - 4){
						// 3) Check horizontal going ->
						if( b[i][j+1] == player ){
							tmp_connecting_pieces = 2;
							if( b[i][j+2] == player ){
								tmp_connecting_pieces = 3;
								if( b[i][j+3] == player){
									tmp_connecting_pieces = 4;
								}
							}
						}
						// Compare to final and change
						if (tmp_connecting_pieces > max_connecting_pieces) max_connecting_pieces =tmp_connecting_pieces;
						if( i > 4){
							// 4) Check diagonally going up and ->			
							if( b[i-1][j+1] == player) {
								tmp_connecting_pieces = 2;
								if ( b[i-2][j+2] == player){
									tmp_connecting_pieces = 3;
									if( b[i-3][j+3] == player){
										tmp_connecting_pieces = 4;
									}
								}
							}
							// Compare to final and change
							if (tmp_connecting_pieces > max_connecting_pieces) max_connecting_pieces =tmp_connecting_pieces;	
						}
					}	
				}
				// Update counts of connecting pieces
				switch(tmp_connecting_pieces){
					case 1: num_one_piece++; break;
					case 2: num_two_piece++; break;
					case 3: num_three_piece++; break;
					case 4: num_four_piece++; break;
					default: break;
				}
			}
		}
		// Translate to TOTAL connecting pieces to a value
		switch(max_connecting_pieces){
			case 0: val = 0; break;
			case 1: val = 10; break;
			case 2: val = 100; break;
			case 3: val = 1000; break;
			case 4: val = 10000; break;
			default : val = 0;
		}

		// Consider number of sets
		num_one_piece *= 10;
		num_two_piece *= 100;
		num_three_piece *= 1000;
		num_four_piece *= 10000;
		// Calculate heuristic val.
		//System.out.println("Value of board is : "+ val + num_one_piece + num_two_piece + num_three_piece + num_four_piece);
		return val + num_one_piece + num_two_piece + num_three_piece + num_four_piece;
	}

	// Mini-max Algorithm Implementation
	// Given a board, return the best possible choice (i,j) using the mini-max alg.
	// (i,j) is returned as an ArrayList<int>
	// NOTE: Implements alpha-beta pruning with min,max

	public int mini_max_choice(Go_Board board, int depth, int maximizing_player, int max, int min){
		int best_val = 0;

  		ArrayList<ArrayList<Integer>> possible_moves = new ArrayList<ArrayList<Integer>>();
  		possible_moves = board.list_of_possible_moves(board);
  		double depth_pow = Math.pow((double)10,(double)depth);

  		// base case: reached depth limit OR no more moves to make (tie, score = 0)
		if( (depth == 0) || (possible_moves.size() == 0) ){
			return board.value_of_board(board, maximizing_player);
		}

  		if(maximizing_player == 1){
  			best_val = min;
  			// traverse through each child of node (each possibility of board)
  			for(int i = 0; i < possible_moves.size() ; i++){
  				ArrayList<Integer> the_move = possible_moves.get(i);
  				board.change_board(board, the_move.get(0), the_move.get(1), 1);
  				int v = mini_max_choice(board, depth-1, 2, max, best_val);
  				best_val = Math.max(v,best_val);
  				board.change_board(board, the_move.get(0), the_move.get(1), 0); // undo change
  				if (best_val > max) return max;
  			}
  			return best_val;
  		}

  		else{ // minimizing player
  			best_val = max;
  			//traverse through each child of node
  			for(int i = 0; i < possible_moves.size() ; i++){
  				// make copy of board bc we don't want to alter master board
  				ArrayList<Integer> the_move = possible_moves.get(i);
  				board.change_board(board, the_move.get(0), the_move.get(1), 2); //try move
  				int v = mini_max_choice(board, depth-1, 1, best_val, min);
  				best_val = Math.min(v,best_val);
  				board.change_board(board, the_move.get(0), the_move.get(1), 0); //undo move
  				if (best_val < min) return min;
  			}
  			return best_val;
  		}
	}

	
	// Return coordinates (ArrayList<Integer>) of best available move via minimax
	// best_move depth set to 2 (look 2 steps ahead)
	public ArrayList<Integer> find_best_move(Go_Board board, int player){
		int best_val = -100000;
		ArrayList<Integer> best_move = new ArrayList<Integer>();
		// default ret vals is the first val of possible_moves
		// get possible moves of the board and traverse
		ArrayList<ArrayList<Integer>> possible_moves = new ArrayList<ArrayList<Integer>>();
  		possible_moves = board.list_of_possible_moves(board);
  		best_move.add( (possible_moves.get(0)).get(0) ); // x-coordin
		best_move.add( (possible_moves.get(0)).get(1) ); // y-coordin
  		for(int i = 1; i < possible_moves.size(); i++){
 			ArrayList<Integer> the_move = possible_moves.get(i);
  			board.change_board(board, the_move.get(0), the_move.get(1), player); // find move
  			int move_val = mini_max_choice(board, 2, player, 100000, -100000); // CHANGE_DEPTH = 3
  			board.change_board(board, the_move.get(0), the_move.get(1), 0); // undo move
  			if( move_val > best_val){
  				best_move.set(0, the_move.get(0));
  				best_move.set(1, the_move.get(1));
  				best_val = move_val;
  				System.out.println("Best move has changed, value is :" + best_val);
  			}
  			if (best_val == 100000){
  				System.out.println(" * Game-winning move found: " + best_move.get(0) + " , " + best_move.get(1));
  				break;
  			}
  		}
		System.out.println("Chosen best move is : " + best_move.get(0) + "," + best_move.get(1));
		return best_move;
	}

	// Returns ArrayList<ArrayList<Integer>> which holds a list of all possible moves
	public ArrayList<ArrayList<Integer>> list_of_possible_moves(Go_Board board){
		int[][] b = board.get_elements();
		ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < board.size; i++){
			for(int j = 0; j < board.size; j++){
				if(b[i][j]==0){
					ArrayList<Integer> inner = new ArrayList<Integer>();
					inner.add(i);
					inner.add(j);
					outer.add(inner);
				}
			}
		}
		return outer;
	}

	public Boolean is_winning_board(Go_Board board, int player){
		int[][] b = board.get_elements();
		Boolean victory = false;
		for(int i = 0; i < board.size; i++){
			for(int j = 0; j < board.size; j++){
				// Make sure the answer fits onto the board and we don't segfault
				// Checking conditions for 1
				if(b[i][j]==player){
					// position 'i' has ATLEAST enough space for 5
					if( i < board.size - 4 ){

						// 1) Check vertically going down
						if( (b[i+1][j] == player) && 
							(b[i+2][j] == player) &&
							(b[i+3][j] == player) &&
							(b[i+4][j] == player) ){
							return victory = true;
						}
						if ( j < board.size - 4){
							// 3) Check diagonally going down and ->	
							if( (b[i+1][j+1] == player) && 
								(b[i+2][j+2] == player) &&
								(b[i+3][j+3] == player) &&
								(b[i+4][j+4] == player) ){
								return victory = true;
							}
						}							
					}
					if ( j < board.size - 4){
						// 2) Check horizontal going ->
						if( (b[i][j+1] == player) && 
							(b[i][j+2] == player) &&
							(b[i][j+3] == player) &&
							(b[i][j+4] == player) ){
							return victory = true;
						}
						if( i > 4){
							// 4) Check diagonally going up and ->			
							if( (b[i-1][j+1] == player) && 
								(b[i-2][j+2] == player) &&
								(b[i-3][j+3] == player) &&
								(b[i-4][j+4] == player) ){
								return victory = true;
							}								
						}
					}
				}
			}
		}
		return victory;
	}

	public static void main(String[] args){
		int size = 7;
		Go_Board board = new Go_Board(size);
        Scanner scanner = new Scanner(System.in);
        String move = "";
        Boolean player_won = false;
        Boolean computer_won = false;
        board.print_board(board);
     
        // Program terminates at "quit" in CLI
        for(int i = 1; i < board.size*board.size; i++){
        	//Begin playing here
        	ArrayList<ArrayList<Integer>> possible_moves = new ArrayList<ArrayList<Integer>>();
  			possible_moves = board.list_of_possible_moves(board);
  			if(possible_moves.size() == 0){
  				System.out.println("No more spaces left, game has tied");
  				return;
  			}
        	System.out.println("-----------");
        	System.out.println("Turn #: " + ((Integer)i).toString());
        	System.out.println("");

        	// BEGIN HUMAN TURN
        	System.out.println("Enter a move: ");
        	move = scanner.nextLine();

        	while( board.is_valid_move(move) == false){
        		if(move.equals("quit")) return;
        		if(move.equals("moves")){
        			possible_moves = board.list_of_possible_moves(board);
        			System.out.println(possible_moves);
        			scanner.nextLine();
        		}
        		if(move.equals("value")){
        			int val = board.value_of_board(board,1);
        			System.out.println("Board value is : " + val);
        			move = scanner.nextLine();
        		}
        		else{
	        		System.out.println("Invalid move, try again.");
	        		System.out.println("Enter a move: ");
	        		move = scanner.nextLine();        			
        		}
        	}



        	int first_char_int_val = board.alpha_row_to_int(move.charAt(0)) - 1;
        	int second_char_int_val = Character.getNumericValue(move.charAt(1)) - 1;
        	// check if move exists already
        	if (board.does_move_exist(board, first_char_int_val+1, second_char_int_val+1 ) == true){
        		System.out.println("Invalid move: Move exists already, try again.");
        		System.out.println("Enter a move: ");
        		move = scanner.nextLine();
        	}
        	board.change_board(board, first_char_int_val, second_char_int_val, 1);
        	board.print_board(board);
        	System.out.println("Move played: " + move.charAt(0) + Character.getNumericValue(move.charAt(1)) );
        	
        	// Check if human made game-changing move
    		player_won = board.is_winning_board(board,1);
    		computer_won = board.is_winning_board(board,2);
    		if(player_won){
    			System.out.println("Player wins!");
    			board.print_board(board);
    			return;
    		}
    		if(computer_won){
    			System.out.println("Computer wins!");
    			board.print_board(board);
    			return;
    		}
  			if (possible_moves.size() == 0){
  				board.print_board(board);
  				System.out.println("No more moves, game is tied.");
  				return;
  			}
			// BEGIN Computer's turn (rep'd by L(2) on board)
        	ArrayList<Integer> comp_move = new ArrayList<Integer>();


        	// Count time
        	System.out.println("Computer is deciding next move...");
        	comp_move = board.find_best_move(board,2);
        	// Make decision
        	board.change_board(board, comp_move.get(0), comp_move.get(1), 2);
        	// Print board
        	board.print_board(board);
        	String comp_row = board.int_to_alpha_row(comp_move.get(0));
        	System.out.println("Move played: " + comp_row + (comp_move.get(1)+1));
        	player_won = board.is_winning_board(board,1);
    		computer_won = board.is_winning_board(board,2);
    		if(player_won){
    			System.out.println("Player wins!");
    			board.print_board(board);
    			return;
    		}
    		if(computer_won){
    			System.out.println("Computer wins!");
    			board.print_board(board);
    			return;
    		}
        }
        return;
	}
}