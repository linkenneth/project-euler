#include <iostream>

using namespace std;

int main () {
  int sum = 0;
  for (int m = 0; m < 1000; m++) {
    if (m % 3 == 0 || m % 5 == 0) {
      sum += m;
    }
  }

  cout << sum << endl;
}
