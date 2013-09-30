#include <iostream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

string permutation(int nth, int digits); // n-th permutation of digits (0 ... digits)
unsigned int factorial(unsigned int n);

main() {
  cout << permutation(1000000, 10) << endl;
}

string permutation(int nth, int digit_number) {
  string result = "";
  int n = nth - 1;
  ostringstream oss;

  vector<int> digits;
  for (int i = 0; i < digit_number; i++)
    digits.push_back(i);
 
  while(digits.size() > 1) {
    int next_index = n / factorial(digits.size() - 1);

    oss << digits[next_index];
    result = result + oss.str();
    oss.str("");

    digits.erase(digits.begin() + next_index);
    n %= factorial(digits.size());
  }

  oss << digits[0];
  result += oss.str();
  return result;
}

unsigned int factorial(unsigned int n) {
  static const unsigned int table[] = {1, 1, 2, 6, 24, 120, 720,
    5040, 40320, 362880, 3628800, 39916800, 479001600};
  if (n >= sizeof table / sizeof *table)
    throw "Range error";
  return table[n];
}
