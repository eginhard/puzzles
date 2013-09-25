:- ensure_loaded([input]).

main :-
	open('keylog.txt', read, Str),
	read_numbers(Str, Numbers),
	close(Str),
	write(Numbers), nl, nl,
	define_before(Ordered, Numbers),
	sort(Ordered, Sorted),
	%write(Sorted), nl, nl,
	code(X, Sorted),
	write(X).

define_before([], []).
define_before([A|[B|[C|L1]]], [[X,Y,Z]|L2]) :-
	A = [X|Y],
	B = [Y|Z],
	C = [Z|x],
	define_before(L1, L2).

code([X|[Y|[]]], Order) :-
	member([Y,x], Order),
	member([X,Y], Order).

code([X|[Y|L]], Order) :-
	member([X,Y], Order),
	code([Y|L], Order).