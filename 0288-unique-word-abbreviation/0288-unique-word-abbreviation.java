import java.util.*;
class ValidWordAbbr {
    HashMap<String, HashSet<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        map=new HashMap<>();
        for (String word : dictionary)
        {
            String abbr=getAbbr(word);
            if (!map.containsKey(abbr)){
                map.put(abbr,new HashSet<>());
            }

            map.get(abbr).add(word);
        }
    }

    public boolean isUnique(String word) {

        String abbr=getAbbr(word);

        if (!map.containsKey(abbr)) {
            return true;
        }

        HashSet<String> set=map.get(abbr);

        if (set.size()==1 && set.contains(word)) {
            return true;
        }

        return false;
    }

    private String getAbbr(String word) {

        if (word.length()<= 2) {
            return word;
        }

        return "" + word.charAt(0)+(word.length()-2)+word.charAt(word.length()-1);
    }
}
