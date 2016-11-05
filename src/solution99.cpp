//============================================================================
// Name        : solution99.cpp
// Author      : JiangJiafu
// Version     :
// Copyright   : Your copyright notice
// Description : LeetCode: https://leetcode.com/problems/recover-binary-search-tree/
//               ����˼·����˳�������������һ�����ҵ�����Ԫ�أ�
//               ��һ��Ԫ���ǵ�һ�γ����ɴ�Сת���Ԫ���е��Ǹ�����Ԫ��
//               �ڶ���Ԫ���ǵڶ��γ����ɴ�Сת���Ԫ���е��Ǹ�С���Ԫ�أ�����
//                   | ��һ�γ����ɴ�С��5->3��
//               1 5 3 4 2 6 7 (2��5������λ��)
//                       | �ڶ��γ����ɴ�С��4��>2��
//               ����һ��������������ڵ��������������ˣ���ʱֻ�����һ���ɴ�С�ı仯��
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
		// �ѳ���������ڵ��е�ֵ����һ��
		int temp = first_mis->val;
		first_mis->val = second_mis->val;
		second_mis->val = temp;
	}

	void visit(TreeNode* node) {
		// ��һ��Ԫ��
		if (pre == NULL) {
			pre = node;
			return;
		}
		// ��ǰһ��Ԫ�رȽϣ������ǰһ��Ԫ��С����ô���������¼
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
