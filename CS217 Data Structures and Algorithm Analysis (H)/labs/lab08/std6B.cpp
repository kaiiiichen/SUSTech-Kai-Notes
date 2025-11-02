#include<bits/stdc++.h>
#define fi first
#define se second
#define mp make_pair
#define pb push_back
typedef long long ll;
typedef long double ld;
using namespace std;
const int N=500010,M=5000010,INF=0x3f3f3f3f;
int T,n,lt,rt,ans,a[N],b[N],ls[N],rs[N],lg[N];
int partition(int l,int r){
	int mid=(l+r)>>1,p=a[mid];
	int i=l-1,j=r+1,it=1,jt=1,op=0;
	int len=min(p-l+1,r-p);
	if(len*lg[len]>=r-l+1){
		while(1){
			while(i<r){
				i++;
				if(a[i]>=p)break;
			}
			while(j>l){
				j--;
				if(a[j]<=p)break;
			}
			if(i>=j)return j;
			swap(a[i],a[j]);
			swap(b[a[i]],b[a[j]]);
			ans++;
		}
	}
	lt=rt=0;
	if(p<mid){
		for(int i=l;i<=p;i++)
			if(a[i]>p)ls[++lt]=i;
		for(int i=l;i<p;i++)
			if(b[i]>=p)rs[++rt]=b[i];
	}
	else{
		for(int i=p;i<=r;i++)
			if(a[i]<p)rs[++rt]=i;
		for(int i=p+1;i<=r;i++)
			if(b[i]<=p)ls[++lt]=b[i];
	}
	sort(ls+1,ls+lt+1);
	sort(rs+1,rs+rt+1);
	reverse(rs+1,rs+rt+1);
	ls[++lt]=INF,rs[++rt]=-INF;
	while(1){
		++i,--j;
		while(ls[it]<i)++it;
		while(rs[jt]>j)++jt;
		i=min(ls[it],b[p]>=i?b[p]:INF);
		j=max(rs[jt],b[p]<=j?b[p]:-INF);
		if(i>=j)return p-op;
		if(i==b[p])op=1;
		swap(a[i],a[j]);
		swap(b[a[i]],b[a[j]]);
		ans++;
	}
}
void qsort(int l,int r){
	if(l<0||r<0||l>=r)return;
	int mid=partition(l,r);
	qsort(l,mid);
	qsort(mid+1,r);
}
void solve(){
	cin>>n,ans=0;
	for(int i=1;i<=n;i++)
		cin>>a[i],b[a[i]]=i;
	qsort(1,n);
	cout<<ans<<'\n';
}
int main(){
	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
	for(int i=2;i<N;i++)lg[i]=lg[i>>1]+1;
	cin>>T;
	while(T--)solve();
	return 0;
}
