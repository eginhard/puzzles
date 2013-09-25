main :-
	open('keylog.txt', read, Str),
	read_numbers(Str, Numbers),
	close(Str),
	write(Numbers), nl.

read_numbers(Stream, []) :-
	at_end_of_stream(Stream).

read_numbers(Stream, [X|L]) :-
	\+ at_end_of_stream(Stream),
	read_word(Stream, X),
	read_numbers(Stream, L).

read_word(Stream, Chars) :-
	get_code(Stream, Char),
	char_code(Number, Char),
	check_char_read_rest(Number, Chars, Stream).
	%number_chars(W, Chars).

check_char_read_rest(10, [], _) :- !. % new line

check_char_read_rest(-1, [], _) :- !. % end of stream

check_char_read_rest(end_of_file, [], _) :- !.

check_char_read_rest(Char, [Char|Chars], Stream) :-
	get_code(Stream, NextChar),
	char_code(Number, NextChar),
	check_char_read_rest(Number, Chars, Stream).