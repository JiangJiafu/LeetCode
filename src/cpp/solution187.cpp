#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <set>
#include <map>

using namespace std;

class Solution {
 public:
  vector<string> findRepeatedDnaSequences(string s) {
	map<string, int> str_count;
	vector<string> result;
	
	if (s.size() < 11) {
	  goto l_out;
	}
	
    for (int i = 0; i <= s.size() - 10; ++i) {
	  string sub = s.substr(i, 10);
	  int &count = str_count[sub];
	  ++count;
	}
	
	for (auto it = str_count.begin(); it != str_count.end(); ++it) {
	  if (it->second > 1) {
		result.push_back(it->first);
	  }
	}

  l_out:
	return result;
  }
};

int main() {
  Solution s = Solution();
  string test_data = "AAAAAAAAAAA";

  // will print ["AAAAACCCCC", "CCCCCAAAAA"].
  vector<string> result = s.findRepeatedDnaSequences(test_data);
  for (auto it = result.begin(); it != result.end(); ++it) {
	std::cout << *it << std::endl; 
  }

  return 0;
}
