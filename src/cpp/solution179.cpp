#include <string>
#include <vector>
#include <iostream>

using namespace std

class Solution {
 public:
  string largestNumber(vector<int>& nums) {
    sort(nums.begin(), nums.end(), MyCompare);
	string ret;
	for (int i = nums.size() - 1; i >= 0; --i) {
	  ret += to_string(nums[i]);
	}
	
	return ret;
  }
  
 private:
  bool MyCompare(int i, int j) {
    string str_i = to_string(i);
	string str_j = to_string(j);
    string str1 = str_i + str_j;
	string str2 = str_j + str_i;
	
	int len = str1.size();
	for (int i = 0; i < len; ++i) {
	  if (str1.at(i) < str2.at(i)) {
	    return true;
	  } else if (str1.at(i) > str2.at(i){
	    return false;
	  } else {
	    continue;
	  }
	}
	return false;
  }
};

int main() {
  Solution s = Solution();
  <vector<int> > test_data;

  test_data.push_back(3);
  test_data.push_back(30);
  test_data.push_back(34);
  test_data.push_back(5);
  test_data.push_back(9);
  cout << s.largestNumber(test_data) << endl; // will print 9534330

  return 0;
}
