Hackerrang - T9 Prediction
=====

Small report on my solution for the following Hackerrang problem: 
[T9 Prediction](https://www.hackerrank.com/challenges/t9-predictions/)

#### Task
The task was to return the top 5 word predictions for a
given T9 (mobile phone) integer sequence, based on word
frequencies in a specified corpus and allowing only words
from a specified dictionary.

#### Overview
My solution is mainly based on two data structures:
* **A map**:

  Stores all words from the dictionary and the number of times
  it occurs in the corpus.
* **A Trie** (prefix tree):
  A trie is a tree where each child branch has a label and thus it is possible
  to quickly follow the branches for a given prefix and see which elements
  start with that prefix.
  
  In this case, each element in the trie can have up to 10 children
  (1 for each digit, although only 2-9 are needed for T9). Each element
  then has a list of all words possible with the T9 sequence leading to it,
  word of which that sequence is a prefix are stored in that element's children.
  
  My simple Trie class provides 4 methods:
  * `getWords(String t9)`: Returns all words that have the same prefix `t9`.
  * `getWords()`: Returns all words in the (sub-)trie.
  * `addWord(String word, Trie trie)`: Adds the `word` to the correct element in the trie.
  * `findPrefix(String t9, Trie trie)`: Returns the sub-trie for a given T9 prefix.
  
#### Conversion to T9
Converting a word to T9 is very easy - each character is associated with a certain digit.

#### Getting top predictions
The core algorithm follows the following steps, after the data structures
mentioned above have been created:
  1. Take a T9 prefix as input.
  2. Follow down the trie to get the correct sub-trie for that prefix.
  3. Add all words of that sub-trie and all its children to a candidate list.
  4. Storing the candidate words and their associated word counts in a map.
  5. Sort the map pairs using a custom `Comparator` based on their word count
     and break ties by sorting candidates lexicographically.
  6. Return the top 5 candidates.

#### Sample input
```6
6837
86
69
23777
11111
77777
```

#### Sample Output
```over;overlying;overcome;overcoat;overgrowth
to;under;took;united;too
my;own;myself;owing;owners
cesspool;cesspool's;cesspools
No Suggestions
No Suggestions
```
