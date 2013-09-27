#include <iostream>
using namespace std;

const int SIZE = 28123;

int divisor_sum(int n);

main() {
  long sum = 0;
  bool is_abundant[SIZE];

  for(int i = 0; i < SIZE; i++) {
    is_abundant[i] = divisor_sum(i) > i;

    sum += i;
    for(int j = i-1; j > 0; j--) {
      if(is_abundant[j] && is_abundant[i-j]) {
	sum -= i;
	//cout << j << " + " << i-j << " = " << i << endl;
	break;
      }
    }
  }

  cout << sum <<  endl;
}

int divisor_sum(int n) {
  int sum = 1;

  for(int i = 2; i*i <= n; i++) {
    if(n % i == 0)
      sum += (i*i == n) ? i : i + n/i;
  }
  
  return sum;
}
