:- use_module(library(clpfd)).

sudoku(Rows) :-
	append(Rows, AllRows), AllRows in 1..9,
	maplist(all_distinct, Rows),
	transpose(Rows, Columns),
	maplist(all_distinct, Columns),
	