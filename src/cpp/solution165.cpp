using namespace std;

class Solution {
public:
    int compareVersion(string version1, string version2) {
        int ret = 0;
        string delimiter = ".";
        int cur_pos1 = 0;
        int cur_pos2 = 0;
        int last_dot_pos1, last_dot_pos2;
        while (true)
        {
            int v1, v2;
            last_dot_pos1 = version1.find(cur_pos1, delimiter);
            last_dot_pos2 = version2.find(cur_pos2, delimiter);
      
            if (last_dot_pos1 != string::npos) {
                v1 = atoi(version1.substr(cur_pos1, last_dot_pos1).c_str());
                cur_pos1 = last_dot_pos1 + 1;
            } else {
                v1 = atoi(version1.substr(cur_pos1, version1.size()));
                cur_pos1 = version1.size(); // reach the end of the string
            }

            if (last_dot_pos2 != string::npos) {
                v2 = atoi(version2.substr(cur_pos2, last_dot_pos1).c_str());
                cur_pos2 = last_dot_pos2;
            } else {
                v2 = atoi(version2.substr(cur_pos2, version2.size()));
                cur_pos2 = version2.size();
            }

            if (v1 > v2) {
                ret = 1;
                goto l_out;
            } else if (v1 < v2) {
                ret = -1;
                goto l_out;
            } else {
                if (cur_pos1 == version1.size() && cur_pos2 != version2.size()) {
                    ret = -1;
                    goto l_out;
                } else if (cur_pos1 != version1.size() && cur_pos2 == version2.size()){
                    ret = 1;
                    goto l_out;
                } else if (cur_pos1 == version1.size() && cur_pos2 == version2.size()){
                    ret = 0;
                    goto l_out;
                } else {
                    continue;
                }
            }
            
        }

    l_out:
        return ret;
        
    }
};
