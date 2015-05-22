/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package xml.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Nassim
 */
public class O1DataStruct // NB : insert = O(1) amortized
{
    private HashMap<Integer, Integer> H;
    private List<Integer> A;
    
    public O1DataStruct()
    {
        this.H = new HashMap<Integer, Integer>();
        this.A = new ArrayList<Integer>();
    }
    
    public void insert(int id)
    {
        A.add(id);
        H.put(id, A.size() - 1);
    }
    
    public boolean contains(int id)
    {
        return H.containsKey(id);
    }
    
    public int getRandomElement()
    {
        int min = 0;
        int max = A.size() - 1;
        int r = min + (int)(Math.random() * (max - min + 1));
        
        return A.get(r);
    }
    
    public void remove(int id)
    {
        int lastIndex = A.size() - 1;
        int lastId = (int)A.get(lastIndex);
        int removedIndex = (int)H.get(id);
        H.remove(id);
        A.remove(removedIndex);
        A.add(removedIndex, lastId);
        A.remove(lastIndex);
        H.put(lastId, removedIndex);
    }
}
