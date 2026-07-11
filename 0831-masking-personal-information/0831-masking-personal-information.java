class Solution{
    public String maskPII(String s){

        if(s.contains("@")){
            s=s.toLowerCase();
            int at=s.indexOf('@');
            return s.charAt(0) + "*****" +s.charAt(at-1)+s.substring(at);
        }
        String digits=s.replaceAll("[^0-9]", "");

        String last=digits.substring(digits.length()-4);
        int country=digits.length()-10;
        if(country==0)
            return "***-***-" + last;

        StringBuilder ans=new StringBuilder("+");
        for(int i=0;i<country;i++)
            ans.append("*");
        ans.append("-***-***-");
        ans.append(last);
        return ans.toString();
    }
}