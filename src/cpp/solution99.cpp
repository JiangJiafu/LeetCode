//============================================================================
// Name        : solution99.cpp
// Author      : JiangJiafu
// Version     :
// Copyright   : Your copyright notice
// Description : LeetCode: https://leetcode.com/problems/recover-binary-search-tree/
//               解题思路，按顺序遍历二叉树，一定会找到两个元素：
//               第一个元素是第一次出现由大到小转变的元素中的那个大点的元素
//               第二个元素是第二次出现由大到小转变的元素中的那个小点的元素，例如
//                   | 第一次出现由大到小（5->3）
//               1 5 3 4 2 6 7 (2和5互换了位置)
//                       | 第二次出现由大到小（4—>2）
//               考虑一种特殊情况，相邻的两个数被互换了，这时只会出现一组由大到小的变化。
//============================================================================

#include <iostream>
using namespace std;

struct TreeNode {
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) :
			val(x), left(NULL), right(NULL) {
	}
};

class Solution {
public:
    TreeNode* first_mis = NULL;
    TreeNode* second_mis = NULL;
    TreeNode* pre = NULL;

	void recoverTree(TreeNode* root) {
		travel(root);
		// 把出错的两个节点中的值互换一下
		int temp = first_mis->val;
		first_mis->val = second_mis->val;
		second_mis->val = temp;
	}

	void visit(TreeNode* node) {
		// 第一个元素
		if (pre == NULL) {
			pre = node;
			return;
		}
		// 跟前一个元素比较，如果比前一个元素小，那么根据情况记录
		if (pre->val > node->val) {
			if (first_mis == NULL) {
				first_mis = pre;
				second_mis = node;
			} else {
				second_mis = node;
			}
		}
		pre = node;
	}

	void travel(TreeNode* node) {
		if (node->left != NULL) {
			travel(node->left);
		}
		visit(node);
		if (node->right != NULL) {
			travel(node->right);
		}
	}
};
