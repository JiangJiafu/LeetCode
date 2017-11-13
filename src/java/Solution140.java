import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：https://leetcode.com/problems/word-break-ii/#/description
 * 思路：深度优先搜索，同时保存匹配的节点，以删除不必要的分支，加快搜索。
 *      一开始自己只能想到深度优先搜索，参考别人的答案后，才想到可以用这种方式来优化。
 */
public class Solution140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}