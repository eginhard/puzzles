:- ensure_loaded([keylog]).

before(X,Y) :-
    before(X,Y,_).

before(Y,Z) :-
    before(_,Y,Z).

before(X,Z) :-
    before(X,_,Z).

code([X|[]]) :-
    \+ before(X,_),
    before(_,X).
code([X|[Y|L]]) :-
    before(X,Y),
    code([Y|L]).
