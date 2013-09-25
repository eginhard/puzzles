%%%%% INPUT DATA %%%%%

%%% Read list of numbers %%%

read_numbers(Stream, []) :-
	at_end_of_stream(Stream).

read_numbers(Stream, [X|L]) :-
	\+ at_end_of_stream(Stream),
	read_number(Stream, X),
	read_numbers(Stream, L).

%%% Read one number %%%

read_number(Stream, W) :-
	get_code(Stream, Char),
	check_char_read_rest(Char, Chars, Stream),
	atom_codes(V, Chars),
	atom_chars(V, W).

%%% Read individual character %%%

check_char_read_rest(10, [], _) :- !. % new line

check_char_read_rest(-1, [], _) :- !. % end of stream

check_char_read_rest(end_of_file, [], _) :- !.

check_char_read_rest(Char, [Char|Chars], Stream) :-
	get_code(Stream, NextChar),
	check_char_read_rest(NextChar, Chars, Stream).