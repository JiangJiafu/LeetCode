#include <vector>
#include <iostream>

using namespace std;

class Solution {
 public:
  int calculateMinimumHP(vector<vector<int> >& dungeon);
};

// Use dynamic programming, let min_remain[i][j] represents the minimum health 
// point when knight reaches dungeon[i][j]. And start calculate 
// min_remain[i][j] from the bottom-right to top-left.
int Solution::calculateMinimumHP(vector<vector<int> >& dungeon) {
  // min_remain[i][j] is the min remaining value when reach i,j
  vector<vector<int> > min_remain; 
  size_t m = dungeon.size();
  size_t n = dungeon[0].size();

  for (size_t i = 0; i < m; ++i) {
    min_remain.push_back(vector<int>(n));
  }
  // when reach the bottom-right corner, at least 1 health point is needed
  min_remain[m - 1][n - 1] = 1;
  
  for (int i = m - 2; i >= 0; --i) {
	int tmp = min_remain[i + 1][n - 1] - dungeon[i + 1][n - 1];
	min_remain[i][n - 1] = max(tmp, 1);
  }
  
  for (int j = n - 2; j >= 0; --j) {
	int tmp = min_remain[m - 1][j + 1] - dungeon[m - 1][j + 1];
	min_remain[m - 1][j] = max(tmp, 1);
  }

  for (int i = m - 2; i >= 0; --i) {
    for (int j = n - 2; j >= 0; --j) {
	  // the min remain health point needed to go rightward
	  int min_remain1 = min_remain[i][j + 1] - dungeon[i][j + 1];
	  min_remain1 = max(min_remain1, 1);
	  
	  // the min remain health point needed to go downward
	  int min_remain2 = min_remain[i + 1][j] - dungeon[i + 1][j];	  
	  min_remain2 = max(min_remain2, 1);
	  min_remain[i][j] = min(min_remain1, min_remain2);
    }
  }
  
  return max(min_remain[0][0] - dungeon[0][0], 1);
}

int main() {
  Solution s = Solution();
  vector<vector<int> > test_data;
  vector<int> row1;
  row1.push_back(0);
  row1.push_back(0);
  test_data.push_back(row1);
  vector<int> row2;
  row2.push_back(-5);
  row2.push_back(-4);
  test_data.push_back(row2);
  cout << s.calculateMinimumHP(test_data) << endl; // will print 5

  return 0;
}
