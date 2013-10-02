:- use_module(library(clpfd)).

sudoku(Rows) :-
	append(Rows, AllRows), AllRows in 1..9,
	maplist(all_distinct, Rows),
	transpose(Rows, Columns),
	maplist(all_distinct, Columns),


blocks([], [], []).
blocks([A,B,C|Rest1], [D,E,F|Rest2], [G,H,I|Rest3]) :-
	all_distinct([A,B,C,D,E,F,G,H,I]),
	blocks(Rest1, Rest2, Rest3).