public class MajorityElement2 {
    // Note : only use elseif ladder no if else
    int candidate1 = 0, candidate2 = 0, count1 =  0, count2 = 0;for(
    int el:arr)
    {
        if (el == candidate1)
            count1++;
        else if (el == candidate2)
            count2++;
        else if (count1 == 0) {
            candidate1 = el;
            count1 = 1;
        } else if (count2 == 0) {
            candidate2 = el;
            count2 = 1;
        } else {
            count1--;
            count2--;
        }
    }

    // verify
    final double FREQ = Math.floor(arr.length / 3);count1=0;count2=0;for(
    int el:arr)
    {
        if (el == candidate1)
            count1++;
        else if (el == candidate2)
            count2++;
    }
    List<Integer> ans = new ArrayList<>();if(count1>FREQ)ans.add(candidate1);if(count2>FREQ)ans.add(candidate2);

    return ans;
}}
