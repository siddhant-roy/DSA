class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        
       if(!matrix.size() || !matrix[0].size())
            return false;
    
        int m = matrix.size();
        int n = matrix[0].size();
        
        int start = 0, end = m*n - 1;
        
        //Treat matrix as a sorted list of numbers
        while(start <= end)
        {
            int mid = start + (end-start)/2;
            int curr = matrix[mid/n][mid%n];
            
            if( curr == target)
                return true;
            else if( curr > target)
                end = mid-1;
            else
                start = mid+1;
        }

        return false;
    }
};