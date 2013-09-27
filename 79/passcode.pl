:- ensure_loaded([keylog]).

before(X,Y) :-
    before(X,Y,_).

before(Y,Z) :-
    before(_,Y,Z).

before(X,Z) :-
    before(X,_,Z).

code([X|[]], 1) :-
    \+ before(X,_),
    before(_,X).

code([X|[Y|L]], Length) :-
    before(X,Y),
    Less is Length-1,
    code([Y|L], Less).
