class Solution {
    public String licenseKeyFormatting(String s,int k){
        StringBuilder cleaned=new StringBuilder();
        for (char c:s.toCharArray()){
            if (c!='-') {
                cleaned.append(Character.toUpperCase(c));
            }
        }
        int n=cleaned.length();
        StringBuilder result=new StringBuilder();
        
      
        for(int i=0;i<n;i++){
            result.append(cleaned.charAt(n-1-i));
            if ((i+1)%k==0&&i+1!=n){
                result.append('-');
            }
        }
        
        return result.reverse().toString();
    }
}