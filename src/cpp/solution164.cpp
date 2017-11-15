#include <iostream>
using namespace std;

class Solution {
public:
    int maximumGap(vector<int>& nums) {
        if (nums.size() <= 1) {
            return 0;
        }
        int minn = INT_MAX;
        int maxx = INT_MIN;
        for (int i = 0; i < nums.size(); ++i) {
            minn = min(nums[i], minn);
            maxx = max(nums[i], maxx);
        }
        if (maxx - minn == 0) {
            return 0;
        }
        int size_of_bucket = (maxx - minn) / (nums.size() - 1);
        if ((maxx - minn) % (nums.size() - 1) != 0)
        {
            size_of_bucket += 1;
        }
        int bucket_nr = (maxx - minn) / size_of_bucket + 1;
      
        vector<int> minb(bucket_nr, INT_MAX);
        vector<int> maxb(bucket_nr, INT_MIN);
        for (int i = 0; i < nums.size(); ++i) {
            int id = (nums[i] - minn) / size_of_bucket;
            minb[id] = min(minb[id], nums[i]);
            maxb[id] = max(maxb[id], nums[i]);
        }
        int ret = 0;
        int premax = maxb[0];
        for (int i = 1; i < minb.size(); ++i) {
            if (minb[i] != INT_MAX) {
                ret = max(ret, minb[i] - premax);
                premax = maxb[i];
            }
        }
        return ret;
    }
};
