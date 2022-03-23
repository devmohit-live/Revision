class {

    //Approach : Greedly chose 1 heiviest and 1 lighest and par them, if sum of both their weight < = lim
    // put them in a boat else put the heaviset one on the boat.
    // to take the lighest and heaviest at a time we sorted the array and use 2 pointers


    //Only 2 peoples at most can be on boat at a time:
     public int numRescueBoats(int[] people, int limit) {
        int i = 0, n = people.length , j = n-1;
        Arrays.sort(people);
        int count = 0;
        //boat can carry on 2 people at max;
        
        while(i<=j){ //a single person can also go to baot i==j => same person
            
            if(people[i] + people[j] <=limit) {
                i++;
                j--;
            }else{
                //let the heavy one go alone
                j--;
            }
            count++;
        }
        
        return count;
    }



    // Follow Up : What if a boat can take any number of people within a limited weight ?
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length; 
        int i=n-1; 
        int p=0; // pointer to take lighest people
        int boatCnt=0;

        //Approach : take the heaviest ans as much as lighest people

        while (i>=p) { // till all the people are sent (same people)
            int weight=people[i]; //heaviest
            //  leightest = people[p];
            while (p<i &&  people[p] +weight<=limit) {
                weight=weight+people[p];
                p++; 
            }    
            boatCnt++; 
            i--; 
        }
        return boatCnt; 
    }
}