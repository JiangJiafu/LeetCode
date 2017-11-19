#include <stack>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

nclude <stack>

class BSTIterator {
public:
    BSTIterator(TreeNode *root) {
        if (root)
            pushParent(root);
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return !parentNodes.empty();
    }

    /** @return the next smallest number */
    int next() {
        TreeNode *next = parentNodes.top();
        parentNodes.pop();
        int ret = next->val;
        if (next->right) {
            pushParent(next->right);
        }
        return ret;
    }

private:
    TreeNode *root = NULL;
    stack<TreeNode *> parentNodes;

    void pushParent(TreeNode *p) {
        if (p->left) {
            parentNodes.push(p);
            pushParent(p->left);
        }
        else
            parentNodes.push(p);
        
    }
};
/**
 *  * Your BSTIterator will be called like this:
 *   * BSTIterator i = BSTIterator(root);
 *    * while (i.hasNext()) cout << i.next();
 *     */
