//https://leetcode.com/problems/spiral-matrix/discuss/948411/Faster-than-100.-Simple-solution-with-detailed-comments.

class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        
		//The vector which we return to the main function
		vector<int> nums;
       
	   //We set the boundaries of the input matrix manually
        int top = 0, bottom = matrix.size()-1, left = 0, right = matrix[0].size()-1;
		
		//Size of the matrix, which is basically, number of rows multiplies by number of columns
        int size = matrix.size() * matrix[0].size();
        
		//Here, nums.size() will serve as the counter
        while(nums.size()<size) {
		
		    //BTW, we continuously have to check if nums.size() is less than 
			//size or not in each iteration, since the last elements may get 
			//visited from any any of the four directions
		
		    //Adding the values of the top row to nums and then increasing 
			//top so that those elements don't get inserted into nums again
            for(int i=left; i<=right && nums.size()<size; i++)
                nums.push_back(matrix[top][i]);
            top++;
			
			//Same as above, for the rightmost column. If you face difficulty 
			//to understand this, try to draw the simulation a piece of paper.
			//Just go clockwise through the whole matrix, till you run out of 
			//unvisited elements
            for(int i=top; i<=bottom && nums.size()<size; i++)
                nums.push_back(matrix[i][right]);
            right--;
			
			//Again, the same thing to cover the bottommost row
            for(int i=right; i>=left && nums.size()<size; i--)
                nums.push_back(matrix[bottom][i]);
            bottom--;
			
			//Our fourth for loop, to cover the leftmost elements
            for(int i=bottom; i>=top && nums.size()<size; i--)
                nums.push_back(matrix[i][left]);
            left++;
        }
		
		//Return nums to the main function
        return nums;
    }
};
//Have a great day :D
//Upvote if you liked this post