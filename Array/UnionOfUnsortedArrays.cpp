#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin>>t;
	while(t--) {
	    int n,m;
	    cin>>n>>m;
	    int aa[n],bb[m];
	    set<int> a;
	    for(int i=0;i<n;i++) {
	        cin>>aa[i];
	        a.insert(aa[i]);
	    }
	    for(int i=0;i<m;i++) {
	        cin>>bb[i];
	        a.insert(bb[i]);
	    }
	    cout<<a.size()<<endl;
	}
	return 0;
}