// ARVIND PATEL

#include<bits/stdc++.h>
#include<atomic>
using namespace std;

bool anyLockNode;

class Tree {
public:
	string name;
	int uid;
	atomic<int> isLocked;
	int desCnt;
	Tree *parent;
	vector<Tree*> child;

	Tree()
	{
		name = "";
		uid = -1;
		isLocked = 0;
		desCnt = 0;
		parent = NULL;
	}
	Tree(string str, Tree *par)
	{
		name = str;
		uid = -1;
		isLocked = 0;
		desCnt = 0;
		parent = par;
	}
};

void inorder(Tree *node)
{
	if (node == NULL) return;
	cout << (node->name) << " ";
	for (int i = 0; i < (node->child).size(); i++)
	{
		inorder((node->child)[i]);
	}
}

int isLock(Tree *node) {
	return (node->isLocked).load();
}

// atomic {
//  
// }
bool Lock(Tree *node, int id)
{
  
  node->isLocked++ ;
  if( node->isLocked > 1) {
    	node->isLocked--;
      return false;
  }
  if ((node->desCnt) != 0) {
    node->isLocked = 0;
    return false;
  }
	
	Tree *T = node->parent;
	bool flag = false;

	while ( T != NULL )
	{
		++(T->desCnt); // +1
    if(T->parent->isLocked == 1) {
			flag = true;
			break;
		}
		T = T->parent; 
	}
  
	if (flag) {
		Tree *K = node;
		while ( K != NULL && K != T )                               	// setting back isWorkingDesc to false
		{
			--(T->desCnt);
			K = K->parent;
		}
		node->isLocked = 0;											// setting back busy state to false
		return false;
  }
	
		node->uid = id;
		return true;
}

bool unLock(Tree *node, int id)
{
	if (isLock(node) == false) return false;
	if (node->uid != id) return false;

	Tree *T = node;
	node->isLocked = false;
	node->uid = -1;
	while ( T != NULL )
	{
		--(T->desCnt);
		T = T->parent;
	}
	return true;
}

bool checkDesc(Tree *node, int id)
{
	bool flag = true;
	if (node == NULL) return flag;
	for (int i = 0; i < (node->child).size(); i++)
	{
		if (((node->child)[i])->isLocked == true)
		{
			if (((node->child)[i])->uid != id) return false;
			anyLockNode = true;
		}
		else if (((node->child)[i])->desCnt > 0)
		{
			if (checkDesc((node->child)[i], id) == false) return false;
		}
	}
	return true;
}
void setDesc(Tree *node)
{
	if (node == NULL) return;
	for (int i = 0; i < (node->child).size(); i++)
	{
		if (((node->child)[i])->isLocked == true)
		{
			((node->child)[i])->isLocked = false;
			--(((node->child)[i])->desCnt);
			((node->child)[i])->uid = -1;
		}
		else if (((node->child)[i])->desCnt > 0)
		{
			setDesc((node->child)[i]);
			--(((node->child)[i])->desCnt);
		}
	}
}

bool UpgradeLock(Tree *node, int id)
{
	anyLockNode = false;
	if (isLock(node) == true) return false;
	if (checkDesc(node, id) && anyLockNode == true)
	{
		node->isLocked = true;
		node->uid = id;
		setDesc(node);
		node->desCnt = 1;
		return true;
	}
	else return false;
}

int main()
{
	Tree* root = new Tree();
	int N, M, Q;
	cin >> N >> M >> Q;
	vector<string> names;
	vector<Tree*> nodes;
	map<string, Tree*> mp;
	string s;
	for (int i = 0; i < N; i++)
	{
		cin >> s;
		if (i == 0) {
			Tree* newNode = new Tree(s, root);
			nodes.push_back(newNode);
			mp[s] = newNode;
			root = newNode;
		}
		else
		{
			Tree* newNode = new Tree(s, nodes[(i - 1) / M]);
			nodes.push_back(newNode);
			mp[s] = newNode;
		}
	}
	int cld;
	for (int i = 0; i < N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			cld = M * i + j;
			if (cld < N) (nodes[i]->child).push_back(nodes[cld]);
		}
	}
	int type, id;
	for (int i = 0; i < Q; i++)
	{
		cin >> type >> s >> id;
		if (type == 1)    // Lock
		{
			if (Lock(mp[s], id)) cout << "true" << endl;
			else cout << "false" << endl;
		}
		else if (type == 2) // unLock
		{
			if (unLock(mp[s], id)) cout << "true" << endl;
			else cout << "false" << endl;
		}
		else {                          // upgradeLock
			if (UpgradeLock(mp[s], id)) cout << "true" << endl;
			else cout << "false" << endl;
		}
	}
	return 0;

}
















