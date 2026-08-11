class SummaryRanges {

    public SummaryRanges() {  }
    TreeSet<Integer> set = new TreeSet<>();
    
    public void addNum(int value) {
        set.add(value);      
    }
    
    public int[][] getIntervals() {
        ArrayList<int[]> al = new ArrayList<>();
        if (set.isEmpty()) return new int[0][];
        int start = -1,end = -1;
        for(int i : set){
            if(start == -1 && end == -1)
                start = end = i;
            else if(start != -1 && i == end + 1)
                end = i;
            else if(i > end + 1){
                al.add(new int[]{start, end});
                start = end = i;
            }
        }

        al.add(new int[]{start, end});

        int[][] arr = new int[al.size()][2];
        for(int i=0; i<al.size(); i++){
            arr[i] = al.get(i);
        }

        return arr;

        
    }
}