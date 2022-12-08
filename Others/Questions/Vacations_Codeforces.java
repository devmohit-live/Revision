    import java.util.Scanner;
    import java.util.Arrays;
    public class Vacations_Codeforces{
        
        static final int MAX = (int)1e9;
        static final int GYM_AND_CONTEST_CLOSED = 0 , GYM = 2, CONTEST = 1 ,GYM_AND_CONTEST_OPEN = 3, REST = 0; 

        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();sc.nextLine();
                String[] inp = sc.nextLine().split(" ");
                int[] days = new int[inp.length];
                for(int i=0;i<n;i++) days[i] = Integer.parseInt(inp[i]);
                System.out.println(solve(n , days));
        }

        private static void intializeDp(int[][] dp){
            for(int[] row : dp) Arrays.fill(row, -1);
        }

        private static int solve(int n, int[] arr){
            int[][] dp = new int[n+1][4]; // 3 choices rest,gym,music
            intializeDp(dp);
            int minRestDays = findMinRestDaysCount(arr, 0, REST, dp);
            return minRestDays == MAX ? 0 : minRestDays;
        }
        private static int findMinRestDaysCount(int[] days, int idx, int lastTaskIndicator, int[][] dp){
            if(idx >= days.length) return 0;
            if(dp[idx][lastTaskIndicator]!=-1) return dp[idx][lastTaskIndicator];
            
            int ans = MAX;
            if(days[idx] == GYM_AND_CONTEST_CLOSED){ // taken a rest today : no choice
                ans = 1 + findMinRestDaysCount(days, idx+1, REST, dp);
            }else if(days[idx] == GYM){ //can go to gym, contest is not happening
                    if(lastTaskIndicator ==  GYM) // have to take rest
                        ans = 1 + findMinRestDaysCount(days, idx+1, REST, dp);
                    else 
                        ans = findMinRestDaysCount(days, idx+1, GYM, dp);
            }else if(days[idx] == CONTEST){
                if(lastTaskIndicator == CONTEST)
                    ans = 1 + findMinRestDaysCount(days, idx + 1, REST, dp);
                else 
                    ans = findMinRestDaysCount(days, idx + 1, CONTEST, dp);
            }else if(days[idx] == GYM_AND_CONTEST_OPEN){
               int a = lastTaskIndicator == GYM ? MAX : findMinRestDaysCount(days, idx+1, GYM, dp);
               int b = lastTaskIndicator == CONTEST ? MAX : findMinRestDaysCount(days, idx+1, CONTEST, dp);
               ans = Math.min(a,b);
            }

            return dp[idx][lastTaskIndicator] = ans;
        }

    }